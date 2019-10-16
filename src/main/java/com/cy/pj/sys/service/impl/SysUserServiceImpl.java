package com.cy.pj.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.vo.SysUserDeptVo;
@Service
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
		//1.对参数进行校验
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("当前页码值无效");
		//2.查询总记录数并进行校验
		int rowCount=sysUserDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("没有找到对应记录");
		//3.查询当前页记录
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysUserDeptVo> records=
				sysUserDao.findPageObjects(username,
						startIndex, pageSize);
		//4.对查询结果进行封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		//1.合法性验证
				if(id==null||id<=0)
				throw new ServiceException("参数不合法,id="+id);
				if(valid!=1&&valid!=0)
				throw new ServiceException("参数不合法,valie="+valid);
				if(StringUtils.isEmpty(modifiedUser))
				throw new ServiceException("修改用户不能为空");
				//2.执行禁用或启用操作
				int rows=sysUserDao.validById(id, valid, modifiedUser);
				//3.判定结果,并返回
				if(rows==0)
				throw new ServiceException("此记录可能已经不存在");
				return rows;
	}
	@Override
	public int saveObject(SysUser entity, Integer[] roleIds) {
		long start=System.currentTimeMillis();
    	//log.info("start:"+start);
    	//1.参数校验
    	if(entity==null)
    		throw new ServiceException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getUsername()))
    		throw new ServiceException("用户名不能为空");
    	if(StringUtils.isEmpty(entity.getPassword()))
    		throw new ServiceException("密码不能为空");
    	if(roleIds==null || roleIds.length==0)
    		throw new ServiceException("至少要为用户分配角色");
    	//2.保存用户自身信息
        //2.1对密码进行加密
    	String source=entity.getPassword();
    	String salt=UUID.randomUUID().toString();
    	SimpleHash sh=new SimpleHash(//Shiro框架
    			"MD5",//algorithmName 算法
    			 source,//原密码
    			 salt, //盐值
    			 1);//hashIterations表示加密次数
    	entity.setSalt(salt);
    	entity.setPassword(sh.toHex());
    	int rows=sysUserDao.insertObject(entity);
    	//3.保存用户角色关系数据
    	sysUserRoleDao.insertObjects(entity.getId(), roleIds);
    	long end=System.currentTimeMillis();
    	//log.info("end:"+end);
    	//log.info("total time :"+(end-start));
    	//4.返回结果
    	return rows;
	}
	@Override
	public Map<String, Object> findObjectById(Integer userId) {
		//1.合法性验证
		if(userId==null||userId<=0)
		throw new ServiceException(
		"参数数据不合法,userId="+userId);
		//2.业务查询
		SysUserDeptVo user=
		sysUserDao.findObjectById(userId);
		if(user==null)
		throw new ServiceException("此用户已经不存在");
		List<Integer> roleIds=
		sysUserRoleDao.findRoleIdsByUserId(userId);
		//3.数据封装
		Map<String,Object> map=new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		return map;
	}
	@Override
	public int updateObject(SysUser entity, Integer[] roleIds) {
		//1.参数有效性验证
				if(entity==null)
					throw new IllegalArgumentException("保存对象不能为空");
				if(StringUtils.isEmpty(entity.getUsername()))
					throw new IllegalArgumentException("用户名不能为空");
				if(roleIds==null||roleIds.length==0)
					throw new IllegalArgumentException("必须为其指定角色");
				//其它验证自己实现，例如用户名已经存在，密码长度，...
				//2.更新用户自身信息
				int rows=sysUserDao.updateObject(entity);
				//3.保存用户与角色关系数据
				sysUserRoleDao.deleteObjectsByUserId(entity.getId());
				sysUserRoleDao.insertObjects(entity.getId(),
						roleIds);
				//4.返回结果
				return rows;
	}

}
