package com.asset.dao.mapper;

import org.apache.ibatis.annotations.Select;

import com.asset.model.User;

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
	@Select("select * from usr_user where account = #{account }")
	public User query(String account);

}
