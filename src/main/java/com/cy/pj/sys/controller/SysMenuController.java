package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

@RestController
@RequestMapping("/menu/")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects() {

		return new JsonResult(sysMenuService.findObjects());
	}
	
	 @RequestMapping("doDeleteObject")
	  public JsonResult doDeleteObject(Integer id){
		  sysMenuService.deleteObject(id);
		  return new JsonResult("delete ok");
	  }
	 @RequestMapping("doFindZtreeMenuNodes")
	 public JsonResult doFindZtreeMenuNodes(){
		 return new JsonResult(
		 sysMenuService.findZtreeMenuNodes());
	 }
	 @RequestMapping("doSaveObject")
		public JsonResult doSaveObject(SysMenu entity){
			sysMenuService.saveObject(entity);
			return new JsonResult("save ok");
		}
	 
	 @RequestMapping("doUpdateObject")
		public JsonResult doUpdateObject(SysMenu entity){
			sysMenuService.updateObject(entity);
			return new JsonResult("update ok");
		}
}