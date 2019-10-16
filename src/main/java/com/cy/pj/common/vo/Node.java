package com.cy.pj.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Node implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2048083156365694892L;
	private Integer id;
	private String name;
	private Integer parentId;
}
