package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.vo.SysRoleMenuVo;
@Mapper
public interface SysRoleDao {
	int getRowCount(@Param("name") String name);
	List<SysRole> findPageObjects(
		      @Param("name")String  name,
		      @Param("startIndex")Integer startIndex,
		      @Param("pageSize")Integer pageSize);
	
	int deleteObject(Integer id);
	int insertObject(SysRole entity);
	SysRoleMenuVo findObjectById(Integer id);
	int updateObject(SysRole entity);
	
	List<CheckBox> findObjects();
}
