package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 此Dao对象负责操作sys_role_menus中间表数据
 */
@Mapper
public interface SysRoleMenuDao {
	/**
	  * 基于多个角色id获取多个菜单id
	 * @param roleIds
	 * @return
	 */
	List<Integer> findMenuIdsByRoleIds(
			@Param("roleIds")Integer[] roleIds);

	/**
	  * 保存角色菜单关系数据
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	 int insertObjects(
			 @Param("roleId")Integer roleId,
			 @Param("menuIds")Integer[]menuIds);
     /**
            * 基于角色id删除菜单和角色的关系数据
      * @param menuId
      * @return
      */
	 @Delete("delete from sys_role_menus where role_id=#{roleId}")
	 int deleteObjectsByRoleId(Integer roleId);
	 /**
	  * 基于菜单id删除菜单和角色的关系数据
	  * @param menuId
	  * @return
	  */
	 @Delete("delete from sys_role_menus where menuId=#{menuId}")
	 int deleteObjectsByMenuId(Integer menuId);
}






