package com.kmob.generator.template;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.kmob.generator.database.Table;
import com.kmob.generator.template.model.FormType;
import com.kmob.generator.template.model.ModelAttr;

/**
 * 基本crud封装
 * 
 * @author verne
 */
public class CRUD {

    private Table table;
    /**
     * 主键
     */
    private String primaryKey;
    /**
     * url key 关键字
     */
    private String urlKey;
    /**
     * 名称
     */
    private String name;

    @SuppressWarnings("unused")
    private String timestamp;

    private Map<String, String> properties = new LinkedHashMap<String, String>();

    private final Map<String, ModelAttr> map = new LinkedHashMap<String, ModelAttr>();
    private final Map<String, ModelAttr> searchMap = new LinkedHashMap<String, ModelAttr>();
    private final Map<String, ModelAttr> listMap = new LinkedHashMap<String, ModelAttr>();
    private final Map<String, ModelAttr> addMap = new LinkedHashMap<String, ModelAttr>();
    private final Map<String, ModelAttr> editMap = new LinkedHashMap<String, ModelAttr>();
    private final Map<String, ModelAttr> viewMap = new LinkedHashMap<String, ModelAttr>();
    /**
     * 获取时，存入处理的数据
     */
    private final Map<String, String> selectMap = new HashMap<String, String>();

    public void setAttr(ModelAttr... attr) {
        for (ModelAttr modelAttr : attr) {
            map.put(modelAttr.getKey(), modelAttr);
            if (modelAttr.isSearch()) {
                searchMap.put(modelAttr.getKey(), modelAttr);
            }
            if (modelAttr.isList()) {
                listMap.put(modelAttr.getKey(), modelAttr);
            }
            if (modelAttr.isAdd()) {
                addMap.put(modelAttr.getKey(), modelAttr);
            }
            if (modelAttr.isEdit()) {
                editMap.put(modelAttr.getKey(), modelAttr);
            }
            if (modelAttr.isView()) {
                viewMap.put(modelAttr.getKey(), modelAttr);
            }
            if (modelAttr.getFormType() == FormType.DICT
                    || modelAttr.getFormType() == FormType.SELECT) {
                selectMap.put(modelAttr.getKey(), null);
            }
        }
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, ModelAttr> getMap() {
        return map;
    }

    public Map<String, ModelAttr> getSearchMap() {
        return searchMap;
    }

    public Map<String, ModelAttr> getListMap() {
        return listMap;
    }

    public Map<String, ModelAttr> getAddMap() {
        return addMap;
    }

    public Map<String, ModelAttr> getEditMap() {
        return editMap;
    }

    public Map<String, ModelAttr> getViewMap() {
        return viewMap;
    }

    public Map<String, String> getSelectMap() {
        return selectMap;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getTimestamp() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
