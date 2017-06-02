package com.asset.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.asset.model.Dept;

/**
 * mapper类使用接口形式。
 * @author Administrator
 *
 */
public interface DeptMapper {
	
	/**
	 * 查询使用select注解
	 * @return
	 */
	@Select("select * from usr_dept ")
	public List<Dept> query();

}
