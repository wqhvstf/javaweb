package com.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json 工具类
 * 
 * @author qinghua.wu
 * @since
 * @date 2016年4月15日 下午4:07:50
 */
public final class JsonUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * 将POJO转为json
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> String toJson(T obj) {
		String json;
		try {
			json = OBJECT_MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			LOGGER.error("convert POJO to Json failure.", e);
			throw new RuntimeException(e);
		}
		return json;
	}

	/**
	 * 将Json转换为POJO
	 * 
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> type) {
		T pojo;
		try {
			pojo = OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			LOGGER.error("convert Json to POJO failure.", e);
			throw new RuntimeException(e);
		}
		return pojo;
	}
}
