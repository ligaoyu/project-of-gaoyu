package com.asset.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.asset.model.FixedAsset;

/**
 * mapper类使用接口形式。
 * @author Administrator
 *
 */
public interface UserMapper {
	
	/**
	 * 查询使用select注解
	 * @return
	 */
	@Select("select * from asset")
	public List<FixedAsset> query();

}
