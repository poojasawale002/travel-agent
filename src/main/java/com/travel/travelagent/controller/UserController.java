package com.travel.travelagent.controller;

import org.springframework.web.bind.annotation.RestController;

import com.travel.travelagent.entity.User;
import com.travel.travelagent.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/tripusers")
	public User saveUser(@RequestBody User user) {
		System.out.println("REGISTER API HIT");
		return userService.saveUser(user);
	}
	
	
}
