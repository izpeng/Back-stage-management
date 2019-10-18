package com.cy.pj.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cy.pj.common.anno.RequiredLog;
import com.cy.pj.common.util.IPUtils;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Order(1)
@Slf4j
@Aspect
@Component
public class SysLogAspect {//SysLogAspect.class
	//@Pointcut注解用于描述方法,定义切入点
	//bean(sysUserServiceImpl)为一种切入点表达式
	//sysUserServiceImpl为spring容器中的一个bean的名字
	//切入点表达式中的bean表达式
	//@Pointcut("bean(sysUserServiceImpl)")
	//切入点表达式中的注解表达式
	//由此注解描述的方法作为切入点.
	@Pointcut("@annotation(com.cy.pj.common.anno.RequiredLog)")
	public void logPointCut() {}
	
	//@Around为一个环绕通知
	//特点
	//1)可以在目标方法执行之前和之后添加自己的拓展业务
	//2)方法返回值类型Object
	//3)方法参数类型为ProceedingJoinPoint,此对象中封装了目标方法信息
	//4)方法异常抛出为Throwable类型
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint joinPoint)
	throws Throwable{
		//1.记录目标方法开始执行时间
		long t1=System.currentTimeMillis();
		//log.info("start time:"+t1);
		//2.执行目标方法
		Object result=joinPoint.proceed();//假如有下一个切面先执行切面对象方法
		//3.记录目标方法结束执行时间
		long t2=System.currentTimeMillis();
		//log.info("end time:"+t2);
		//记录用户行为日志
		saveLog(joinPoint,(t2-t1));
		return result;
	}
	@Autowired
	private SysLogService logService;
	//获取用户行为日志信息,然后将日志写入到数据库
	private void saveLog(ProceedingJoinPoint joinPoint,long time)throws Exception {
		//1.获取日志信息
		//获取目标方法对象
		Method targetMethod = getTargetMethod(joinPoint);
		//获取目标方法名:目标类全名+方法名
		String classMethodName=getTargetMethodName(targetMethod);
		//获取操作名
		String operation= getOperation(targetMethod);
		//String params=Arrays.toString(joinPoint.getArgs());
		//获取方法执行时的实际参数
		String params=
		new ObjectMapper().writeValueAsString(joinPoint.getArgs());
		//2.封装日志信息
	    SysLog sysLog=new SysLog(null,
	    		"tmooc",//登录用户名
	    		 operation,
	    		 classMethodName, 
	    		 params, 
	    		 time, 
	    		 IPUtils.getIpAddr(), 
	    		 new Date());
		//3.持久化日志信息
		logService.insertObject(sysLog);
	}

	private String getOperation(Method targetMethod) {
		RequiredLog rLog=
		targetMethod.getAnnotation(RequiredLog.class);
		return rLog.value();
	}

	private String getTargetMethodName(Method targetMethod) {
		return targetMethod.getDeclaringClass().getName()
		+"."+targetMethod.getName();
	}

	private Method getTargetMethod(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
		Class<?> targetClass=joinPoint.getTarget().getClass();
		MethodSignature s=(MethodSignature)joinPoint.getSignature();//方法签名
		Method targetMethod=
				targetClass.getMethod(
						s.getName(),
				        s.getParameterTypes());
		return targetMethod;
	}
}








