package com.asset.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.asset.dao.mapper.AssetDeptMapper;
import com.asset.dao.mapper.AssetMapper;
import com.asset.dao.mapper.DeptMapper;
import com.asset.model.Asset;
import com.asset.model.AssetDept;
import com.asset.model.Dept;
import com.asset.service.AssetService;
import com.asset.vo.BarChart;
import com.asset.vo.SelAssetVo;

@Controller  //配置controller注解
@RequestMapping("/asset")  //配置请求
public class AssetController {
	
	@Resource
	private AssetMapper assetMapper;
	
	@Resource
	private AssetService assetService;
	
	@Resource
	private DeptMapper deptMapper;
	
	@Resource
	private AssetDeptMapper assetDeptMapper;
	
	/**
	 * 进入主页的方法
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/main/")
	public String main(String name,ModelMap model){
		//获取数据百分比
		model.put("progress", JSON.toJSONString(assetMapper.getPercent()));
		
		//获取饼图数据
		List<Map<String,String>> pieData = assetMapper.getPieData();
		String[] arr = new String[pieData.size()];
		for(int i=0 ;i<pieData.size();i++){
			arr[i] = pieData.get(i).get("name");
		}
		model.put("arr", JSON.toJSONString(arr));
		model.put("pieData", JSON.toJSONString(pieData));
		
		//获取柱形图数据
		BarChart bar = assetService.getBarChartData();
		
		model.put("legend", JSON.toJSONString(bar.getLegend()));
		model.put("xAxis", JSON.toJSONString(bar.getXAxis()));
		model.put("series", JSON.toJSONString(bar.getSeries()));
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
		
		List<Dept> deptList = deptMapper.query();
		
		model.put("assetJson", assetJson);
		model.put("assetParent", JSON.toJSONString(assetParent));
		model.put("deptList", JSON.toJSONString(deptList));
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
		List<Asset> assetParent = assetMapper.queryAllParentIdAndName();
		model.put("assetParent", JSON.toJSONString(assetParent));
		return "asset_add";
	}
	
	@RequestMapping(value = "/save/")
	public @ResponseBody Object save(Asset form,ModelMap model){
		int result = assetMapper.save(form);
		return result;
	}
	
	@RequestMapping(value = "/find/")
	public String find(String value,ModelMap model){
		SelAssetVo vo = new SelAssetVo(value,value,null,null);
		List<Asset> assetList = assetService.query(vo);
		String assetJson = JSON.toJSONString(assetList);
		List<Asset> assetParent = assetMapper.queryAllParentIdAndName();
		model.put("assetJson", assetJson);
		model.put("assetParent", JSON.toJSONString(assetParent));
		
		List<Dept> deptList = deptMapper.query();
		
		model.put("deptList", JSON.toJSONString(deptList));
		
		return "asset_list";
	}
	
	/**
	 * 借出流程，先把设备状态改为1，再在中间表插入数据
	 * @param assetId
	 * @param name
	 * @param deptId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/lend/")
	public @ResponseBody Object lend(AssetDept dialogForm,ModelMap model){
		assetMapper.lend(dialogForm.getAssetId()+"");
		int y = assetDeptMapper.save(dialogForm);
		return y;
	}
	
	@RequestMapping(value = "/getBack/")
	public @ResponseBody Object getBack(String id,ModelMap model){
		assetMapper.getBack(id);
		int i = assetDeptMapper.getBack(id);
		return i;
	}
	
	
}
