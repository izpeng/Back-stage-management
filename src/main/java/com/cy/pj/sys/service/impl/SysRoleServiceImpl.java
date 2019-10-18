package com.cy.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.anno.RequiredLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.vo.SysRoleMenuVo;
@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Override
	@RequiredLog("角色:查询")
	public PageObject<SysRole> findPageObjects(
			String username,Integer pageCurrent) {
		//1.对参数进行校验
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("当前页码值无效");
		//2.查询总记录数并进行校验
		int rowCount=sysRoleDao.getRowCount(username);
		if(rowCount==0)
		throw new ServiceException("没有找到对应记录");
		//3.查询当前页记录
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysRole> records=
		sysRoleDao.findPageObjects(username,
		startIndex, pageSize);
		//4.对查询结果进行封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}
	@Override
	@RequiredLog("角色:删除")
	public int deleteObject(Integer id) {
		//1.验证数据的合法性
		if(id==null||id<=0)
		throw new IllegalArgumentException("请先选择");
		//3.基于id删除关系数据
		sysRoleMenuDao.deleteObjectsByRoleId(id);
        sysUserRoleDao.deleteObjectsByRoleId(id);
		//4.删除角色自身
		int rows = sysRoleDao.deleteObject(id);
		//if(rows==0)
		//	throw new ServiceException("此菜单可能已经不存在");
		//5.返回结果
		return rows;
	}
	@Override
	//@RequiredLog("角色:添加")
	public int saveObject(SysRole entity, Integer[] menuIds) {
		//1.合法性验证
    	if(entity==null)
    throw new ServiceException("保存数据不能为空");
    	if(StringUtils.isEmpty(entity.getName()))
    	throw new ServiceException("角色名不能为空");
   	if(menuIds==null||menuIds.length==0)
    	throw new ServiceException("必须为角色赋予权限");
    	//2.保存数据
    	int rows=sysRoleDao.insertObject(entity);
    	sysRoleMenuDao.insertObjects(
    			entity.getId(),menuIds);
    	//3.返回结果
    	return rows;
	}
	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		//1.合法性验证
    	if(id==null||id<=0)
    	throw new ServiceException("id的值不合法");
    	//2.执行查询
    	SysRoleMenuVo result=sysRoleDao.findObjectById(id);
  	//3.验证结果并返回
    	if(result==null)
    	throw new ServiceException("此记录已经不存在");
    	return result;
	}
	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		//1.合法性验证
    	if(entity==null)
        throw new ServiceException("更新的对象不能为空");
    	if(entity.getId()==null)
    	throw new ServiceException("id的值不能为空");
    	
    	if(StringUtils.isEmpty(entity.getName()))
    	throw new ServiceException("角色名不能为空");
    	if(menuIds==null||menuIds.length==0)
    	throw new ServiceException("必须为角色指定一个权限");
    	
    	//2.更新数据
    	int rows=sysRoleDao.updateObject(entity);
    	if(rows==0)
        throw new ServiceException("对象可能已经不存在");
    	sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
    	sysRoleMenuDao.insertObjects(entity.getId(),menuIds);
    	//3.返回结果
    	return rows;
	}
	@Override
	public List<CheckBox> findObjects() {
		return sysRoleDao.findObjects();
	}

}
