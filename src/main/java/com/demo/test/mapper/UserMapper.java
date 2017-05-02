package com.demo.test.mapper;

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
	@Select("select * from usr_user")
	public List<Map> query();

}
