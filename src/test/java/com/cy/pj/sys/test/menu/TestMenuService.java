package com.cy.pj.sys.test.menu;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.sys.service.SysMenuService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMenuService {
	@Autowired
	private SysMenuService sysMenuService;
	@Test
	public void testLodsService() {
		List<Map<String, Object>> findObjects = sysMenuService.findObjects();
		for (Map<String, Object> map : findObjects) {
			System.out.println(map);
		}
	}
}
