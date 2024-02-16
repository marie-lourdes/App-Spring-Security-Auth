package com.example.springsecurityauth.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
	
	@GetMapping("/user")
	public String getUserPage() {
		return "Welcome, User";
	}
	
	@GetMapping("/admin")
	public String getAdminPage() {
		return "Welcome, Admin";
	}

	@GetMapping("/")
	public String getGithubPage() {
		return "Welcome, GitHub user";
	}
}
