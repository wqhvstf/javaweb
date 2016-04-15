package com.smart4j.framework.bean;

import java.util.Map;

/**
 * 返回视图对象
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 下午2:53:49
 */
public class View {
	/**
	 * 视图路径
	 */
	private String path;

	/**
	 * 模型数据
	 */
	private Map<String, Object> model;

	public View(String path) {
		super();
		this.path = path;
	}

	public View addModel(String key, Object value) {
		this.model.put(key, value);
		return this;
	}

	public String getPath() {
		return path;
	}

	public Map<String, Object> getModel() {
		return model;
	}

}
