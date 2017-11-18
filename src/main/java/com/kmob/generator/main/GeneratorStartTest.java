package com.kmob.generator.main;

import java.sql.SQLException;
import java.util.Map;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.kmob.generator.template.CRUD;
import com.kmob.generator.util.AutoCreate;
import com.kmob.generator.util.DbUtils;
import com.kmob.generator.util.StrUtils;

/**
 * 启动测试类
 * 
 * @author verne
 */
public class GeneratorStartTest {

    public static void main(String[] args) throws Exception {
        run();
    }

    protected static void run() throws SQLException, Exception {
        // initOracle();
        initMysql();

        String module = "derror";
        String packagePath = "com.kmob.etcm.user.entity";
        String tables = "etc_pay_account";

        Map<String, CRUD> crudMap = null;
        if (StrUtils.isEmpty(tables) || "all".equalsIgnoreCase(tables)) {
            crudMap = DbUtils.getCRUDMap();
        } else {
            String[] tableArray = tables.split(",");
            crudMap = DbUtils.getCRUDMap(tableArray);
        }

        new AutoCreate().setTemplatePath("/template/project/operation/").setPackagePath(packagePath)
                .setModule(module).setCrudMap(crudMap).create();
    }

    public static void initMysql() {

        String jdbcUrl =
                "jdbc:mysql://192.168.1.70:3306/etc_user?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
        String user = "kmob";
        String password = "kmob0724(";
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

    public static void initOracle() {

        String jdbcUrl = "jdbc:oracle:thin:@192.168.1.70:1521:orcl";
        String user = "kop";
        String password = "kop237";
        String driverClass = "oracle.jdbc.driver.OracleDriver";

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
