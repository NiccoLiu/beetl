package com.kmob.generator.template.model;

import com.kmob.generator.util.StrKit;


public class ModelAttr {

    /**
     * 字段key
     */
    private String key;
    /**
     * 字段名称
     */
    private String name;
    /**
     * java bean名称
     */
    private String javaKey;
    /**
     * 编辑类型
     */
    private FormType formType = FormType.INPUT;
    /**
     * Input类型
     */
    private InputType inputType = InputType.TEXT;
    /**
     * 编辑数据
     */
    private String formTypeData = "";
    /**
     * 验证方式
     */
    private String formTypeValid = "";
    /**
     * 是否可以为空
     */
    private boolean isNull;
    /**
     * 是否是数字
     */
    private boolean isNumber;

    /**
     * 数据展示
     * <p>
     * 学以致用，不嫌麻烦~！~
     * 8位，前四位保留;后三位，
     * 查询,展示列表，添加列表，编辑列表，查看列表
     * 1表示展示，0表示隐藏
     */
    private byte operate;

    public ModelAttr() {
        operate = (byte) 0xf; // 默认不在查询里
    }

    public boolean isSearch() {
        return (operate >> 4 & 0x1) == 1;
    }

    public boolean isList() {
        return (operate >> 3 & 0x1) == 1;
    }

    public boolean isAdd() {
        return (operate >> 2 & 0x1) == 1;
    }

    public boolean isEdit() {
        return (operate >> 1 & 0x1) == 1;
    }

    public boolean isView() {
        return (operate >> 0 & 0x1) == 1;
    }

    public ModelAttr addSearch() {
        operate = (byte) (operate | 0x10); // 10000
        return this;
    }

    public ModelAttr addList() {
        operate = (byte) (operate | 0x8); // 1000
        return this;
    }

    public ModelAttr addAdd() {
        operate = (byte) (operate | 0x4); // 100
        return this;
    }

    public ModelAttr addEdit() {
        operate = (byte) (operate | 0x2); // 10
        return this;
    }

    public ModelAttr addView() {
        operate = (byte) (operate | 0x1); // 1
        return this;
    }

    public ModelAttr removeSearch() {
        operate = (byte) (operate & 0xef); // 11101111
        return this;
    }

    public ModelAttr removeList() {
        operate = (byte) (operate & 0xf7); // 11110111
        return this;
    }

    public ModelAttr removeAdd() {
        operate = (byte) (operate & 0xfb); // 11111011
        return this;
    }

    public ModelAttr removeEdit() {
        operate = (byte) (operate & 0xfd); // 11111101
        return this;
    }

    public ModelAttr removeView() {
        operate = (byte) (operate & 0xfe); // 11111110
        return this;
    }

    public String getKey() {
        return key;
    }

    public ModelAttr setKey(String key) {
        this.key = key;
        this.javaKey = StrKit.firstCharToLowerCase(StrKit.makeAllWordFirstLetterUpperCase(StrKit.toUnderscoreName(key)));
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelAttr setName(String name) {
        this.name = name;
        return this;
    }

    public String getJavaKey() {
        return this.javaKey;
    }

    public byte getOperate() {
        return operate;
    }

    public ModelAttr setOperate(byte operate) {
        this.operate = operate;
        return this;
    }

    public FormType getFormType() {
        return formType;
    }

    public ModelAttr setFormType(FormType formType) {
        this.formType = formType;
        return this;
    }

    public String getFormTypeData() {
        return formTypeData;
    }

    public ModelAttr setFormTypeData(String formTypeData) {
        this.formTypeData = formTypeData;
        return this;
    }

    public String getFormTypeValid() {
        return formTypeValid;
    }

    public ModelAttr setFormTypeValid(String formTypeValid) {
        this.formTypeValid = formTypeValid;
        return this;
    }

    public InputType getInputType() {
        return inputType;
    }

    public ModelAttr setInputType(InputType inputType) {
        this.inputType = inputType;
        return this;
    }

    public boolean getIsNull() {
        return isNull;
    }

    public boolean isNull() {
        return isNull;
    }

    public ModelAttr setNull(boolean isNull) {
        this.isNull = isNull;
        return this;
    }

    public boolean getIsNumber() {
        return isNumber;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public ModelAttr setNumber(boolean isNumber) {
        this.isNumber = isNumber;
        return this;
    }

    @Override
    public String toString() {
        return "[" + key + ":" + name + "]";
    }

}