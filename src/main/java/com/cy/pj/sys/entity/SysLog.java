package com.cy.pj.sys.entity;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * pojo:(Plain Ordinary Java Object) 简单java对象:属性/set/get
 * 1)po(persistant object) 持久化对象:与表中字段有一一映射关系
 * 2)vo(Value Object) 值对象:负责每层对象数据值的封装和传递
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysLog implements Serializable{
	private static final long serialVersionUID = 8924387722922123121L;
	private Integer id;
	//用户名
	private String username;
	//用户操作
	private String operation;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//执行时长(毫秒)
	private Long time;
	//IP地址
	private String ip;
	//创建时间
	private Date createdTime;

}
