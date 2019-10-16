package com.cy.pj.sys.service;

import java.util.List;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

public interface SysRoleService {
	PageObject<SysRole> findPageObjects(
			 String name,Integer pageCurrent);
	int deleteObject(Integer id);
	int saveObject(SysRole entity,Integer[]menuIds);
	SysRoleMenuVo findObjectById(Integer id) ;
	int updateObject(SysRole entity,Integer[] menuIds);
	
	 List<CheckBox> findObjects();
}
