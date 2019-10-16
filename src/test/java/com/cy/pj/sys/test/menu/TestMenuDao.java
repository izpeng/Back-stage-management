package com.cy.pj.sys.test.menu;


import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.sys.dao.SysMenuDao;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMenuDao {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Test
public void testMenuDao(){
		List<Map<String, Object>> findObjects = sysMenuDao.findObjects();
		for (Map<String, Object> map : findObjects) {
			System.out.println(map.toString());
		}
}
}
