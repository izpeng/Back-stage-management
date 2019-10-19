package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;

@Mapper
public interface SysMenuDao {
	List<Map<String,Object>> findObjects();
	int getChildCount(Integer id);
	int deleteObject(Integer id);
	List<Node> findZtreeMenuNodes();
	int insertObject(SysMenu entity);
	int updateObject(SysMenu entity);
	List<String> findPermissions(
			@Param("menuIds")
			Integer[] menuIds);
}