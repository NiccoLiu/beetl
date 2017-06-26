package com.kmob.generator.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import com.kmob.generator.util.StrKit;


public class Table implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据库中表的表名称,其它属性很多都是根据此属性派生
	 */
	private String tableName;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 列表
	 */
	private LinkedHashSet<Column> columns = new LinkedHashSet<Column>();
	/**
	 * 主键
	 */
	private List<String> primaryKeyList = new ArrayList<String>();

	public Table() {
	}

	public Table(Table t) {
		setTableName(t.getTableName());
		this.remarks = t.getRemarks();
		this.columns = t.getColumns();
		this.primaryKeyList = t.getPkList();
	}

	/**
	 * 根据sqlName得到的类名称，示例值: UserInfo
	 * 
	 * @return
	 */
	public String getClassName() {
		return StrKit.makeAllWordFirstLetterUpperCase(StrKit.toUnderscoreName(tableName));
	}

	public Column getColumnByName(String name) {
		Column c = getColumnBySqlName(name);
		if (c == null) {
			c = getColumnBySqlName(StrKit.toUnderscoreName(name));
		}
		return c;
	}

	public Column getColumnBySqlName(String sqlName) {
		for (Column c : getColumns()) {
			if (c.getColumnName().equalsIgnoreCase(sqlName)) {
				return c;
			}
		}
		return null;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/** 数据库中表的表备注 */
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void addColumn(Column column) {
		columns.add(column);
	}

	public LinkedHashSet<Column> getColumns() {
		return columns;
	}

	/**
	 * 得到是主键的全部column
	 * 
	 * @return
	 */
	public List<String> getPkList() {
		return primaryKeyList;
	}

	public void setPkList(List<String> primaryKeyList) {
		this.primaryKeyList = primaryKeyList;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public String toString() {
		return "[" + getRemarks() + ":" + getTableName() + " ]";
	}
}
