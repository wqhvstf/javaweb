package com.smart4j.chapter3.controller;

import java.util.Map;

import com.google.common.collect.Maps;
import com.smart4j.chapter3.service.IndexService;
import com.smart4j.framework.annotation.Action;
import com.smart4j.framework.annotation.Controller;
import com.smart4j.framework.annotation.Inject;
import com.smart4j.framework.bean.Data;
import com.smart4j.framework.bean.View;

@Controller
public class IndexController {

	@Inject
	private IndexService indexService;

	@Action("get:/indexView")
	public View indexView() {
		return new View("hello.jsp").addModel("index", indexService.index());
	}

	@Action("get:/indexData")
	public Data indexData() {
		Map<String, String> model = Maps.newHashMap();
		model.put("hello", "smart4j");
		return new Data(model);
	}

}
