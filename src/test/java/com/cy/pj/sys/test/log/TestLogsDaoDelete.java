package com.cy.pj.sys.test.log;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.sys.dao.SysLogDao;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLogsDaoDelete {
	@Autowired
	private SysLogDao sysLogDao;
	@Test
	public void testLogsDaoDelete() {
		int deleteObjects = sysLogDao.deleteObjects(8,4,5);
		System.out.println(deleteObjects);
	}

}
