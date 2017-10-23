package com.kmob.generator.database;

import java.sql.Types;
import java.util.HashMap;

/**
 * 
 * 
 *   
 * @author zhouzhixiang  
 * @date 2017年6月26日
 * @since 1.0
 */
public class DbDataTypesUtils {

	private final static HashMap<Integer, Object> TYPE_MAP_OBJECT = new HashMap<Integer, Object>();
	private final static HashMap<Integer, Object> TYPE_MAP = new HashMap<Integer, Object>();

	public static boolean isFloatNumber(String javaType) {
		if (javaType.endsWith("Float") || javaType.endsWith("Double") || javaType.endsWith("BigDecimal")
				|| javaType.endsWith("BigInteger")) {
			return true;
		}
		if (javaType.endsWith("float") || javaType.endsWith("double") || javaType.endsWith("BigDecimal")
				|| javaType.endsWith("BigInteger")) {
			return true;
		}
		return false;
	}

	public static boolean isIntegerNumber(String javaType) {
		if (javaType.endsWith("Long") || javaType.endsWith("Integer") || javaType.endsWith("Short")
				|| javaType.endsWith("Byte")) {
			return true;
		}
		if (javaType.endsWith("long") || javaType.endsWith("int") || javaType.endsWith("short")
				|| javaType.endsWith("byte")) {
			return true;
		}
		return false;
	}

	public static boolean isDate(String javaType) {
		if (javaType.endsWith("Date") || javaType.endsWith("Timestamp") || javaType.endsWith("Time")) {
			return true;
		}
		return false;
	}

	public static boolean isTimeStamp(String javaType) {
		if (javaType.endsWith("Timestamp")) {
			return true;
		}
		return false;
	}

	public static boolean isString(String javaType) {
		if (javaType.endsWith("String")) {
			return true;
		}
		return false;
	}

	public static String getPreferredJavaTypeObject(int sqlType, int size, int decimalDigits) {
		if ((sqlType == Types.DECIMAL || sqlType == Types.NUMERIC) && decimalDigits == 0) {
			if (size == 1) {
				return "java.lang.Integer";
			} else if (size < 3) {
				return "java.lang.Byte";
			} else if (size < 5) {
				return "java.lang.Short";
			} else if (size < 10) {
				return "java.lang.Integer";
			} else if (size < 19) {
				return "java.lang.Long";
			} else {
				return "java.math.BigDecimal";
			}
		}
		String result = (String) TYPE_MAP_OBJECT.get(sqlType);
		if (result == null) {
			result = "java.lang.Object";
		}
		return result;
	}

	public static String getPreferredJavaType(int sqlType, int size, int decimalDigits) {
		if ((sqlType == Types.DECIMAL || sqlType == Types.NUMERIC) && decimalDigits == 0) {
			if (size <= 10) {
				return "Integer";
			} else if (size <= 20) {
				return "Long";
			} else {
				return "BigDecimal";
			}
		}
		String result = (String) TYPE_MAP.get(sqlType);
		if (result == null) {
			result = "Object";
		}
		return result;
	}

	static {
		TYPE_MAP.put(Types.TINYINT, "Integer");
		TYPE_MAP.put(Types.SMALLINT, "Integer");
		TYPE_MAP.put(Types.INTEGER, "Integer");
		TYPE_MAP.put(Types.BIGINT, "Long");
		TYPE_MAP.put(Types.REAL, "Float");
		TYPE_MAP.put(Types.FLOAT, "Double");
		TYPE_MAP.put(Types.DOUBLE, "Double");
		TYPE_MAP.put(Types.DECIMAL, "BigDecimal");
		TYPE_MAP.put(Types.NUMERIC, "BigDecimal");
		TYPE_MAP.put(Types.BIT, "Integer");
		TYPE_MAP.put(Types.BOOLEAN, "Boolean");
		TYPE_MAP.put(Types.CHAR, "String");
		TYPE_MAP.put(Types.VARCHAR, "String");
		TYPE_MAP.put(Types.LONGVARCHAR, "String");
		TYPE_MAP.put(Types.BINARY, "byte[]");
		TYPE_MAP.put(Types.VARBINARY, "byte[]");
		TYPE_MAP.put(Types.LONGVARBINARY, "byte[]");
		TYPE_MAP.put(Types.DATE, "Date");
		TYPE_MAP.put(Types.TIME, "Date");
		TYPE_MAP.put(Types.TIMESTAMP, "Date");
		TYPE_MAP.put(Types.CLOB, "Clob");
		TYPE_MAP.put(Types.BLOB, "Blob");
		TYPE_MAP.put(Types.ARRAY, "Array");
		TYPE_MAP.put(Types.REF, "Ref");
		TYPE_MAP.put(Types.STRUCT, "Object");
		TYPE_MAP.put(Types.JAVA_OBJECT, "Object");


		TYPE_MAP_OBJECT.put(Types.TINYINT, "java.lang.Byte");
		TYPE_MAP_OBJECT.put(Types.SMALLINT, "java.lang.Short");
		TYPE_MAP_OBJECT.put(Types.INTEGER, "java.lang.Integer");
		TYPE_MAP_OBJECT.put(Types.BIGINT, "java.lang.Long");
		TYPE_MAP_OBJECT.put(Types.REAL, "java.lang.Float");
		TYPE_MAP_OBJECT.put(Types.FLOAT, "java.lang.Double");
		TYPE_MAP_OBJECT.put(Types.DOUBLE, "java.lang.Double");
		TYPE_MAP_OBJECT.put(Types.DECIMAL, "java.math.BigDecimal");
		TYPE_MAP_OBJECT.put(Types.NUMERIC, "java.math.BigDecimal");
		TYPE_MAP_OBJECT.put(Types.BIT, "java.lang.Boolean");
		TYPE_MAP_OBJECT.put(Types.BOOLEAN, "java.lang.Boolean");
		TYPE_MAP_OBJECT.put(Types.CHAR, "java.lang.String");
		TYPE_MAP_OBJECT.put(Types.VARCHAR, "java.lang.String");
		TYPE_MAP_OBJECT.put(Types.LONGVARCHAR, "java.lang.String");
		TYPE_MAP_OBJECT.put(Types.BINARY, "byte[]");
		TYPE_MAP_OBJECT.put(Types.VARBINARY, "byte[]");
		TYPE_MAP_OBJECT.put(Types.LONGVARBINARY, "byte[]");
		TYPE_MAP_OBJECT.put(Types.DATE, "java.util.Date");
		TYPE_MAP_OBJECT.put(Types.TIME, "java.util.Date");
		TYPE_MAP_OBJECT.put(Types.TIMESTAMP, "java.util.Date");
		TYPE_MAP_OBJECT.put(Types.TIME, "java.util.Date");
		TYPE_MAP_OBJECT.put(Types.TIMESTAMP, "java.util.Date");
		TYPE_MAP_OBJECT.put(Types.CLOB, "java.sql.Clob");
		TYPE_MAP_OBJECT.put(Types.BLOB, "java.sql.Blob");
		TYPE_MAP_OBJECT.put(Types.ARRAY, "java.sql.Array");
		TYPE_MAP_OBJECT.put(Types.REF, "java.sql.Ref");
		TYPE_MAP_OBJECT.put(Types.STRUCT, "java.lang.Object");
		TYPE_MAP_OBJECT.put(Types.JAVA_OBJECT, "java.lang.Object");
	}

}
