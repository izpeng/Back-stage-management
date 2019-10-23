package com.cy.pj.common.web;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.cy.pj.common.exception.ServiceException;

public class TimeAccessInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandler()");
		//获取java中的日历对象
		Calendar c=Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 9);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		long start=c.getTimeInMillis();
		c.set(Calendar.HOUR_OF_DAY,20);
		long end=c.getTimeInMillis();
		long cTime=System.currentTimeMillis();
		if(cTime<start||cTime>end)
		throw new ServiceException("不在访问时间之内");
		return true;//true放行
		
	}
}
