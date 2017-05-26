package com.asset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  //配置controller注解
@RequestMapping("/asset")  //配置请求
public class AssetController {
	
	@RequestMapping(value = "/main/")
	public String main(String name,ModelMap model){
		return "main";
	}
	
	@RequestMapping(value = "/list/")
	public String list(String name,ModelMap model){
		return "asset_list";
	}
	
	@RequestMapping(value = "/add/")
	public String add(String name,ModelMap model){
		return "asset_add";
	}
	
}
