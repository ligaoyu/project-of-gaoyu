package com.asset.dao.mapper.impl;

import com.asset.vo.SelAssetVo;

/**
 * 用来生成拼接的动态SQL
 * @author ligaoyu
 *
 */
public class SqlProvider {

	
	public String queryAllAssetByAssetVo(SelAssetVo vo){
		
		StringBuffer sb = new StringBuffer();
		
		if(vo.getAssetNo() != null && !("".equals(vo.getAssetNo().trim()))){
			sb.append(" and asset_no like '%" + vo.getAssetNo() +"%' ");
		}
		if(vo.getName() != null && !("".equals(vo.getName().trim()))){
			sb.append(" and name like '%" + vo.getName() +"%' ");
		}
		if(vo.getParent() != null && !("".equals(vo.getParent().trim()))){
			sb.append(" and parent_id = '" + vo.getParent() +"' ");
		}
		if(vo.getType() != null && !("".equals(vo.getType().trim()))){
			sb.append(" and type = '" + vo.getType() +"' ");
		}
		
		return " select * from asset where removed = 0 and level=2 " + sb.toString() ;
	}
	
}
