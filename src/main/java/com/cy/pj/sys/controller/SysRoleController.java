package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
@RestController
@RequestMapping("/role/")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	@RequestMapping("doFindPageObjects")
	 public JsonResult doFindPageObjects(
			 String name,Integer pageCurrent) {
		 return new JsonResult(
			sysRoleService.findPageObjects(name,
					pageCurrent));
	 }
	
	@RequestMapping("doDeleteObject")
	  public JsonResult doDeleteObject(Integer id){
		  sysRoleService.deleteObject(id);
		  return new JsonResult("delete ok");
	  }
	
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(
	    		SysRole entity,Integer[] menuIds){
	    	sysRoleService.saveObject(entity,menuIds);
	return new JsonResult("save ok");    
	}
	@RequestMapping("doFindObjectById")
	 public JsonResult doFindObjectById(Integer id){
	    	return new JsonResult(sysRoleService.findObjectById(id));
	 }
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysRole entity,
			Integer[] menuIds){
			  sysRoleService.updateObject(entity,menuIds);
	 return new JsonResult("update ok");
}
	 @RequestMapping("doFindRoles")
	 public JsonResult doFindRoles() {
		 return new JsonResult(sysRoleService.findObjects());
	 }
}