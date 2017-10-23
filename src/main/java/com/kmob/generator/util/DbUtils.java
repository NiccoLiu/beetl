package com.kmob.generator.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.kmob.generator.beetl.TemplateUtils;
import com.kmob.generator.database.Column;
import com.kmob.generator.database.DbDataTypesUtils;
import com.kmob.generator.database.Table;
import com.kmob.generator.database.TableFactory;
import com.kmob.generator.template.CRUD;
import com.kmob.generator.template.model.FormType;
import com.kmob.generator.template.model.InputType;
import com.kmob.generator.template.model.ModelAttr;

import static com.kmob.generator.util.Config.getStr;

public class DbUtils {

	public static void init() {
		String db_type = getStr("db_type") + ".";
		String jdbcUrl = getStr(db_type + "jdbcUrl");
		String user = getStr(db_type + "user");
		String password = getStr(db_type + "password");
		String driverClass = getStr(db_type + "driverClass");

		System.out.println("####jdbcUrlRead:" + jdbcUrl);
		System.out.println("####user:" + user);
		System.out.println("####password:" + password.trim());
		System.out.println("####driverClass:" + driverClass);

		C3p0Plugin c3p0Plugin = new C3p0Plugin(jdbcUrl, user, password.trim(), driverClass);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		c3p0Plugin.start();
		arp.start();
	}

	/**
	 * 查询数据库表返回Map<String, CRUD>
	 * 
	 * @param tables
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, CRUD> getCRUDMap(String... tables) throws SQLException {
		Map<String, CRUD> crudMap = new HashMap<String, CRUD>();
		TableFactory tbFactory = TableFactory.getInstance();
		String[] tableArray = null;
		if (tables != null && tables.length > 0) {
			tableArray = tables;
		} else {
			List<Table> tmpList = tbFactory.getAllTables();
			tableArray = new String[tmpList.size()];
			for (int i = 0; i < tmpList.size(); i++) {
				tableArray[i] = tmpList.get(i).getTableName();
			}

		}
		for (String tableName : tableArray) {
			// 获取
			Table table = tbFactory.getTable(tableName);

			System.out.println(table.getRemarks() + ":" + table.getTableName());
			if (StrUtils.isEmpty(table.getRemarks())) {
				System.err.println("####" + table.getTableName() + "没有备注！！！");
			}
			CRUD model = new CRUD();
			model.setProperties(Config.configMap);
			// 把Table设置进去，java文件生成需要
			model.setTable(table);

			if (table.getPkList().size() == 1) {
				model.setPrimaryKey(table.getPkList().get(0));
			}
			String urlKey = table.getTableName();
			if (table.getTableName().indexOf("_") >= 0) {
				urlKey = table.getTableName().substring(table.getTableName().indexOf("_") + 1);
				urlKey = urlKey.replaceAll("_", "");
			}

			model.setUrlKey(urlKey);
			model.setName(table.getRemarks());

			for (Column column : table.getColumns()) {
//				String colName = column.getColumnName();
				// 这里需要优化
				if (column.isPk()) {
					continue;
				}

				// 设置属性
				model.setAttr(attr(column));
			}
			crudMap.put(urlKey, model);
		}
		return crudMap;
	}

	/**
	 * 根据字段名和备注，设置属性
	 * 
	 * @param model
	 * @param colName
	 * @param remark
	 */
	public static ModelAttr attr(Column column) {
		String colName = column.getColumnName();
		String remark = column.getRemarks();
		boolean isNull = column.isNullable();
		boolean isNumber = DbDataTypesUtils.isIntegerNumber(column.getJavaType());

		ModelAttr attr = new ModelAttr();
		// 0 名称 1 增删改查设置 2 类型 3 类型参数
		String[] columnNames = remark.split("\\/");
		// 字段中文、英文、是否为空
		attr.setKey(colName).setName(columnNames[0]).setNull(isNull).setNumber(isNumber);

		// 增删改查设置
		if (columnNames.length >= 2) {
			String operateName = columnNames[1];
			if (StrUtils.isNotEmpty(operateName)) {
				double operate = 0;
				for (int i = 0; i < operateName.length(); i++) {
					int tmpOper = ("0".equals(operateName.charAt(i))) ? 0 : 1;
					operate += Math.pow(2, i) * tmpOper;
				}
				attr.setOperate((byte) operate);
			}
		}

		// 字段类型
		if (columnNames.length >= 3) {
			String type = columnNames[2].toUpperCase();
			String param = columnNames.length >= 4 ? columnNames[3] : null;
			if (param != null && param.indexOf("key") == -1 && param.indexOf("value") == -1) {
				// 1,男,2,女
				param = TemplateUtils.getJson(param.split(","));
			}

			if (type.equals(FormType.TEXTAREA.toString())) {
				attr.setFormType(FormType.TEXTAREA);
			} else if (type.equals(FormType.SELECT.toString())) {
				attr.setFormType(FormType.SELECT).setFormTypeData(param);
			} else if (type.equals(FormType.DICT.toString())) {
				attr.setFormType(FormType.DICT).setFormTypeData(param);
			} else if (type.equals(FormType.DATE.toString())) {
				attr.setFormType(FormType.DATE).setFormTypeData(param);
			} else { // 默认INPUT
				if (type.equals(InputType.RADIO.toString())) {
					attr.setInputType(InputType.RADIO).setFormTypeData(param);
				} else if (type.equals(InputType.CHECKBOX.toString())) {
					attr.setInputType(InputType.CHECKBOX).setFormTypeData(param);
				}
			}
		}
		return attr;
	}

}
