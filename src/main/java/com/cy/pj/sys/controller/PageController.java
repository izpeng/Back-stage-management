package com.cy.pj.sys.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
  *  在此Controller中定义项目中的页面
  *  首页访问:http://localhost/doIndexUI
 */
@Controller
@RequestMapping("/")
public class PageController {
	 @RequestMapping("doIndexUI")
     public String doIndexUI() {
    	 return "starter";
     }
	 /**返回分页页面*/
	 @RequestMapping("doPageUI")
	 public String doPageUI() {
		 return "common/page";
	 }
	 
	 @RequestMapping("doLoginUI")
	 public String doLoginUI(){
	 		return "login";
	 }

	 /**返回日志列表页面*/
//	 @RequestMapping("log/log_list")
//	 public String doLogUI() {
//		 return "sys/log_list";
//	 }
	 /**返回菜单列表页面*/
//	 @RequestMapping("menu/menu_list")
//	 public String doMenuUI() {
//		 return "sys/menu_list";
//	 }
	 /**返回各模块的UI页面*/
	 @RequestMapping("{module}/{moduleUI}")
	 public String doModuleUI(
			 @PathVariable String moduleUI) {
		 return "sys/"+moduleUI;
	 }
}






