package com.smart4j.chapter2.service;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.smart4j.chapter2.model.Customer;

/**
 * CustomerService 单元测试
 * 
 * @author DELL
 *
 */
public class CustomerServiceTest {
	private final CustomerService customerService;

	public CustomerServiceTest() {
		customerService = new CustomerService();
	}

	@Before
	public void init() {
		// TODO 初始化数据库
	}

	@Test
	public void getCustomerListTest() {
		List<Customer> customerList = customerService.getCustomerList();
		Assert.assertEquals(2, customerList.size());
	}

	@Test
	public void createCustomerTest() {
		Map<String, Object> fieldMap = Maps.newHashMap();
		fieldMap.put("name", "customer100");
		fieldMap.put("contact", "John");
		fieldMap.put("telphone", "13512345678");
		boolean result = customerService.createCustomer(fieldMap);
		Assert.assertTrue(result);
	}

	@Test
	public void updateCustomerTest() {
		long id = 1;
		Map<String, Object> fieldMap = Maps.newHashMap();
		fieldMap.put("contact", "John");
		boolean result = customerService.updateCustomer(id, fieldMap);
		Assert.assertTrue(result);
	}

	@Test
	public void deleteCustomerTest() {
		long id = 1;
		boolean result = customerService.deleteCustomer(id);
		Assert.assertTrue(result);
	}
}
