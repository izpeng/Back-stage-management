package com.cy.pj.sys.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysDept;

@Mapper
public interface SysDeptDao {
      /**
               * 查询所有部门以及部门的上级菜单信息
       * @return
       */
	  @Select("select c.*,p.name parentName from sys_depts c left join sys_depts p on c.parentId=p.id")
	  List<Map<String,Object>> findObjects();
	  
	  @Select("select id,name,parentId from sys_depts")
	  List<Node> findZTreeNodes();
	  
	  int updateObject(SysDept entity);
	  int insertObject(SysDept entity);
	  
	  @Select("select count(*) from sys_depts where parentId=#{id}")
	  int getChildCount(Integer id);
	  
	  @Delete("delete from sys_depts where id=#{id}")
	  int deleteObject(Integer id);
}







