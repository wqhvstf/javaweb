package com.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 * 
 * @author qinghua.wu
 * @since
 * @date 2016年4月15日 下午2:24:56
 */
public class Handler {
	/**
	 * Controller类
	 */
	private Class<?> controllerClass;

	/**
	 * Action方法
	 */
	private Method actionMethod;

	public Handler(Class<?> controllerClass, Method actionMethod) {
		super();
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Method getActionMethod() {
		return actionMethod;
	}
}
