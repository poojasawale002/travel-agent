package com.travel.travelagent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travelagent.dto.LoginRequest;
import com.travel.travelagent.jwt.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {

	    System.out.println("***** LOGIN API HIT *****");

	    try {
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        request.getEmail(),
	                        request.getPassword()));

	        System.out.println("Authentication Successful");

	        return jwtService.generateToken(request.getEmail());

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}
}
