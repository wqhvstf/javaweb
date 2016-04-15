package com.smart4j.chapter2.helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smart4j.chapter2.util.CollectionUtil;
import com.smart4j.chapter2.util.PropsUtil;

public class DatabaseHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	private static final ThreadLocal<Connection> CONNECTION_HOLDER;
	private static final BasicDataSource DATA_SOURCE;

	private static final QueryRunner QUERY_RUNNER = new QueryRunner();
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;

	static {
		CONNECTION_HOLDER = new ThreadLocal<>();
		Properties conf = PropsUtil.loadProps("config.properties");
		DRIVER = conf.getProperty("jdbc.driver");
		URL = conf.getProperty("jdbc.url");
		USERNAME = conf.getProperty("jdbc.username");
		PASSWORD = conf.getProperty("jdbc.password");

		DATA_SOURCE = new BasicDataSource();
		DATA_SOURCE.setDriverClassName(DRIVER);
		DATA_SOURCE.setUrl(URL);
		DATA_SOURCE.setUsername(USERNAME);
		DATA_SOURCE.setPassword(PASSWORD);
	}

	/**
	 * 获取数据库连接
	 */
	public static Connection getConnection() {
		Connection conn = CONNECTION_HOLDER.get();
		if (conn == null) {
			try {
				conn = DATA_SOURCE.getConnection();
			} catch (SQLException e) {
				LOGGER.error("get connection failure.", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
		return conn;
	}

	/**
	 * 关闭数据库连接错误
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("close connection failure.", e);
			}
		}
	}

	/**
	 * 查询实体对象集合
	 * 
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
		List<T> entityList = null;
		Connection conn = getConnection();
		try {
			entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity list failure.", e);
			throw new RuntimeException();
		} finally {
			closeConnection(conn);
		}
		return entityList;
	}

	/**
	 * 查询实体对象
	 * 
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
		T entity = null;
		Connection conn = getConnection();
		try {
			entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity list failure.", e);
			throw new RuntimeException();
		} finally {
			closeConnection(conn);
		}
		return entity;
	}

	/**
	 * 执行查询语句
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
		List<Map<String, Object>> result = null;
		Connection connection = getConnection();
		try {
			result = QUERY_RUNNER.query(connection, sql, new MapListHandler(), params);
		} catch (SQLException e) {
			LOGGER.error("execute query failure.", e);
			throw new RuntimeException();
		}
		return result;
	}

	/**
	 * 修改对象
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int executeUpdate(String sql, Object... params) {
		int rows = 0;
		Connection connection = getConnection();
		try {
			rows = QUERY_RUNNER.update(connection, sql, params);
		} catch (SQLException e) {
			LOGGER.error("execute update failure.", e);
			throw new RuntimeException();
		}
		return rows;
	}

	/**
	 * 插入
	 * 
	 * @param entityClass
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not insert entity:fieldMap is empty.");
			return false;
		}

		String sql = "INSERT INTO " + getTableName(entityClass);
		StringBuilder columns = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append(", ");
			values.append("?");
		}

		columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
		values.replace(values.lastIndexOf(", "), values.length(), ")");

		sql += columns + " VALUES " + values;
		Object[] params = fieldMap.values().toArray();
		return executeUpdate(sql, params) == 1;
	}

	/**
	 * 更新
	 * 
	 * @param entityClass
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not update entity:fieldMap is empty.");
			return false;
		}

		String sql = "UPDATE " + getTableName(entityClass) + " SET ";
		StringBuilder columns = new StringBuilder();
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append("=?, ");
		}

		sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id=?";
		List<Object> paramList = new ArrayList<>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object[] params = fieldMap.values().toArray();
		return executeUpdate(sql, params) == 1;
	}

	/**
	 * 删除
	 * 
	 * @param entityClass
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
		String sql = "DELETE FROM  " + getTableName(entityClass) + " WHERE id =? ";
		return executeUpdate(sql, id) == 1;
	}

	private static String getTableName(Class<?> entityClass) {
		return entityClass.getSimpleName();
	}

}
