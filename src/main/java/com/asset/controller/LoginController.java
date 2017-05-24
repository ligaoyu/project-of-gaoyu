package com.asset.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asset.dao.mapper.UserMapper;
import com.asset.model.User;

@Controller  //配置controller注解
@RequestMapping("/login/")  //配置请求
public class LoginController {
	
	@Resource
	private UserMapper userMapper;
	
	@RequestMapping(value = "/login/")
	public String login(ModelMap model){
		return "login";
	}
	
	@RequestMapping(value = "/check/")
	public String check(String username,String password,ModelMap model){
		User user = userMapper.query(username);
		
		if(user != null && user.getPassword().equals(password)){
			return "redirect:/asset/main/";
		}
		
		return "redirect:/login/login/";
		
	}
	

}
