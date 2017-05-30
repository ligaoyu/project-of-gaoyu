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
import com.asset.service.AssetService;
import com.asset.vo.SelAssetVo;

@Controller  //配置controller注解
@RequestMapping("/asset")  //配置请求
public class AssetController {
	
	@Resource
	private AssetMapper assetMapper;
	
	@Resource
	private AssetService assetService;
	
	/**
	 * 进入主页的方法
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/main/")
	public String main(String name,ModelMap model){
		return "main";
	}
	
	/**
	 * 进入list列表页的方法
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list/")
	public String list(String name,ModelMap model){
		List<Asset> assetList = assetMapper.queryAllAsset();
		String assetJson = JSON.toJSONString(assetList);
		List<Asset> assetParent = assetMapper.queryAllParentIdAndName();
		model.put("assetJson", assetJson);
		model.put("assetParent", JSON.toJSONString(assetParent));
		return "asset_list";
	}
	
	/**
	 * ajax进行多条件值 过滤查询的方法
	 * @param name
	 * @param parent
	 * @param assetNo
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/query/")
	public @ResponseBody Object query(String name,String parent,String assetNo,String type,ModelMap model){
		SelAssetVo vo = new SelAssetVo(name,assetNo,parent,type);
		List<Asset> assetList = assetService.query(vo);
		String assetJson = JSON.toJSONString(assetList);
		return assetJson;
	}
	
	/**
	 * 进入add页面的方法
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add/")
	public String add(String name,ModelMap model){
		return "asset_add";
	}
	
	
	
	
}
