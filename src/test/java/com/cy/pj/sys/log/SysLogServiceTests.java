package com.cy.pj.sys.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLogServiceTests {
	  @Autowired
	  private SysLogService sysLogService;
	  @Test
	  public void testFindPageObjects() {
		  PageObject<SysLog> pageObject=
		  sysLogService.findPageObjects(null,1);
		  System.out.println(pageObject);
	  }
}







