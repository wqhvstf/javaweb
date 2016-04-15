package com.smart4j.framework.helper;

import java.util.Set;

import com.google.common.collect.Sets;
import com.smart4j.framework.annotation.Controller;
import com.smart4j.framework.annotation.Service;
import com.smart4j.framework.util.ClassUtil;

/**
 * 类操作助手类
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 下午1:28:30
 */
public final class ClassHelper {
	/**
	 * 定义类集合（用于存放加载的类）
	 */
	private static final Set<Class<?>> CLASS_SET;

	static {
		String appBasePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(appBasePackage);
	}

	/**
	 * 获取应用包名下所有的类
	 * 
	 * @return
	 */
	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}

	/**
	 * 获取所有的Service类
	 * 
	 * @return
	 */
	public static Set<Class<?>> getServiceClassSet() {
		Set<Class<?>> classSet = Sets.newHashSet();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Service.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}

	/**
	 * 获取所有的Controller类
	 * 
	 * @return
	 */
	public static Set<Class<?>> getControllerClassSet() {
		Set<Class<?>> classSet = Sets.newHashSet();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Controller.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}

	/**
	 * 获取所有的Bean类(包括Service、Controller)
	 * 
	 * @return
	 */
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> classSet = Sets.newHashSet();
		classSet.addAll(getServiceClassSet());
		classSet.addAll(getControllerClassSet());
		return classSet;
	}
}
