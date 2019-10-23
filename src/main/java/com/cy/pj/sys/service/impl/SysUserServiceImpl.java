package com.cy.pj.sys.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.util.ShiroUtils;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.vo.SysUserDeptVo;
//1)@Service描述类表示要交给Spring管理
//2)Spring会创建此类对象,并将对象存储到map容器
//3)Spring Bean容器中的key默认为类名首字母小写.
@Service
@Transactional(readOnly = false,
               isolation = Isolation.READ_COMMITTED,
               rollbackFor =Throwable.class,
               timeout = 30,
               propagation = Propagation.REQUIRED)
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    
    
    @Override
	public int updatePassword(String password, String newPassword,
     String cfgPassword) {
		//1.判定新密码与密码确认是否相同
		if(StringUtils.isEmpty(newPassword))
		throw new IllegalArgumentException("新密码不能为空");
		if(StringUtils.isEmpty(cfgPassword))
		throw new IllegalArgumentException("确认密码不能为空");
		if(!newPassword.equals(cfgPassword))
		throw new IllegalArgumentException("两次输入的密码不相等");
		//2.判定原密码是否正确
		if(StringUtils.isEmpty(password))
		throw new IllegalArgumentException("原密码不能为空");
		//获取登陆用户
		SysUser user=ShiroUtils.getUser();
		SimpleHash sh=new SimpleHash("MD5",
		password, user.getSalt(), 1);
		if(!user.getPassword().equals(sh.toHex()))
		throw new IllegalArgumentException("原密码不正确");
		//3.对新密码进行加密
		String salt=UUID.randomUUID().toString();
		sh=new SimpleHash("MD5",newPassword,salt, 1);
		//4.将新密码加密以后的结果更新到数据库
		int rows=sysUserDao.updatePassword(sh.toHex(), salt,user.getId());
		if(rows==0)
		throw new ServiceException("修改失败");
		return rows;
	}
    
    //@Cacheable描述方法时，用于告诉spring框架，此方法的返回结果要进行cache
    //userCache表示一个cache对象的名称
    //key默认为方法的参数的组合
    @Cacheable(value = "userCache")
    @Transactional(readOnly = true)
    @Override
    public Map<String,Object> findObjectById(Integer id) {
    	System.out.println("==findObjectById==");
    	//1.参数有效性校验
    	if(id==null||id<1)
    		throw new IllegalArgumentException("id值无效");
    	//2.基于id查询用户以及对应的部门信息
    	SysUserDeptVo user=
    	sysUserDao.findObjectById(id);
    	if(user==null)
    		throw new ServiceException("用户不存在");
    	//3.查询用户对应的角色id
    	List<Integer> roleIds=
    	sysUserRoleDao.findRoleIdsByUserId(id);
    	//4.封装两次查询结果
    	Map<String,Object> map=new HashMap<>();
    	map.put("user", user);
    	map.put("roleIds", roleIds);
    	
    	return map;
    }
    //@CacheEvict表示清空缓存
    //在本应用中清空cache中key为指定id的对象
    //#entity.id表示获取参数entity对象的id值
    @CacheEvict(value="userCache",
    		    key = "#entity.id")
    @Override
    public int updateObject(SysUser entity, 
    		Integer[] roleIds) {
    	//1.参数检验
    	if(entity==null)
    		throw new IllegalArgumentException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getUsername()))
    		throw new IllegalArgumentException("用户名不能为空");
    	if(roleIds==null||roleIds.length==0)
    		throw new ServiceException("必须为用户分配角色");
    	//2.保存用户自身信息
    	int rows=sysUserDao.updateObject(entity);
    	//3.保存用户和角色关系数据
    	sysUserRoleDao.deleteObjectsByUserId(entity.getId());
    	sysUserRoleDao.insertObjects(
    			entity.getId(),roleIds);
    	//4.返回结果
    	return rows;
    }
    @RequiresPermissions("sys:user:save")
    @Override
    public int saveObject(SysUser entity, 
    		Integer[] roleIds) {
    	//1.参数检验
    	if(entity==null)
    		throw new IllegalArgumentException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getUsername()))
    		throw new IllegalArgumentException("用户名不能为空");
    	if(StringUtils.isEmpty(entity.getPassword()))
    		throw new IllegalArgumentException("密码不能为空");
    	if(roleIds==null||roleIds.length==0)
    		throw new ServiceException("必须为用户分配角色");
    	//2.保存用户自身信息
    	//2.1对密码进行md5盐值加密
    	//获取一个盐值,这个值使用随机的字符串
    	String salt=UUID.randomUUID().toString();
    	//借助shiro框架中的API对应密码进行盐值加密
    	SimpleHash sh=new SimpleHash(
    			"MD5",//algorithmName 算法名(MD5算法是一种消息摘要算法)
    			entity.getPassword(),//source 未加密的密码
    			salt,//盐
    			1);//hashIterations表示加密次数
    	//将加密结果转成16进制
    	String pwd=sh.toHex();
    	//2.2将盐值和密码存储SysUser对象
    	entity.setSalt(salt);
    	entity.setPassword(pwd);
    	//2.3将SysUser对象持久化到数据库
    	int rows=sysUserDao.insertObject(entity);
    	//3.保存用户和角色关系数据
    	sysUserRoleDao.insertObjects(entity.getId(),roleIds);
    	//4.返回结果
    	return rows;
    }
    /**
     * @RequiresPermissions 告诉shiro框架
          *  此方法的访问需要授权，此时系统底层对象
       需要将"sys:user:update"提交给SecurityManager
       ,这个对象会基于登录用户信息获得用户权限,然后
       检测用户是否有访问这个资源的权限,假如有则授权访问
       ,没有则抛出异常.
     */
    @RequiresPermissions("sys:user:update")
    @RequiredLog("禁用启用")
    @Override
    public int validById(Integer id, 
    		Integer valid, String modifiedUser) {
    	//1.验证参数有效性
    	if(id==null||id<1)
    		throw new IllegalArgumentException("id值无效");
    	if(valid==null||valid!=1&&valid!=0)
    		throw new IllegalArgumentException("状态不正确");
    	//...	
    	//2.更新用户状态,并对其结果进行判定
    	int rows=sysUserDao.validById(id, valid, modifiedUser);
    	if(rows==0)
    		throw new ServiceException("记录可能已经不存在");
    	//3.返回结果
    	return rows;
    }
    @Transactional(readOnly = true)
    @RequiredLog("用户分页查询")
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
    	System.out.println("user.thread.name:"+Thread.currentThread().getName());
	    //1.参数校验
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("页码值不正确");
		//2.统计总记录数并校验
		int rowCount=
		sysUserDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.查询当前页记录
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysUserDeptVo> records=
		sysUserDao.findPageObjects(username,
				startIndex, pageSize);
		//4.封装查询结果
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}

}
