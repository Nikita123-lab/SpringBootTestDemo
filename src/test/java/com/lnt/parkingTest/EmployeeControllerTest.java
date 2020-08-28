package com.lnt.parkingTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lnt.sb.controller.EmployeeController;

@SpringBootTest(classes = EmployeeControllerTest.class)
@RunWith(SpringRunner.class)

public class EmployeeControllerTest {

	@Test
	public void getApiTestCase() {
		EmployeeController emp = new EmployeeController();
		String result=emp.getApiTest();
		assertNotNull(result);
		
	}

	@Test
	public void getEmployeeInfoTest() {
		EmployeeController emp = new EmployeeController();
		String result=emp.getEmployeeInfo();
		assertNotNull(result);
	}

}