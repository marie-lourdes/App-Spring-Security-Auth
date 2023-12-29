package com.example.springsecurityauth.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
	
	@GetMapping("/user")
	public String getUser() {
		return "Welcome, User";
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "Welcome, Admin";
	}
}
