package com.asset.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.asset.model.Asset;

/**
 * mapper类使用接口形式。
 * @author Administrator
 *
 */
public interface AssetMapper {
	
	/**
	 * 查询使用select注解
	 * @return
	 */
	@Select("select * from asset where removed = 0")
	public List<Asset> queryAll();
	
	/**
	 * 查询第二层级数据
	 * @return
	 */
	@Select("select * from asset where removed = 0 and level=2")
	public List<Asset> queryAllAsset();
	
	/**
	 * 查询第一层级数据
	 * @return
	 */
	@Select("select * from asset where removed = 0 and level=1")
	public List<Asset> queryAllParent();
	
	/**
	 * 查询第一层级数据的name和id
	 * @return
	 */
	@Select("select id,name from asset where removed = 0 and level=1")
	public List<Asset> queryAllParentIdAndName();

}
