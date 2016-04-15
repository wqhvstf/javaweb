package com.smart4j.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编码解码操作
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 下午3:57:11
 */
public final class CodecUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

	/**
	 * 将URL编码
	 * 
	 * @param source
	 * @return
	 */
	public static String encodeURL(String source) {
		String target;
		try {
			target = URLEncoder.encode(source, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("encode url failure", e);
			throw new RuntimeException(e);
		}
		return target;
	}

	/**
	 * 将URL解码
	 * 
	 * @param source
	 * @return
	 */
	public static String decodeURL(String source) {
		String target;
		try {
			target = URLEncoder.encode(source, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("decode url failure", e);
			throw new RuntimeException(e);
		}
		return target;
	}
}
