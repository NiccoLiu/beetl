package com.kmob.generator.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import com.kmob.generator.util.Config;
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
	 * 非空列
	 */
	private LinkedHashSet<Column> noNullcolumns = new LinkedHashSet<Column>();
	
	/**
	 * 搜索列
	 */
	private LinkedHashSet<Column> searchcolumns = new LinkedHashSet<Column>();
	
	
	
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
		this.noNullcolumns = t.getNotNullColumns();
		this.searchcolumns = t.getSearchColumns();
		this.primaryKeyList = t.getPkList();
	}

	/**
	 * 根据sqlName得到的类名称，示例值: UserInfo
	 * 
	 * @return
	 */
	public String getClassName() {
		return StrKit.makeAllWordFirstLetterUpperCase(StrKit.toUnderscoreName(tableName.replace(Config.getStr("template.table.remove.prefixes"), "")));
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
	
	public void addNotNullColumn(Column column){
	    noNullcolumns.add(column);
	}
	
	public void addSearchColumn(Column column){
	    searchcolumns.add(column);
	}

	public LinkedHashSet<Column> getColumns() {
		return columns;
	}
	
	public LinkedHashSet<Column> getNotNullColumns() {
	    return noNullcolumns;
	}
	

	public LinkedHashSet<Column> getSearchColumns() {
        return searchcolumns;
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

	@Override
    public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	/**
	 * 获取多少列
	 * 
	 * 
	 * @return
	 */
	public int getColumnCount() {
        return columns.size();
    }
	
	
	/**
	 * 获取多少列
	 * 
	 * 
	 * @return
	 */
	public int getHalfColumnCount() {
	    if(columns.size() % 2 == 0){
	        return ( columns.size() - 1 )/2;
	    }else{
	        return( columns.size() -1 )/2 + 1;
	    }
	}
	
	

    @Override
    public String toString() {
		return "[" + getRemarks() + ":" + getTableName() + " ]";
	}
}
