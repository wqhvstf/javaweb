package com.smart4j.framework.helper;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.smart4j.framework.util.ReflectionUtil;

/**
 * Bean操作助手类
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 下午1:49:03
 */
public final class BeanHelper {
	/**
	 * 定义Bean映射（用户存放bean类和bean实例的映射关系）
	 */
	private static final Map<Class<?>, Object> BEAN_MAP = Maps.newHashMap();

	static {
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for (Class<?> cls : beanClassSet) {
			Object obj = ReflectionUtil.newInstance(cls);
			BEAN_MAP.put(cls, obj);
		}
	}

	/**
	 * 获取BeanMap
	 * 
	 * @return
	 */
	public static Map<Class<?>, Object> getBeanMap() {
		return BEAN_MAP;
	}

	/**
	 * 获取Bean实例
	 * 
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls) {
		if (!BEAN_MAP.containsKey(cls)) {
			throw new RuntimeException("can not get bean by class:" + cls);
		}

		return (T) BEAN_MAP.get(cls);
	}

}
