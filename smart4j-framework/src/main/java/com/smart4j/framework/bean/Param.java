package com.smart4j.framework.bean;

import java.util.Map;

import com.smart4j.framework.util.CastUtil;

/**
 * 请求参数对象
 * 
 * @author qinghua.wu
 * @since
 * @date 2016年4月15日 下午2:47:11
 */
public class Param {
	private Map<String, String> paramObj;

	public Param(Map<String, String> paramObj) {
		super();
		this.paramObj = paramObj;
	}

	/**
	 * 根据参数名获取long型的参数值
	 * 
	 * @param name
	 * @return
	 */
	public long getLong(String name) {
		return CastUtil.castLong(paramObj.get(name));
	}

	/**
	 * 获取所有的字段信息
	 * 
	 * @return
	 */
	public Map<String, String> getMap() {
		return paramObj;
	}

}
