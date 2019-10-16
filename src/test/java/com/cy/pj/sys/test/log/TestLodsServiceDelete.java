package com.cy.pj.sys.test.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.sys.dao.SysLogDao;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLodsServiceDelete {
	@Autowired
	private SysLogDao sysLogDao;
	@Test
	public  void testLodsServiceDelete() {
		int deleteObjects = sysLogDao.deleteObjects(1,2,3,4);
		System.out.println(deleteObjects);
	}
}
