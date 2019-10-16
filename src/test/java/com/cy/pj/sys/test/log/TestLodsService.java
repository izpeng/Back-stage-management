package com.cy.pj.sys.test.log;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLodsService {
	@Autowired
	private SysLogService sysLogService;
	@Test
	public void testLodsService() {
		PageObject<SysLog> findPageObjects = sysLogService.findPageObjects("admin", 3);
		System.out.println(findPageObjects);



	}
}
