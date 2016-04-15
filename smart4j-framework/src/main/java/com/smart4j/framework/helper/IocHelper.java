package com.smart4j.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import com.smart4j.framework.annotation.Inject;
import com.smart4j.framework.util.CollectionUtil;
import com.smart4j.framework.util.ReflectionUtil;

/**
 * 依赖注入助手类
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 下午2:01:40
 */
public final class IocHelper {
	static {
		// 获取所有的Bean类和bean实例之间的映射关系
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if (CollectionUtil.isNotEmpty(beanMap)) {
			for (Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();

				// 获取Bean定义的所有的成员变量
				Field[] beanFields = beanClass.getDeclaredFields();
				if (CollectionUtil.isNotEmpty(beanFields)) {
					for (Field field : beanFields) {
						if (field.isAnnotationPresent(Inject.class)) {
							Class<?> beanFieldType = field.getType();
							Object beanFieldInstance = beanMap.get(beanFieldType);
							if (beanFieldInstance != null) {
								ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}
}
