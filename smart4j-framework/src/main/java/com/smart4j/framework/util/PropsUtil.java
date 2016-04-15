package com.smart4j.framework.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性工具类
 * 
 * @author DELL
 *
 */
public final class PropsUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

	/**
	 * 加载属性文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties loadProps(String fileName) {
		Properties props = null;
		InputStream is = null;

		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if (is == null) {
				throw new FileNotFoundException(fileName + " file is not found");
			}
			props = new Properties();
			props.load(is);
		} catch (IOException e) {
			LOGGER.error("load properties file failure", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					LOGGER.error("close input stream failure", e);
				}
			}
		}
		return props;
	}

	/**
	 * 获取字符型属性(默认值为空字符串)
	 * 
	 * @param pops
	 * @param key
	 * @return
	 */
	public static String getString(Properties pops, String key) {
		return getString(pops, key, "");
	}

	/**
	 * 获取字符类型（可以指定默认值）
	 * 
	 * @param pops
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(Properties pops, String key, String defaultValue) {
		String value = defaultValue;
		if (pops.contains(key)) {
			value = pops.getProperty(key);
		}
		return value;
	}

	/**
	 * 获取INT属性(默认值为0)
	 * 
	 * @param pops
	 * @param key
	 * @return
	 */
	public static int getInt(Properties pops, String key) {
		return getInt(pops, key, 0);
	}

	/**
	 * 获取字符类型（可以指定默认值）
	 * 
	 * @param pops
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(Properties pops, String key, int defaultValue) {
		int value = defaultValue;
		if (pops.contains(key)) {
			value = CastUtil.castInt(pops.getProperty(key));
		}
		return value;
	}

	/**
	 * 获取boolean属性(默认值为false)
	 * 
	 * @param pops
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Properties pops, String key) {
		return getBoolean(pops, key, false);
	}

	/**
	 * 获取boolean（可以指定默认值）
	 * 
	 * @param pops
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(Properties pops, String key, boolean defaultValue) {
		boolean value = defaultValue;
		if (pops.contains(key)) {
			value = CastUtil.castBoolean(pops.getProperty(key));
		}
		return value;
	}

}
