package com.cy.pj.sys.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;
/**
 * @Mapper 注解一般用于描述数据层接口对象,表示
 * 此接口对象的实现类由系统底层自动创建,并且会
 * 在此接口的实现类中注入SqlSessionFactory对象.
 */
@Mapper
public interface SysMenuDao {
	List<String> findPermissions(
			@Param("menuIds")
			Integer[] menuIds);
	/**
	 * 更新数据库中的菜单记录
	 * @param entity
	 * @return
	 */
	 int updateObject(SysMenu entity);
	  /**
	   * 将内存中的菜单对象持久化(存储到数据库)
	   * @param entity
	   * @return
	   */
	  int insertObject(SysMenu entity);
	   /**
	    * 获取所有菜单的菜单id,菜单名,上级菜单id
	    * 然后一个记录存储到一个Node对象中
	    * FAQ?一定要用node对象封装数据,不一定,
	    * 也可以使用map.
	    * @return
	    */
	   @Select("select id,name,parentId from sys_menus")
	   List<Node> findZtreeMenuNodes(); 
	   /**
	    * 基于菜单id统计子菜单的数量
	    * @param id 菜单id
	    * @return 子菜单的数量
	    */
	   @Select("select count(*) from sys_menus where parentId=#{id}")
	   int getChildCount(Integer id);
	   
	   /**
	    * 基于菜单id删除菜单
	    * @param id 菜单id
	    * @return 删除的行数
	    */
	   @Delete("delete from sys_menus where id=#{id}")
	   int deleteObject(Integer id);
	   
	  /**
	   * 查询所有的菜单以及菜单对应的上级菜单信息
	   * 要求:一行记录映射为内存中一个map对象.
	   * 说明:在很多优秀的产品级应用中其实不推荐
	   * 直接基于map做映射存储,因为第一可读性相对较差,
	   * 第二它的值的类型不可控.但是有时为提高开发的
	   * 速度,map就可以直接作为映射存储对象.尤其是
	   * 一些外包项目.
	   * @return
	   */
	  List<Map<String,Object>> findObjects();
}








