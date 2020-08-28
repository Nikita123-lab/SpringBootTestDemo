package com.lnt.parkingTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lnt.sb.controller.AdditionController;

@SpringBootTest(classes= AdditionControllerTest.class)
@RunWith(SpringRunner.class)
public class AdditionControllerTest {
    
	@Test
	public void getAdditionTest() {
		AdditionController addition=new AdditionController();
	     assertEquals(11,addition.getAddition(5, 6));
	}
}
