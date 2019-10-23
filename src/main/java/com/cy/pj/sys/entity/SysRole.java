package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
  *  角色模块po对象(与sys_roles表中的字段有一一映射关系)
 */

@Data
public class SysRole implements Serializable{
	private static final long serialVersionUID = -356538509994150709L;
	private Integer id;
	private String name;
	private String note;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
