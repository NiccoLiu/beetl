package com.kmob.generator.beetl;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.core.resource.StringTemplateResourceLoader;

import com.kmob.generator.template.CRUD;
import com.kmob.generator.util.Config;
import com.kmob.generator.util.StrUtils;

/**
 * beetl 字符串模板
 * 
 *   
 * @author zhouzhixiang  
 * @date 2017年6月26日
 * @since 1.0
 */
public class GroupTemplateFactory {
    private static GroupTemplate stringTemplate = null;
    private static Map<String,GroupTemplate> GROUPTEMPLATE_MAP = new ConcurrentHashMap<String,GroupTemplate>();
    private static GroupTemplate groupTemplate;

    public static GroupTemplate getClasspath(String root) {
        try {
            if (GROUPTEMPLATE_MAP.get(root) == null) {
                FileResourceLoader resourceLoader = new FileResourceLoader(root);
                Configuration cfg = Configuration.defaultConfiguration();
                groupTemplate = new GroupTemplate(resourceLoader, cfg);
                // 模板配置和函数加载，区分别的模板~生成没冲突
                groupTemplate.registerFunctionPackage("flyfox", TemplateUtils.class);
                groupTemplate.registerFunctionPackage("strutils", StrUtils.class);

                cfg.setStatementStart(Config.getStr("beetl.statementStart"));
                cfg.setStatementEnd(Config.getStr("beetl.statementEnd"));
                cfg.setPlaceholderStart(Config.getStr("beetl.placeholderStart"));
                cfg.setPlaceholderEnd(Config.getStr("beetl.placeholderEnd"));
                GROUPTEMPLATE_MAP.put(root, groupTemplate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GROUPTEMPLATE_MAP.get(root);
    }

    public static void closeClasspath() {
        if (groupTemplate != null)
            groupTemplate.close();
    }

    public static GroupTemplate getString() throws IOException {
        try {
            if (stringTemplate == null) {
                StringTemplateResourceLoader stringLoader = new StringTemplateResourceLoader();
                Configuration cfg = Configuration.defaultConfiguration();
                stringTemplate = new GroupTemplate(stringLoader, cfg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringTemplate;
    }

    public static void closeString() {
        if (stringTemplate != null)
            stringTemplate.close();
    }

    public static String getFileName(CRUD crud, String name, String module) throws Exception {
        GroupTemplate stringTemplate = getString();
        Template t = stringTemplate.getTemplate(name);
        t.binding("crud", crud);
        t.binding("tableUpper",StrUtils.toUpperCaseFirst(crud.getTable().getClassName()));
        t.binding("tableLower",StrUtils.toLowerCaseFirst(crud.getTable().getClassName()));
        t.binding("key", crud.getUrlKey());
        t.binding("keyUpper", crud.getUrlKey().toUpperCase());
        t.binding("keyLower", crud.getUrlKey().toLowerCase());
        t.binding("keyLowerFirst", StrUtils.toLowerCaseFirst(crud.getUrlKey()));
        t.binding("keyUpperFirst", StrUtils.toUpperCaseFirst(crud.getUrlKey()));
        t.binding("module", module);
        t.binding("separator", "/");
        String fileName = t.render();
        return fileName;
    }
}
