package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {
	int updatePassword(
			@Param("password")String password,
			@Param("salt")String salt,
			@Param("id")Integer id);

	SysUser findUserByUserName(String username);
	SysUserDeptVo findObjectById(Integer id);
	/**
	  *   更新用户自身信息
	 * @param entity
	 * @return
	 */
	 int updateObject(SysUser entity);
	 /**
	    * 保存用户自身信息
	  * @param entity
	  * @return
	  */
	 int insertObject(SysUser entity);
	/**
	  * 用户禁用启用数据状态的修改
	 * @param id 用户id
	 * @param valid 用户状态
	 * @param modifiedUser 修改用户
	 * @return 修改行数
	 */
	  int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);

       /**
                *  按条件统计记录总数
        * @param username
        * @return
        */
	   int getRowCount(@Param("username")String username);
	   /**
	    * 	按条件分页查询用户信息
	    * @param username
	    * @param startIndex
	    * @param pageSize
	    * @return
	    */
	   List<SysUserDeptVo> findPageObjects(
			   @Param("username")String username,
			   @Param("startIndex")Integer startIndex,
			   @Param("pageSize")Integer pageSize);
}








