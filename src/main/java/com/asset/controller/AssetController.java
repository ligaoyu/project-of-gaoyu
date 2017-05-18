package com.asset.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asset.dao.mapper.UserMapper;
import com.asset.model.FixedAsset;

@Controller  //配置controller注解
@RequestMapping("/testController")  //配置请求
public class AssetController {
	
	@Resource
	private UserMapper userMapper;
	
	@RequestMapping(value = "/findUser/")
    public String findUserByName(String name,ModelMap model) {
		System.out.println(" go to findUserByName 1 !!!!!!!!!!!");
		List<FixedAsset> list = userMapper.query();
        model.addAttribute("list",list);
        for(FixedAsset l : list){
        	System.out.println(l.getId());
        	System.out.println(l.getName());
        	System.out.println(l.getCreation());
        	System.out.println(l.getLastModified());
        	System.out.println(l.getAssetNo());
        	System.out.println(l.getLocation());
        	System.out.println(l.toString());

        }
        System.out.println(list);
        return "asset_list";
    }

}
