package com.lnt.sb.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/addition")
public class AdditionController {
    
	@GetMapping(value = "/add")
	public long getAddition(int x, int y) {
		return x+y;
	}

}
