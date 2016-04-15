package com.smart4j.framework.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 流操作工具类
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 下午4:02:54
 */
public final class StreamUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

	/**
	 * 从输入流中获取字符串
	 * 
	 * @param is
	 * @return
	 */
	public static String getString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			LOGGER.error("get string failure", e);
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
}
