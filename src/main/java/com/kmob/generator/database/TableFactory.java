package com.kmob.generator.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Config;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;

/**
 * 
 * 根据数据库表的元数据(metadata)创建Table对象
 * 
 * getTable(sqlName) : 根据数据库表名,得到table对象
 * getAllTable() : 搜索数据库的所有表,并得到table对象列表
 * 
 */
public class TableFactory {
	private static TableFactory instance = null;

	private TableFactory() {
	}

	public synchronized static TableFactory getInstance() {
		if (instance == null)
			instance = new TableFactory();
		return instance;
	}

	public String getCatalog() {
		return null;
	}

	public String getSchema() {
		return null;
	}

	public List<Table> getAllTables() throws SQLException {
		Config config = getConfig();
		Connection conn = config.getConnection();
		DatabaseMetaData dbMetaData = conn.getMetaData();
		ResultSet rs = dbMetaData.getTables(getCatalog(), getSchema(), null, null);
		List<Table> tables = new ArrayList<Table>();
		while (rs.next()) {
			String realTableName = rs.getString("TABLE_NAME");
			String remarks = rs.getString("REMARKS");
			// if(remarks == null && DbKit.getDialect().isOracle()) {
			// remarks = getOracleTableComments(realTableName);
			// }
			Table table = new Table();
			table.setTableName(realTableName);
			table.setRemarks(remarks);
			tables.add(table);
		}
		conn.close();// 关闭数据库连接
		return tables;
	}

	public Table getTable(String tableName) {
		return getTable(getSchema(), tableName);
	}

	private Table getTable(String schema, String tableName) {
		return getTable(getCatalog(), schema, tableName);
	}

	private Table getTable(String catalog, String schema, String tableName) {
		if (tableName == null || tableName.trim().length() == 0)
			throw new IllegalArgumentException("tableName must be not empty");

		Table t = null;
		try {
			t = _getTable(catalog, schema, tableName);
			if (t == null && !tableName.equals(tableName.toUpperCase())) {
				t = _getTable(catalog, schema, tableName.toUpperCase());
			}
			if (t == null && !tableName.equals(tableName.toLowerCase())) {
				t = _getTable(catalog, schema, tableName.toLowerCase());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (t == null) {
			throw new RuntimeException("not found table with give name:" + tableName);
		}
		return t;
	}

	private Table _getTable(String catalog, String schema, String tableName) throws SQLException {
		Config config = getConfig();
		Connection conn = config.getConnection();
		DatabaseMetaData dbMetaData = conn.getMetaData();
		ResultSet rs = dbMetaData.getTables(catalog, schema, tableName, null);
		while (rs.next()) {
			Table table = createTable(conn, rs);
			return table;
		}
		conn.close();
		return null;
	}

	/**
	 * 生成table类
	 * 
	 * 
	 * @param conn
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Table createTable(Connection conn, ResultSet rs) throws SQLException {
		String realTableName = null;
		try {
			realTableName = rs.getString("TABLE_NAME");
			String remarks = rs.getString("REMARKS");
            if (remarks == null) {
                remarks = getOracleTableComments(realTableName);
            }

			Table table = new Table();
			table.setTableName(realTableName);
			table.setRemarks(remarks);

			// 获取信息
			List<String> primaryKeys = getTablePrimaryKeys(table, conn);
			table.setPkList(primaryKeys);

			// get the indices and unique columns
			List<String> indices = new LinkedList<String>();

			// 记录唯一索引~一会去重
			Map<String, String> uniques = new HashMap<String, String>();
			List<String> nonuniques = new ArrayList<String>();

			ResultSet indexRs = null;
			DatabaseMetaData dbMetaData = conn.getMetaData();
			indexRs = dbMetaData.getIndexInfo(getCatalog(), getSchema(), table.getTableName(), false, true);
			while (indexRs.next()) {
				String columnName = indexRs.getString("COLUMN_NAME");
				if (columnName == null) {
					continue;
				}
				indices.add(columnName);

				// now look for unique columns
				String indexName = indexRs.getString("INDEX_NAME");
				boolean nonUnique = indexRs.getBoolean("NON_UNIQUE");
				if (indexName == null) {
					continue;
				}

				// 唯一键记录
				if (!nonUnique) {
					if (uniques.containsKey(indexName)) {
						// 记录重复索引
						nonuniques.add(indexName);
					}
					uniques.put(indexName, columnName);
				}

			}

			// 去除重复，组合唯一键
			for (String key : nonuniques) {
				uniques.remove(key);
			}

			List<Column> columns = getTableColumns(conn, table, indices, uniques);

			for (Column column : columns) {
				table.addColumn(column);
				String searchColumn = com.kmob.generator.util.Config.getStr("template.table."+table.getTableName()+".searchColumn");
				
				if(StringUtils.isNoneBlank(searchColumn)){
				    String[] searchColumns = searchColumn.split(",");
				    
				    for(String c:searchColumns){
				        if(column.getColumnName().toLowerCase().equals(c.toLowerCase())){
				            table.addSearchColumn(column);
				        }
				    }
				}
				
				if(!column.isNullable() && !column.isPk()){
				    table.addNotNullColumn(column);
				}
			}

			return new Table(table);
		} catch (SQLException e) {
			throw new RuntimeException("create table object error,tableName:" + realTableName, e);
		} finally {
			conn.close();
		}
	}

	private List<Column> getTableColumns(Connection conn, Table table, List<String> indices, Map<String, String> uniques)
			throws SQLException {
		// get the columns
		List<Column> columns = new LinkedList<Column>();
		ResultSet columnRs = conn.getMetaData().getColumns(getCatalog(), getSchema(), table.getTableName(), null);
		
		while (columnRs.next()) {
			int sqlType = columnRs.getInt("DATA_TYPE");
			String sqlTypeName = columnRs.getString("TYPE_NAME");
			String columnName = columnRs.getString("COLUMN_NAME");
			String columnDefaultValue = columnRs.getString("COLUMN_DEF");
			String remarks = columnRs.getString("REMARKS");

            if (remarks == null ) {
                remarks = getOracleColumnComments(table.getTableName(), columnName);
            }

			// if columnNoNulls or columnNullableUnknown assume "not nullable"
			int size = columnRs.getInt("COLUMN_SIZE");
			int decimalDigits = columnRs.getInt("DECIMAL_DIGITS");

			boolean isPk = table.getPkList().contains(columnName);
			boolean isNullable = (DatabaseMetaData.columnNullable == columnRs.getInt("NULLABLE"));
			boolean isIndexed = indices.contains(columnName);
			boolean isUnique = uniques.containsValue(columnName);

			Column column = new Column(table, sqlType, sqlTypeName, columnName, size, decimalDigits, isPk, isNullable,
					isIndexed, isUnique, columnDefaultValue, remarks);
			columns.add(column);
		}
		columnRs.close();
		return columns;
	}

	private List<String> getTablePrimaryKeys(Table table, Connection conn) throws SQLException {
		// get the primary keys
		List<String> primaryKeys = new LinkedList<String>();
		ResultSet primaryKeyRs = conn.getMetaData().getPrimaryKeys(getCatalog(), getSchema(), table.getTableName());
		while (primaryKeyRs.next()) {
			String columnName = primaryKeyRs.getString("COLUMN_NAME");
			primaryKeys.add(columnName);
		}
		primaryKeyRs.close();
		return primaryKeys;
	}

	protected String getOracleTableComments(String table) {
		String sql = "SELECT comments FROM user_tab_comments WHERE table_name='" + table + "'";
		return Db.queryStr(sql);
	}

	protected String getOracleColumnComments(String table, String column) {
		String sql = "SELECT comments FROM user_col_comments WHERE table_name='" + table + "' AND column_name = '"
				+ column + "'";
		return Db.queryStr(sql);
	}

	private Config getConfig() {
		return DbKit.getConfig();
	}
}
