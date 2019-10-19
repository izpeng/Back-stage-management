package com.cy.pj.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SpringShiroConfig {
	
	@Bean
	 public CookieRememberMeManager rememberMeManager() {
		 CookieRememberMeManager cManager=
		 new CookieRememberMeManager();
SimpleCookie cookie=new SimpleCookie("rememberMe");
		 cookie.setMaxAge(10*60);
		 cManager.setCookie(cookie);
		 return cManager;
	 }
	
	@Bean
	public CacheManager shiroCacheManager(){
		 return new MemoryConstrainedCacheManager();
	}
	
	
	@Bean
	public org.apache.shiro.mgt.SecurityManager securityManager(Realm realm,CacheManager cacheManager,CookieRememberMeManager rememberManager) {
		DefaultWebSecurityManager sManager=
				new DefaultWebSecurityManager();
		 sManager.setRealm(realm);
		 sManager.setCacheManager(cacheManager);
		 sManager.setRememberMeManager(rememberManager);
		return sManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactory (
			@Autowired org.apache.shiro.mgt.SecurityManager securityManager) {
		ShiroFilterFactoryBean sfBean=
				new ShiroFilterFactoryBean();
		sfBean.setSecurityManager(securityManager);
		sfBean.setLoginUrl("/doLoginUI");
		//定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		LinkedHashMap<String,String> map=
				new LinkedHashMap<>();
		//静态资源允许匿名访问:"anon"
		map.put("/bower_components/**","anon");
		map.put("/build/**","anon");
		map.put("/dist/**","anon");
		map.put("/plugins/**","anon");
		map.put("/user/doLogin","anon");
		
		map.put("/doLogout","logout");
		//除了匿名访问的资源,其它都要认证("authc")后访问
		 map.put("/**","user");//authc
		sfBean.setFilterChainDefinitionMap(map);
		return sfBean;
	}
	
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
			 return new LifecycleBeanPostProcessor();
	}
	
	@DependsOn("lifecycleBeanPostProcessor")
	@Bean
	public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator() {
			 return new DefaultAdvisorAutoProxyCreator();
	}
	
	 @Bean
	 public AuthorizationAttributeSourceAdvisor 
	 newAuthorizationAttributeSourceAdvisor(
	 	    		    @Autowired org.apache.shiro.mgt.SecurityManager securityManager) {
	 		        AuthorizationAttributeSourceAdvisor advisor=
	 				new AuthorizationAttributeSourceAdvisor();
	 advisor.setSecurityManager(securityManager);
	 	return advisor;
	 }
	 
	 
	 
}
