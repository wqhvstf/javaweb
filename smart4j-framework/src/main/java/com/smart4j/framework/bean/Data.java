package com.smart4j.framework.bean;

/**
 * 返回数据对象
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 下午2:57:10
 */
public class Data {

	/**
	 * 数据模型
	 */
	private Object model;

	public Data(Object model) {
		this.model = model;
	}

	public Object getModel() {
		return model;
	}
}
