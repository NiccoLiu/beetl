package com.kmob.generator.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.kmob.generator.beetl.TemplateUtils;
import com.kmob.generator.template.model.FormType;
import com.kmob.generator.template.model.ModelAttr;
import com.kmob.generator.util.StrUtils;

/**
 * crud生成工厂
 * 
 * @author verne
 */
public class CRUDFactory {

    private static final Log logger = Log.getLog(CRUDFactory.class);
    private static final CRUDFactory factory = new CRUDFactory();
    final Map<String, CRUD> crudMap = new HashMap<String, CRUD>();

    private CRUDFactory() {}

    public static CRUDFactory instance() {
        return factory;
    }

    /**
     * 解析XML
     * 
     */
    public void init() {
        CRUDFactoryHelper.parse();
        logger.debug("CRUDFactory init finish ...");
    }

    /**
     * 生成XML
     * 
     */
    public void create() {
        CRUDFactoryHelper.create();
        logger.debug("CRUDFactory create finish ...");
    }

    /**
     * 添加CRUD对象
     * 
     * @param clsName
     * @param crud
     * @return
     */
    public CRUDFactory add(String clsName, CRUD crud) {
        if (crudMap.containsKey(clsName)) {
            logger.warn("CRUD name（" + clsName + "）已存在！");
            throw new RuntimeException("CRUD name（" + clsName + "）已存在！");
        }
        crudMap.put(clsName, crud);
        return this;
    }

    /**
     * 添加CRUD对象
     * 
     * @param cls
     * @param crud
     * @return
     */
    public CRUDFactory add(Class<?> cls, CRUD crud) {
        return add(cls.getName(), crud);
    }

    /**
     * 获取CRUD对象
     * 
     * 
     * @param cls
     * @return
     */
    public CRUD get(Class<?> cls) {
        CRUD crud = crudMap.get(cls.getName());
        if (crud == null) {
            logger.warn("CRUD clsName（" + cls.getName() + "）获取为NULL！");
        }
        // 拼接数据
        dataHandle(crud);

        return crud;
    }

    private void dataHandle(CRUD crud) {
        if (crud == null) {
            return;
        }

        Map<String, String> select = crud.getSelectMap();
        String selectData = null;
        for (Map.Entry<String, String> entry : select.entrySet()) {
            ModelAttr modelAttr = crud.getMap().get(entry.getKey());
            if (modelAttr.getFormType() == FormType.SELECT) {
                Object obj = JSON.parse(modelAttr.getFormTypeData());
                if (obj instanceof JSONObject) {
                    // sql数据
                    JSONObject json = (JSONObject) obj;
                    String sql = json.getString("sql");
                    String key = json.getString("key");
                    String value = json.getString("value");
                    selectData = getJsonData(sql, key, value);
                } else if (obj instanceof JSONArray) {
                    // 自定义数据
                    selectData = modelAttr.getFormTypeData();
                }
            } else if (modelAttr.getFormType() == FormType.DICT) {
                String sql =
                        "select detail_id as dict_key,detail_name as dict_value from sys_dict_detail ";
                sql += " where dict_type = '" + modelAttr.getFormTypeData() + "'";
                String key = "dict_key";
                String value = "dict_value";
                selectData = getJsonData(sql, key, value);
            }

            select.put(entry.getKey(), selectData);

        }
    }

    private String getJsonData(String sql, String key, String value) {
        if (StrUtils.isEmpty(sql)) {
            return "";
        }

        List<Record> list = Db.find(sql);
        if (list == null || list.size() == 0) {
            return "";
        }

        JSONArray array = new JSONArray();
        for (Record record : list) {
            JSONObject tmp = new JSONObject();
            tmp.put(TemplateUtils.KEY, record.get(key));
            tmp.put(TemplateUtils.VALUE, record.get(value));
            array.add(tmp);
        }
        return array.toJSONString();
    }

    /**
     * 清除CRUD
     * 
     * @return
     */
    public CRUDFactory clear() {
        crudMap.clear();
        return this;
    }

}
