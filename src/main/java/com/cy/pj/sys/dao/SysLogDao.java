package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.entity.SysLog;

@Mapper
public interface SysLogDao {
	/**
	 * 统计记录总数
	 * @param username
	 * @return  行数
	 */
	int getRowCount(@Param("username") String username);
	/**
	 * 查询当前页记录
	 * @param username  条件 允许空
	 * @param startIndex  起始位置
	 * @param pageSize   页面大小
	 * @return   当前页记录
	 */
	List<SysLog> findPageObjects(
		      @Param("username")String  username,
		      @Param("startIndex")Integer startIndex,
		      @Param("pageSize")Integer pageSize);
	
	int deleteObjects(@Param("ids")Integer...ids);
	void insertObject(SysLog entity);
}
