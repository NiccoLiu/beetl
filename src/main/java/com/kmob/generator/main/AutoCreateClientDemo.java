package com.kmob.generator.main;

import java.sql.SQLException;
import java.util.Map;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.kmob.generator.template.CRUD;
import com.kmob.generator.util.AutoCreate;
import com.kmob.generator.util.DbUtils;
import com.kmob.generator.util.StrUtils;

public class AutoCreateClientDemo {

	public static void main(String[] args) throws Exception {
		run();
	}

	protected static void run() throws SQLException, Exception {
		init();

		String module = "system";
		String packagePath = "com.kmob.powernetwork.operation";
		String tables = "t_student";

		Map<String, CRUD> crudMap = null;
		if (StrUtils.isEmpty(tables) || "all".equalsIgnoreCase(tables)) {
			crudMap = DbUtils.getCRUDMap();
		} else {
			String[] tableArray = tables.split(",");
			crudMap = DbUtils.getCRUDMap(tableArray);
		}

		new AutoCreate().setTemplatePath("/template/project/dudu/").setPackagePath(packagePath).setModule(module).setCrudMap(crudMap).create();
	}

	public static void init() {

		String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/guns?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
		String user = "root";
		String password = "admin";
		String driverClass = "com.mysql.jdbc.Driver";

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

}
