package com.smart4j.framework;

/**
 * 提供相关配置项常量
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 上午11:30:53
 */
public interface ConfigConstant {
	
	String CONFIG_FILE = "smart4j.properties";

	String JDBC_DRIVER = "smart4j.framework.jdbc.driver";

	String JDBC_URL = "smart4j.framework.jdbc.url";

	String JDBC_USERNAME = "smart4j.framework.jdbc.username";

	String JDBC_PASSWORD = "smart4j.framework.jdbc.password";

	String APP_BASE_PACKAGE = "smart4j.framework.app.base_package";

	String APP_JSP_PATH = "smart4j.framework.app.jsp_path";

	String APP_ASSERT_PATH = "smart4j.framework.app.assert_path";
}
