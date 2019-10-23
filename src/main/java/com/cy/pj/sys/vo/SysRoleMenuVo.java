package com.cy.pj.sys.vo;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
/**
 *     通过此对象封装基于角色id查询到的角色以
 *     及对应的菜单id
 */
@Data
public class SysRoleMenuVo implements Serializable{
	private static final long serialVersionUID = 3609240922718345518L;
	/**角色id*/
	private Integer id;
	/**角色名称*/
	private String name;
    /**角色备注*/
	private String note;
	/**角色对应的菜单id*/
	private List<Integer> menuIds;
}
