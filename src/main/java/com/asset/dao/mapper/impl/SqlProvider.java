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
		
		return " select c.*,d.dname from asset c left join "
			+ "(select a.asset_id,(select name from fixed_asset.usr_dept where id=a.dept_id) as dname from fixed_asset.asset_dept a join "
			+ "(SELECT asset_id,max(creation) as last_time FROM fixed_asset.asset_dept group by asset_id) b "
			+ "on a.asset_id=b.asset_id where creation=last_time and type=0) d on c.id=d.asset_id where removed = 0 and level=2 " + sb.toString() ;
	}
	
}
