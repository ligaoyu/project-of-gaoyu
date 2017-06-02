package com.asset.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.asset.dao.mapper.AssetMapper;
import com.asset.model.Asset;
import com.asset.vo.BarChart;
import com.asset.vo.SelAssetVo;
import com.asset.vo.Series;

@Service
public class AssetService {
	
	@Resource
	private AssetMapper assetMapper;
	
	/**
	 * 条件查询的list
	 * @return
	 */
	public List<Asset> query(SelAssetVo vo){
		String type = vo.getType();
		if(null != type && !("".equals(type.trim()))){
			//如果type有值，则根据命名 外借=1 空闲=0
			if("空闲".equals(type)){
				vo.setType("0");
			}else{
				vo.setType("1");
			}
		}
		
		String parent = vo.getParent();
		if(null != parent && !("".equals(parent.trim()))){
			//如果parent有值，则根据parent的name值 获取到ID
			Asset parentModle = assetMapper.queryParentByName(parent);
			String parentId = parentModle == null ? null:parentModle.getId()+"";
			vo.setParent(parentId);
		}
		
		List<Asset> assetList = assetMapper.queryAllAssetByAssetVo(vo);
		return assetList;
	}
	
	/**
	 * 用于拼装准备柱形图数据
	 * 
	 * 1、需要一个类别总类
	 * 	data: ['直接访问', '邮件营销','联盟广告','视频广告','搜索引擎']
	 * 	
	 * 2、需要一个横轴各部门数据
	 * 	 data: ['周一','周二','周三','周四','周五','周六','周日']
	 * 
	 * 3、需要封装每个类别中 各个部门的使用状态
	 * 
	 * {
		            name: '直接访问',
		            type: 'bar',
		            stack: '总量',
		            label: {
		                normal: {
		                    show: true,
		                    position: 'insideRight'
		                }
		            },
		            data: [320, 302, 301, 334, 390, 330, 320]
		        },
	 * 
	 */
	public BarChart getBarChartData(){
		BarChart bar = new BarChart();
		
		List<Map<String,String>> deptUsingInfoList = assetMapper.getDeptUsingInfo();//有parent_name类别ID dname部门名称  num使用数量 三个字段
		
		//获得所有类别名
		List<String> parentNameList = new ArrayList<String>();
		
		//获取横轴坐标数据
		List<String> dnameList = new ArrayList<String>();
		for(Map<String,String> deptUsingInfo : deptUsingInfoList){
			String dname = deptUsingInfo.get("dname");
			if(!dnameList.contains(dname)){
				dnameList.add(dname);
			}
			
			String parentName = deptUsingInfo.get("parent_name");
			if(!parentNameList.contains(parentName)){
				parentNameList.add(parentName);
			}
		}
		
		String[] arr1 = new String[dnameList.size()];
		String[] arr2 = new String[parentNameList.size()];
		
		//赋值X轴
		bar.setXAxis(dnameList.toArray(arr1));
		
		//赋值属性值
		bar.setLegend(parentNameList.toArray(arr2));
		
		Series[] seriesArr = new Series[parentNameList.size()];
		
		//给每个属性值设置Y轴的value
		for(int index=0 ; index < parentNameList.size() ; index++){
			String parentName = parentNameList.get(index);
			Series series = new Series();
			
			//设置属性值名
			series.setName(parentName);
			Map<String,Integer> deptNum = new HashMap<String,Integer>();
			
			//设置属性值，获取每个部门的使用类别数量
			for(String dname : dnameList){
				for(Map<String,String> deptUsingInfo:deptUsingInfoList){
					if(deptUsingInfo.get("parent_name").equals(parentName) && deptUsingInfo.get("dname").equals(dname)){
						deptNum.put(dname, Integer.parseInt(String.valueOf(deptUsingInfo.get("num"))));
					}
				}
				if(!deptNum.containsKey(dname)){
					deptNum.put(dname, 0);
				}
			}
			
			
			
//			for(Map<String,String> deptUsingInfo:deptUsingInfoList){
//				for(String dname : dnameList){
//					if(deptUsingInfo.get("parent_name").equals(parentName) && deptUsingInfo.get("dname").equals(dname)){
//						deptNum.put(deptUsingInfo.get("dname"), Integer.parseInt(String.valueOf(deptUsingInfo.get("num"))));
//						break;
//					}else{
//						deptNum.put(deptUsingInfo.get("dname"), 0);
//					}
//				}
//			}
			
			//设置value值
			int[] data = new int[dnameList.size()];
			
			for(int i=0;i<data.length;i++){
				String dname = dnameList.get(i);
				data[i] = deptNum.get(dname);
			}
			series.setData(data);
			seriesArr[index] = series;
		}
		
		bar.setSeries(seriesArr);
		
		return bar;
	}

}
