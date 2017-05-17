package com.asset.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asset.dao.mapper.UserMapper;

@Controller  //配置controller注解
@RequestMapping("/testController")  //配置请求
public class AssetController {
	
	@Resource
	private UserMapper userMapper;
	
	@RequestMapping("/test/")   //配置请求路径
	public String test1(Map map){
		System.out.println(" go to test 1 !!!!!!!!!!!");
		
		List<Map> list = userMapper.query();
		
		System.out.println(list);
		
		map.put("list", list);
		
		return "/page/index"; //返回路径信息
	}

}
