package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
 * 部门PO对象
 */
@Data
public class SysDept implements Serializable{
	private static final long serialVersionUID = 8876920804134951849L;
	private Integer id;
	private String name;
	private Integer parentId;
	private Integer sort;
	private String note;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
