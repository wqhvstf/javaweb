package com.smart4j.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 封装请求信息
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 下午2:22:05
 */
public class Request {
	/**
	 * 请求方法
	 */
	private String requestMethod;

	/**
	 * 请求路径
	 */
	private String requestPath;

	public Request(String requestMethod, String requestPath) {
		super();
		this.requestMethod = requestMethod;
		this.requestPath = requestPath;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
