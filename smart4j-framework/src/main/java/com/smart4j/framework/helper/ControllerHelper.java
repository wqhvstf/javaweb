package com.smart4j.framework.helper;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.smart4j.framework.annotation.Action;
import com.smart4j.framework.bean.Handler;
import com.smart4j.framework.bean.Request;
import com.smart4j.framework.util.CollectionUtil;

/**
 * 控制器助手类
 * 
 * @author qinghua.wu
 * @since
 * @date 2016年4月15日 下午2:28:17
 */
public final class ControllerHelper {
	/**
	 * 用于存放请求和处理器映射关系
	 */
	private static final Map<Request, Handler> ACTION_MAP = Maps.newHashMap();

	static {
		// 获取所有的Controller类
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if (CollectionUtil.isNotEmpty(controllerClassSet)) {
			for (Class<?> controllerClass : controllerClassSet) {
				Method[] methods = controllerClass.getDeclaredMethods();
				if (CollectionUtil.isNotEmpty(methods)) {
					for (Method method : methods) {
						if (method.isAnnotationPresent(Action.class)) {
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							// 验证URL规则
							if (mapping.matches("\\w+:/\\w*")) {
								String[] array = mapping.split(":");
								if (CollectionUtil.isNotEmpty(array) && array.length == 2) {
									// 获取请求方法与请求路径
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controllerClass, method);
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 获取Handler
	 * 
	 * @param requestMethod
	 * @param requestPath
	 * @return
	 */
	public static Handler getHandler(String requestMethod, String requestPath) {
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}

}
