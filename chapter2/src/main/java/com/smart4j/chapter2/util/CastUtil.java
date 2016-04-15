package com.smart4j.chapter2.util;

/**
 * 转型操作工具类
 * 
 * @author DELL
 *
 */
public final class CastUtil {

	/**
	 * 转换为String
	 * 
	 * @param obj
	 * @return
	 */
	public static String castString(Object obj) {
		return castString(obj, "");
	}

	public static String castString(Object obj, String defaultValue) {
		return obj == null ? defaultValue : String.valueOf(obj);
	}

	/**
	 * 转换Double
	 * 
	 * @param obj
	 * @return
	 */
	public static Double castDouble(Object obj) {
		return castDouble(obj, 0);
	}

	public static Double castDouble(Object obj, double defaultValue) {
		double doubleValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					doubleValue = Double.parseDouble(strValue);
				} catch (NumberFormatException e) {
					doubleValue = defaultValue;
				}
			}
		}
		return doubleValue;
	}

	/**
	 * 转换为Long
	 * 
	 * @param obj
	 * @return
	 */
	public static Long castLong(Object obj) {
		return castLong(obj, 0);
	}

	public static Long castLong(Object obj, long defaultValue) {
		long longValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					longValue = Long.parseLong(strValue);
				} catch (NumberFormatException e) {
					longValue = defaultValue;
				}
			}
		}
		return longValue;
	}

	/**
	 * 转换为Int
	 * 
	 * @param obj
	 * @return
	 */
	public static Integer castInt(Object obj) {
		return castInt(obj, 0);
	}

	public static Integer castInt(Object obj, int defaultValue) {
		int intValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					intValue = Integer.parseInt(strValue);
				} catch (NumberFormatException e) {
					intValue = defaultValue;
				}
			}
		}
		return intValue;
	}

	/**
	 * 转换为Boolean
	 * 
	 * @param obj
	 * @return
	 */
	public static Boolean castBoolean(Object obj) {
		return castBoolean(obj, false);
	}

	public static Boolean castBoolean(Object obj, boolean defaultValue) {
		boolean booleanValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					booleanValue = Boolean.parseBoolean(strValue);
				} catch (NumberFormatException e) {
					booleanValue = defaultValue;
				}
			}
		}
		return booleanValue;
	}
}
