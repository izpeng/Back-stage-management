package com.cy.pj.common.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cy.pj.common.vo.JsonResult;
//@ResponseBody
//@ControllerAdvice  //异常处理类

@RestControllerAdvice  //等同于上两个
public class GlobalExceptionHandler {
	//JDK中的自带的日志API
	@ExceptionHandler(RuntimeException.class)
    @ResponseBody
	public JsonResult doHandleRuntimeException(
			RuntimeException e){
    	e.printStackTrace();//也可以写日志
		return new JsonResult(e);//封装异常信息
	}
}