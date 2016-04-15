package com.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Action 方法注解
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 下午1:13:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

	/**
	 * 请求类型和路径
	 * 
	 * @return
	 */
	String value();
}
