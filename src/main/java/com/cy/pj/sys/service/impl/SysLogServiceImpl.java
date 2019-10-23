package com.cy.pj.sys.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;


@Service
public class SysLogServiceImpl implements SysLogService{
	@Autowired
	private SysLogDao sysLogDao;
	/**
	 * @Async 描述的方法表示这个方法要在独立于
	 * web服务器的外部线程中进行工作.
	 */
	@Async
	@Transactional(propagation = 
	               Propagation.REQUIRES_NEW)
	@Override
	public void saveObject(SysLog entity) {
		System.out.println("log.thread.name:"+Thread.currentThread().getName());
		sysLogDao.insertObject(entity);
		//try {
		//Thread.sleep(60000);
		//}catch(Exception e) {e.printStackTrace();}
	}
	//@RequiresPermisssion("sys:log:delete")
	@Override
	public int deleteObjects(Integer... ids) {
		//1.验证参数的有效性
		if(ids==null || ids.length==0)
			throw new IllegalArgumentException("请选择删除的记录");
		//2.执行删除操作
		int rows=sysLogDao.deleteObjects(ids);
		//3.验证并返回结果
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		return 0;
	}
	@Override
	public PageObject<SysLog> findPageObjects(
	String username, Integer pageCurrent) {
		//1.对参数校验
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("页码值不正确");
		//2.基于条件查询总记录数并进行校验
		int rowCount=sysLogDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.基于条件查询当前页记录
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysLog> records=
		sysLogDao.findPageObjects(username,startIndex,pageSize);
		//4.对查询结果进行封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount,records);
	}
}





