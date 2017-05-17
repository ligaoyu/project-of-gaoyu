package com.asset.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

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
	public List<Map> query();

}
