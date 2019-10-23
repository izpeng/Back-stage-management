package com.cy.pj.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/log/")
public class SysLogController {//BaseController
	 @Autowired
	 private SysLogService sysLogService;
	 
	 @RequestMapping("doDeleteObjects")
	 public JsonResult doDeleteObjects(Integer...ids) {
		 sysLogService.deleteObjects(ids);
		 return new JsonResult("delete ok");
	 }
	 /***
	  * 分页查询请求处理方法(此方法由Spring MVC框架通过反射技术调用)
	  * @param username 接收客户端请求中的username值
	  * @param pageCurrent 接收客户端请求中的pageCurrent值
	  * @return 封装了业务数据以及状态信息的一个对象(JsonResult)
	  */
	 @RequestMapping("doFindPageObjects")
	 //@ResponseBody
	 public JsonResult doFindPageObjects(
			 String username,
			 Integer pageCurrent) {
		 PageObject<SysLog> pageObject=
		 sysLogService.findPageObjects(username, pageCurrent);
		 return new JsonResult(pageObject);
	 }
	 //局部异常处理
//	 @ExceptionHandler(RuntimeException.class)
//	 @ResponseBody
//	 public JsonResult doHandleRuntimeException(
//			 RuntimeException e) {
//		 return null;
//	 }
}
