package com.kmob.generator.database;

import com.kmob.generator.util.StrKit;


/**
 * 用于生成代码的Columb对象.对应数据库表column
 *   
 * @author zhouzhixiang  
 * @date 2017年6月26日
 * @since 1.0
 */
public class Column implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	/**
	 * Reference to the containing table
	 */
	private Table table;

	/**
	 * The java.sql.Types type
	 */
	private int sqlType;

	/**
	 * The sql typename. provided by JDBC driver
	 */
	private String sqlTypeName;

	/**
	 * The name of the column
	 */
	private String columnName;

	/**
	 * True if the column is a primary key
	 */
	private boolean isPk;

	/**
	 * @todo-javadoc Describe the column
	 */
	private int size;

	/**
	 * @todo-javadoc Describe the column
	 */
	private int decimalDigits;

	/**
	 * True if the column is nullable
	 */
	private boolean isNullable;

	/**
	 * True if the column is indexed
	 */
	private boolean isIndexed;

	/**
	 * True if the column is unique
	 */
	private boolean isUnique;

	/**
	 * Null if the DB reports no default value
	 */
	private String defaultValue;

	/**
	 * The comments of column
	 */
	private String remarks;

	/**
	 * @param table
	 * @param sqlType
	 * @param sqlTypeName
	 * @param columnName
	 * @param size
	 * @param decimalDigits
	 * @param isPk
	 * @param isNullable
	 * @param isIndexed
	 * @param isUnique
	 * @param defaultValue
	 * @param remarks
	 */
	public Column(Table table, int sqlType, String sqlTypeName, String columnName, int size, int decimalDigits,
			boolean isPk, boolean isNullable, boolean isIndexed, boolean isUnique, String defaultValue, String remarks) {
		if (columnName == null)
			throw new NullPointerException();
		this.table = table;
		this.sqlType = sqlType;
		this.columnName = columnName;
		this.sqlTypeName = sqlTypeName;
		this.size = size;
		this.decimalDigits = decimalDigits;
		this.isPk = isPk;
		this.isNullable = isNullable;
		this.isIndexed = isIndexed;
		this.isUnique = isUnique;
		this.defaultValue = defaultValue;
		this.remarks = remarks;
	}

	public Column(Column c) {
		this(c.getTable(), c.getSqlType(), c.getSqlTypeName(), c.getColumnName(), c.getSize(), c.getDecimalDigits(), c
				.isPk(), c.isNullable(), c.isIndexed(), c.isUnique(), c.getDefaultValue(), c.getRemarks());
	}

	public Column() {
	}

	/**
	 * Gets the SqlType attribute of the Column object
	 * 
	 * @return The SqlType value
	 */
	public int getSqlType() {
		return sqlType;
	}

	/**
	 * Gets the Table attribute of the DbColumn object
	 * 
	 * @return The Table value
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Gets the Size attribute of the DbColumn object
	 * 
	 * @return The Size value
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Gets the DecimalDigits attribute of the DbColumn object
	 * 
	 * @return The DecimalDigits value
	 */
	public int getDecimalDigits() {
		return decimalDigits;
	}

	/**
	 * Gets the SqlTypeName attribute of the Column object
	 * 
	 * @return The SqlTypeName value
	 */
	public String getSqlTypeName() {
		return sqlTypeName;
	}

	/**
	 * Gets the SqlName attribute of the Column object
	 * 
	 * @return The SqlName value
	 */
	public String getColumnName() {
		if (columnName == null)
			throw new NullPointerException();
		return columnName;
	}

	/**
	 * Gets the Pk attribute of the Column object
	 * 
	 * @return The Pk value
	 */
	public boolean isPk() {
		return isPk;
	}

	/**
	 * Gets the Nullable attribute of the Column object
	 * 
	 * @return The Nullable value
	 */
	public boolean isNullable() {
		return isNullable;
	}

	/**
	 * Gets the Indexed attribute of the DbColumn object
	 * 
	 * @return The Indexed value
	 */
	public boolean isIndexed() {
		return isIndexed;
	}

	/**
	 * Gets the Unique attribute of the DbColumn object
	 * 
	 * @return The Unique value
	 */
	public boolean isUnique() {
		return isUnique;
	}

	/**
	 * Gets the DefaultValue attribute of the DbColumn object
	 * 
	 * @return The DefaultValue value
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * 列的数据库备注
	 * 
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 得到 jdbcSqlType类型名称，示例值:VARCHAR,DECIMAL, 现Ibatis3使用该属性
	 */
	public String getJdbcType() {
		String result = JdbcType.getJdbcSqlTypeName(getSqlType());
		return result;
	}

	/**
	 * 得到对应的javaType,如java.lang.String,
	 *
	 * @return
	 */
	public String getJavaTypeObject() {
		return DbDataTypesUtils.getPreferredJavaTypeObject(getSqlType(), getSize(), getDecimalDigits());
	}

	/**
	 * 得到对应的javaType,如java.lang.String,
	 *
	 * @return
	 */
	public String getJavaType() {
		return DbDataTypesUtils.getPreferredJavaType(getSqlType(), getSize(), getDecimalDigits());
	}

	/**
	 * 根据列名，根据sqlName计算得出，示例值： BirthDate
	 **/
	public String getColumnJavaName() {
		return StrKit.makeAllWordFirstLetterUpperCase(StrKit.toUnderscoreName(getColumnName()));
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o instanceof Column) {
			Column other = (Column) o;
			if (getColumnName().equals(other.getColumnName())) {
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		if (getTable() != null) {
			return (getTable().getTableName() + "#" + getColumnName()).hashCode();
		} else {
			return (getColumnName()).hashCode();
		}
	}

	public String toString() {
		return getRemarks() + ":" + getColumnName();
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
