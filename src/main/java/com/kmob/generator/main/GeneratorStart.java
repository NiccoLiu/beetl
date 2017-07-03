package com.kmob.generator.main;

import java.sql.SQLException;
import java.util.Map;

import com.kmob.generator.template.CRUD;
import com.kmob.generator.util.AutoCreate;
import com.kmob.generator.util.Config;
import com.kmob.generator.util.DbUtils;
import com.kmob.generator.util.StrUtils;

/**
 * 
 * 
 *   
 * @author zhouzhixiang  
 * @date 2017年6月26日
 * @since 1.0
 */
public class GeneratorStart {

	public static void main(String[] args) throws Exception {
		run();
	}

	protected static void run() throws SQLException, Exception {
		DbUtils.init();

		String selected = Config.getStr("template.selected");
		String tables = Config.getStr("template.tables");
		
		Map<String, CRUD> crudMap = null;
		if (StrUtils.isEmpty(tables) || "all".equalsIgnoreCase(tables)) {
			crudMap = DbUtils.getCRUDMap();
		} else {
			String[] tableArray = tables.split(",");
			crudMap = DbUtils.getCRUDMap(tableArray);
		}

		new AutoCreate().setTemplatePath(Config.getStr(selected)).setCrudMap(crudMap).create();
	}

}
