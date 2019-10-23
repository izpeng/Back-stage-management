package com.cy.pj.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/menu/")
public class SysMenuController {
	  @Autowired
      private SysMenuService sysMenuService;
	  
	  @PostMapping("doUpdateObject")
	  public JsonResult doUpdateObject(SysMenu entity) {
		  sysMenuService.updateObject(entity);
		  return new JsonResult("update ok");
	  }
	  @PostMapping("doSaveObject")
	  public JsonResult doSaveObject(SysMenu entity) {
		  sysMenuService.saveObject(entity);
		  return new JsonResult("save ok");
	  }
	  @GetMapping("doFindZtreeMenuNodes")
	  public JsonResult doFindZtreeMenuNodes() {
		  return new JsonResult(sysMenuService.findZtreeMenuNodes());
	  }
	  @RequestMapping("doDeleteObject")
	  public JsonResult doDeleteObject(Integer id) {
		  sysMenuService.deleteObject(id);
		  return new JsonResult("delete ok");
	  }
	  @RequestMapping("doFindObjects")
	  public JsonResult doFindObjects() {
		  System.out.println("doFindObjects() ");
		  return new JsonResult(sysMenuService.findObjects());
	  }
}






