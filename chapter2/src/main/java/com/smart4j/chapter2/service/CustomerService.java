package com.smart4j.chapter2.service;

import java.util.List;
import java.util.Map;

import com.smart4j.chapter2.helper.DatabaseHelper;
import com.smart4j.chapter2.model.Customer;

/**
 * 提供客户数据服务
 * 
 * @author DELL
 *
 */
public class CustomerService {

	/**
	 * 获取客户列表
	 * 
	 * @param keyWord
	 * @return
	 */
	public List<Customer> getCustomerList() {
		String sql = "SELECT * FROM customer";
		return DatabaseHelper.queryEntityList(Customer.class, sql);
	}

	/**
	 * 获取客户列表跟据关键字
	 * 
	 * @param keyWord
	 * @return
	 */
	public List<Customer> getCustomerList(String keyWord) {
		// TODO
		return null;
	}

	/**
	 * 获取客户
	 * 
	 * @param id
	 * @return
	 */
	public Customer getCustomer(long id) {
		String sql = "select * from customer where id=" + id;
		return DatabaseHelper.queryEntity(Customer.class, sql);
	}

	/**
	 * 创建客户
	 * 
	 * @param fieldMap
	 * @return
	 */
	public boolean createCustomer(Map<String, Object> fieldMap) {
		return DatabaseHelper.insertEntity(Customer.class, fieldMap);
	}

	/**
	 * 更新客户
	 * 
	 * @param id
	 * @param fieldMap
	 * @return
	 */
	public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
		return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
	}

	/**
	 * 删除客户
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteCustomer(long id) {
		return DatabaseHelper.deleteEntity(Customer.class, id);
	}
}
