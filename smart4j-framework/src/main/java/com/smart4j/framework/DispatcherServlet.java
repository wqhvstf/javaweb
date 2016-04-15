package com.smart4j.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.smart4j.framework.bean.Data;
import com.smart4j.framework.bean.Handler;
import com.smart4j.framework.bean.Param;
import com.smart4j.framework.bean.View;
import com.smart4j.framework.helper.BeanHelper;
import com.smart4j.framework.helper.ConfigHelper;
import com.smart4j.framework.helper.ControllerHelper;
import com.smart4j.framework.util.CodecUtil;
import com.smart4j.framework.util.JsonUtil;
import com.smart4j.framework.util.ReflectionUtil;
import com.smart4j.framework.util.StreamUtil;
import com.smart4j.framework.util.StringUtil;

/**
 * 请求转发器
 * 
 * @author qinghua.wu
 * @since 1.0.0
 * @date 2016年4月15日 下午3:02:35
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6230301028741773713L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取请求方法和请求方法
		String requestMethod = req.getMethod().toLowerCase();
		String requestPath = req.getPathInfo();

		// 获取Action处理器
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if (handler != null) {
			// 获取Controller类及其Bean实例
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);

			// 创建请求对象
			Map<String, String> paramMap = Maps.newHashMap();
			Enumeration<String> paramNames = req.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				String paramValue = req.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}

			String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));

			Param param = null;
			if (StringUtil.isNotEmpty(body)) {
				paramMap = StringUtil.splitString(body, "&", "=");
				param = new Param(paramMap);
			}

			// 调用Action的方法
			Method actionMethod = handler.getActionMethod();
			Object result = null;
			if (param == null) {
				result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
			} else {
				result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
			}

			// 处理Action方法的返回值
			if (result instanceof View) {
				// 返回jsp界面
				View view = (View) result;
				String path = view.getPath();
				if (StringUtil.isNotEmpty(path)) {
					if (path.startsWith("/")) {
						resp.sendRedirect(req.getContextPath() + path);
					} else {
						Map<String, Object> model = view.getModel();
						for (Entry<String, Object> entry : model.entrySet()) {
							req.setAttribute(entry.getKey(), entry.getValue());
						}
						req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
					}
				}
			} else if (result instanceof Data) {
				// 返回JSON数据
				Data data = (Data) result;
				Object model = data.getModel();
				if (model != null) {
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");
					PrintWriter writer = resp.getWriter();
					String json = JsonUtil.toJson(model);
					writer.write(json);
					writer.flush();
					writer.close();
				}
			}
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 初始化相关的Helper类
		HelperLoad.init();
		// 获取ServletContext对象（用于注册Servlet）
		ServletContext servletContext = config.getServletContext();
		// 注册处理JSP的Servlet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

		// 注册处理静态资源的Servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssertPath() + "*");
	}

}
