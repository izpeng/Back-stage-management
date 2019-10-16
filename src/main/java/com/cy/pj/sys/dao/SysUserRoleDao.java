package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleDao {
	int deleteObjectsByRoleId(Integer roleId);
	int insertObjects(
			@Param("userId")Integer userId,
			@Param("roleIds")Integer[] roleIds);
	
	List<Integer> findRoleIdsByUserId(Integer id);
	int deleteObjectsByUserId(Integer userId);
}
