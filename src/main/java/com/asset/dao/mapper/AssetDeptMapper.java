package com.asset.dao.mapper;

import org.apache.ibatis.annotations.Insert;

import com.asset.model.AssetDept;

/**
 * mapper类使用接口形式。
 * @author Administrator
 *
 */
public interface AssetDeptMapper {
	
	@Insert("insert into asset_dept(creation,asset_id,dept_id,location,remark) "
			+ "values (now(),#{assetId},#{deptId },#{location },#{remark })")
	public int save(AssetDept assetDept);
	
	@Insert("insert into asset_dept(creation,asset_id,dept_id,type) "
			+ "values (now(),#{id},8,1)")
	public int getBack(String id);

}
