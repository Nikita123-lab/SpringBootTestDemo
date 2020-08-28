package com.lnt.sb.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {

	private static final Logger logger = Logger.getLogger(EmployeeController.class);
	private final String className = EmployeeController.class.getName();


	public long getCurrentTime() {
		return System.currentTimeMillis();
	}


	
	@GetMapping(value = "/getApiTest")
	public String getApiTest() {
		JSONObject json=new JSONObject();
		json.put("message", "success");
		return json.toString();
	}

	@GetMapping(value = "/getEmployeeInfo")
	public String getEmployeeInfo() {
		JSONObject json=new JSONObject();
		json.put("Employee_ID", "1");
		json.put("Employee_Name", "ABC");
		return json.toString();
	}

	
	
}


