package com.asset.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.asset.dao.mapper.AssetMapper;
import com.asset.model.Asset;
import com.asset.vo.SelAssetVo;

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

}
