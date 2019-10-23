package com.cy.pj.sys.log;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLogDaoTests {
      @Autowired
	  private SysLogDao sysLogDao;
	  @Test
      public void testDeleteObjects() {
    	  int rows=sysLogDao.deleteObjects(30,31);
    	  System.out.println("delete rows "+rows);
      }
      @Test
      public void testGetRowCount() {
    	  int rows=sysLogDao.getRowCount("admin");
    	  System.out.println("rows="+rows);
      }
      @Test
      public void testFindPageObjects() {
    	  List<SysLog> list=sysLogDao.findPageObjects("ad",
    			  0, 3);
    	  for(SysLog log:list) {
    		  System.out.println(log);
    	  }
      }
}









