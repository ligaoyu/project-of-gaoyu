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
	
}
