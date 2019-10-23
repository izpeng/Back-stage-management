package com.cy.pj.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao {
	  /**
	      * 查询所有角色
	   * @return
	   */
	  @Select("select id,name from sys_roles")
	  List<CheckBox> findObjects();
	  /**
	      * 基于角色id查询角色以及角色对象的菜单id
	   * @param id
	   * @return
	   */
	  SysRoleMenuVo findObjectById(Integer id);
	  /**
	      * 更新角色自身信息
	   * @param entity
	   * @return
	   */
	  int updateObject(SysRole entity);
	  /**
	      * 保存角色自身信息
	   * @param entity
	   * @return
	   */
 	  int insertObject(SysRole entity);
	   /**
	        * 基于角色删除角色自身信息
	    * @param id
	    * @return
	    */
	   @Delete("delete from sys_roles where id=#{id}")
	   int deleteObject(Integer id);
       /**
                * 基于条件统计角色记录总数
        * @param name 角色名
        * @return 统计数量
        */
	   int getRowCount(@Param("name")String name);
	   /**
	        * 查询当前页角色记录
	    * @param name 角色名
	    * @param startIndex 当前页起始位置
	    * @param pageSize 当前页面大小
	    * @return 当前页角色记录
	    */
	   List<SysRole> findPageObjects(
			   @Param("name")String name,
			   @Param("startIndex")Integer startIndex,
			   @Param("pageSize")Integer pageSize);
}





