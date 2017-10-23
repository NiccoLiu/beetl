
package com.kmob.generator.util;

/**
 * StrKit.
 */
public class StrKit {
	
	/**
	 * 首字母变小写
	 */
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
	
	/**
	 * 首字母变大写
	 */
	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
	
	/**
	 * 字符串为 null 或者为  "" 时返回 true
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim()) ? true : false;
	}
	
	/**
	 * 字符串不为 null 而且不为  "" 时返回 true
	 */
	public static boolean notBlank(String str) {
		return str == null || "".equals(str.trim()) ? false : true;
	}
	
	public static boolean notBlank(String... strings) {
		if (strings == null) {
            return false;
        }
		for (String str : strings) {
            if (str == null || "".equals(str.trim())) {
                return false;
            }
        }
		return true;
	}
	
	public static boolean notNull(Object... paras) {
		if (paras == null) {
            return false;
        }
		for (Object obj : paras) {
            if (obj == null) {
                return false;
            }
        }
		return true;
	}
	
	  /**
     * Convert a name in camelCase to an underscored name in lower case.
     * Any upper case letters are converted to lower case with a preceding underscore.
     * @param name the string containing original name
     * @return the converted name
     */
    public static String toUnderscoreName(String name) {
        if(name == null) {
            return null;
        }

        String filteredName = name;
        if(filteredName.indexOf("_") >= 0 && filteredName.equals(filteredName.toUpperCase())) {
            filteredName = filteredName.toLowerCase();
        }
        if(filteredName.indexOf("_") == -1 && filteredName.equals(filteredName.toUpperCase())) {
            filteredName = filteredName.toLowerCase();
        }

        StringBuffer result = new StringBuffer();
        if (filteredName != null && filteredName.length() > 0) {
            result.append(filteredName.substring(0, 1).toLowerCase());
            for (int i = 1; i < filteredName.length(); i++) {
                String preChart = filteredName.substring(i - 1, i);
                String c = filteredName.substring(i, i + 1);
                if(c.equals("_")) {
                    result.append("_");
                    continue;
                }
                if(preChart.equals("_")){
                    result.append(c.toLowerCase());
                    continue;
                }
                if(c.matches("\\d")) {
                    result.append(c);
                }else if (c.equals(c.toUpperCase())) {
                    result.append("_");
                    result.append(c.toLowerCase());
                }
                else {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }
    

    public static String makeAllWordFirstLetterUpperCase(String sqlName) {
        String[] strs = sqlName.toLowerCase().split("_");
        String result = "";
        String preStr = "";
        for(int i = 0; i < strs.length; i++) {
            if(preStr.length() == 1) {
                result += strs[i];
            }else {
                result += firstCharToUpperCase(strs[i]);
            }
            preStr = strs[i];
        }
        return result;
    }
    
    
    public static boolean contains(String str,String... keywords) {
        if(str == null) {
            return false;
        }
        if(keywords == null) {
            throw new IllegalArgumentException("'keywords' must be not null");
        }

        for(String keyword : keywords) {
            if(str.contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    

    public static String getJavaClassSimpleName(String clazz) {
        if(clazz == null) {
            return null;
        }
        if(clazz.lastIndexOf(".") >= 0) {
            return clazz.substring(clazz.lastIndexOf(".")+1);
        }
        return clazz;
    }
}




