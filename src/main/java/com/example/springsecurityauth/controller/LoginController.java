package com.example.springsecurityauth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
	public String getGithubPage( Principal principal) {
		return "Welcome, GitHub user"+ principal.toString();
	}
}
