package com.smart4j.framework;

import com.smart4j.framework.helper.BeanHelper;
import com.smart4j.framework.helper.ClassHelper;
import com.smart4j.framework.helper.ControllerHelper;
import com.smart4j.framework.helper.IocHelper;
import com.smart4j.framework.util.ClassUtil;

/**
 * 框架加载响应的类
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 下午2:41:07
 */
public final class HelperLoad {
	public static void init() {
		Class<?>[] classList = { ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class };
		for (Class<?> cls : classList) {
			ClassUtil.loadClass(cls.getName(), true);
		}
	}
}
