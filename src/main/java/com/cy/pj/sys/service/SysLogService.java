package com.cy.pj.sys.service;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;
/**
 * 日志业务处理接口对象
 */
public interface SysLogService {
	
	/**
	 * 保存用户行为日志
	 * @param entity
	 */
	void saveObject(SysLog entity);
	
	/**
	  *  执行删除业务
	 * @param ids 多个日志id
	 * @return 删除的行数
	 */
	int deleteObjects(Integer...ids);
	
     /**
            * 分页查询当前页记录
      * @param username 查询条件
      * @param pageCurrent 当前页码值
      * @return 封装了分页信息和当前页记录的值对象
      */
	 PageObject<SysLog> findPageObjects(
			 String username,
			 Integer pageCurrent);
}
