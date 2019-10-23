package com.cy.pj.sys.service;

import java.util.Map;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

public interface SysUserService {
	int updatePassword(String password,
	           String newPassword,
	           String cfgPassword);

	/**
	 * 基于用户id查询用户以及用户对应部门,角色相关信息
	 * @param id
	 * @return
	 */
	Map<String,Object> findObjectById(Integer id);
	/**
	  * 保存用户自身信息以及用户对应的角色关系数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int saveObject(SysUser entity,Integer[]roleIds);
	/**
	 * 更新用户自身信息以及用户对应的角色关系数据
	 * @param entity
	 * @param roleIds
	 * @return
	 */
	int updateObject(SysUser entity,Integer[]roleIds);
	 /**
	     * 禁用启用业务方法
	  * @param id
	  * @param valid
	  * @param modifiedUser
	  * @return
	  */
	 int validById(Integer id,Integer valid,String modifiedUser);
	 /**
	     * 用户模块的分页查询实现
	  * @param username
	  * @param pageCurrent
	  * @return
	  */
	 PageObject<SysUserDeptVo> findPageObjects(
			 String username,Integer pageCurrent);
}
