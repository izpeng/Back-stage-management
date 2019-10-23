package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserRoleDao {
	/**
	  * 基于用户id查询对应的角色ID
	 * @param id
	 * @return
	 */
	  @Select("select role_id from sys_user_roles where user_id=#{userId}")
	  List<Integer> findRoleIdsByUserId(Integer id);
	 /**
	  * 将用户和角色的关系数据存储到数据库
	  * @param userId
	  * @param roleIds
	  * @return
	  */
	  int insertObjects(
			   @Param("userId")Integer userId,
			   @Param("roleIds")Integer[]roleIds);
      /**
              * 基于用户id删除用户与角色的关系数据
       * @param userId
       * @return
       */
	  @Delete("delete from sys_user_roles where user_id=#{userId}")
	  int deleteObjectsByUserId(Integer userId);
	  /**
	   * 基于角色id删除用户与角色的关系数据
	   * @param roleId
	   * @return
	   */
	  @Delete("delete from sys_user_roles where role_id=#{roleId}")
	  int deleteObjectsByRoleId(Integer roleId);
}
