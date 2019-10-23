package com.cy.pj.common.vo;
import java.io.Serializable;
import lombok.Data;
/**
 * 节点值对象,一般用于封装树结构中的具体对象信息
 * @author Administrator
 */
@Data
public class Node implements Serializable{
	private static final long serialVersionUID = 2048083156365694892L;
	/**
	 * 节点id
	 */
	private Integer id;
	/**
	 * 节点名称
	 */
	private String name;
	/**
	 * 上级节点信息
	 */
	private Integer parentId;
	
	
}
