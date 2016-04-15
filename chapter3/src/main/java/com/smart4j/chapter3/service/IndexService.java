package com.smart4j.chapter3.service;

import com.smart4j.framework.annotation.Service;

@Service
public class IndexService {

	public String index() {
		return "Hello smart4j";
	}
}
