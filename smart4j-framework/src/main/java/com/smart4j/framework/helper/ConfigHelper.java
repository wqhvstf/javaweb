package com.smart4j.framework.helper;

import java.util.Properties;

import com.smart4j.framework.ConfigConstant;
import com.smart4j.framework.util.PropsUtil;

/**
 * 属性文件助手
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 上午11:37:34
 */
public final class ConfigHelper {
	private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

	/**
	 * 获取JDBC驱动
	 * 
	 * @since 1.0.0
	 */
	public static String getJdbcDriver() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
	}

	/**
	 * 获取JDBC URL
	 * 
	 * @since 1.0.0
	 */
	public static String getJdbcUrl() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
	}

	/**
	 * 获取JDBC 用户名
	 * 
	 * @since 1.0.0
	 */
	public static String getJdbcUsername() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
	}

	/**
	 * 获取JDBC 密码
	 * 
	 * @since 1.0.0
	 */
	public static String getJdbcPassword() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
	}

	/**
	 * 获取应用基础包名
	 * 
	 * @since 1.0.0
	 */
	public static String getAppBasePackage() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
	}

	/**
	 * 获取应用JSP路径
	 * 
	 * @since 1.0.0
	 */
	public static String getAppJspPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
	}

	/**
	 * 获取应用静态资源路径
	 * 
	 * @since 1.0.0
	 */
	public static String getAppAssertPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSERT_PATH, "/assert/");
	}
}
