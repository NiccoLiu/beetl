package com.kmob.generator.beetl;

import java.util.ArrayList;
import java.util.List;

import org.beetl.core.Template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kmob.generator.util.StrUtils;

/**
 * 模版工具
 *
 * @author verne
 */
public class TemplateUtils {

    public static final String KEY = "key";
    public static final String VALUE = "value";

    /**
     * 拼接radio json字符串 由于JSONOject排序问题，只能用数组解决
     * 
     * 
     * @param objs
     * @return
     */
    public static String getJson(String... objs) {
        // 没传或者是单数
        if (objs.length == 0 || objs.length % 2 != 0) {
            return null;
        }

        // fastjson为有序对象
        JSONArray array = new JSONArray();
        String key = "";
        String value = "";
        for (int i = 0; i < objs.length; i++) {
            if (i % 2 == 0) {
                key = objs[i];
            }
            if (i % 2 != 0) {
                value = objs[i];
                // 放入数组的json数据
                JSONObject tmp = new JSONObject();
                tmp.put(KEY, key);
                tmp.put(VALUE, value);
                array.add(tmp);
            }
        }

        // 放入JSON数据，Array有序
        return array.toString();
    }

    public static String value(String jsonData, String key) {
        if (StrUtils.isEmpty(jsonData)) {
            return null;
        }
        JSONArray array = (JSONArray) JSON.parse(jsonData);
        if (array == null) {
            return "";
        }
        for (int i = 0; i < array.size(); i++) {
            JSONObject tmp = array.getJSONObject(i);
            if (tmp.getString(KEY).equals(key)) {
                return tmp.getString(VALUE);
            }
        }
        return "";
    }

    /**
     * json返回list对象
     * 
     * @param jsonData
     * @param name
     * @return
     */
    public static List<TemplateUtilsObj> dataList(String jsonString) {
        JSONArray array = (JSONArray) JSON.parse(jsonString);
        if (array == null) {
            return null;
        }

        return getDataList(array);
    }

    /**
     * 获取模板绑定List
     * 
     * 
     * @param selected_value
     * @param array
     * @return
     */
    protected static List<TemplateUtilsObj> getDataList(JSONArray array) {
        List<TemplateUtilsObj> list = new ArrayList<TemplateUtilsObj>();
        TemplateUtilsObj obj;
        for (int i = 0; i < array.size(); i++) {
            JSONObject tmp = array.getJSONObject(i);
            obj = new TemplateUtilsObj();
            String key = tmp.getString(KEY);
            obj.setKey(key);
            obj.setValue(tmp.getString(VALUE));
            obj.setSelected("false");
            list.add(obj);
        }
        return list;
    }

    public static String getStr(String root, String template, Object... objs) {
        if (objs.length <= 0 || objs.length % 2 != 0) {
            return null;
        }

        String str = "";
        Template t = GroupTemplateFactory.getClasspath(root).getTemplate(template);
        Object name = "";
        Object value = "";
        for (int i = 0; i < objs.length; i++) {
            if (i % 2 == 0) {
                name = objs[i];
            }
            if (i % 2 != 0) {
                value = objs[i];
                t.binding(String.valueOf(name), value);
            }
        }

        str = t.render();

        return str;
    }

    /**
     * 测试
     * 
     */
    public static void main(String[] args) {
        String select = getJson("1", "男", "3", "未知", "2", "女");
        System.out.println(select);
        System.out.println(value(select, "2"));

        String radio = getJson("2", "女", "3", "未知", "1", "男");
        System.out.println(radio);
        System.out.println(value(radio, "1"));

        String checkbox = getJson("3", "未知", "1", "男", "2", "女");
        System.out.println(checkbox);
        System.out.println(value(checkbox, "3"));
    }

}
