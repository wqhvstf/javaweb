package com.smart4j.chapter2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建客户
 * 
 * @author DELL
 *
 */
@WebServlet("/customer_create")
public class CustomerCreateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4282142966505110023L;

	/**
	 * 进入创建客户界面
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO
	}

	/**
	 * 处理创建客户请求
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO
	}

}
