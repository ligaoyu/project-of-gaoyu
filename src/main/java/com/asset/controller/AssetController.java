package com.asset.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.asset.dao.mapper.AssetMapper;
import com.asset.model.Asset;

@Controller  //配置controller注解
@RequestMapping("/asset")  //配置请求
public class AssetController {
	
	@Resource
	private AssetMapper assetMapper;
	
	@RequestMapping(value = "/main/")
	public String main(String name,ModelMap model){
		return "main";
	}
	
	@RequestMapping(value = "/list/")
	public String list(String name,ModelMap model){
		List<Asset> assetList = assetMapper.queryAll();
		String assetJson = JSON.toJSONString(assetList);
		List<Asset> assetParent = assetMapper.queryAllParentIdAndName();
		model.put("assetJson", assetJson);
		model.put("assetParent", JSON.toJSONString(assetParent));
		return "asset_list";
	}
	
	@RequestMapping(value = "/query/")
	public @ResponseBody Object query(String name,String parent,String assetNo,String type,ModelMap model){
		System.out.println(name);
		System.out.println(parent);
		System.out.println(assetNo);
		System.out.println(type);
		List<Asset> assetList = assetMapper.queryAllAsset();
		String assetJson = JSON.toJSONString(assetList);
		return assetJson;
	}
	
	@RequestMapping(value = "/add/")
	public String add(String name,ModelMap model){
		return "asset_add";
	}
	
}
