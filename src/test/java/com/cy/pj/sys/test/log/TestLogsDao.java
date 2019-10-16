package com.cy.pj.sys.test.log;

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
public class TestLogsDao {
	@Autowired
	private SysLogDao sysLogDao;
	@Test
	public void testLogsDao() {
		List<SysLog> findPageObjects = sysLogDao.findPageObjects("ad", 0, 3);
		for (SysLog sysLog : findPageObjects) {
			System.out.println(sysLog);
		}
	}
}
