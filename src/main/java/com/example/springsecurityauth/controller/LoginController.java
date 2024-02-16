package com.example.springsecurityauth.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	private OAuth2AuthorizedClientService authorizedClientService;

	public LoginController(OAuth2AuthorizedClientService authorizedClientService) {
		this.authorizedClientService = authorizedClientService;
	}

	@GetMapping("/user")
	public String getUserPage() {
		return "Welcome, User";
	}

	@GetMapping("/admin")
	public String getAdminPage() {
		return "Welcome, Admin";
	}

	@GetMapping("/")
	public String getUserInfo(Principal principal) {
		StringBuffer userInfo = new StringBuffer();
		if (principal instanceof UsernamePasswordAuthenticationToken) {
			userInfo.append(getUsernamePasswordLoginInfo(principal));
		} else if (principal instanceof OAuth2AuthenticationToken) {
			userInfo.append(getOauth2LoginInfo(principal));
		}
		return userInfo.toString();

	}

	private StringBuffer getUsernamePasswordLoginInfo(Principal user) {
		StringBuffer usernameInfo = new StringBuffer();

		UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
		if (token.isAuthenticated()) {
			User u = (User) token.getPrincipal();
			usernameInfo.append("Welcome, " + u.getUsername());
		} else {
			usernameInfo.append("NA");
		}
		return usernameInfo;
	}

	private StringBuffer getOauth2LoginInfo(Principal user) {
		   StringBuffer protectedInfo = new StringBuffer();
		   
		   OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
		   OAuth2AuthorizedClient authClient = this.authorizedClientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());
		   if(authToken.isAuthenticated()){
		   
		   Map<String,Object> userAttributes = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();
		   
		   String userToken = authClient.getAccessToken().getTokenValue();
		   protectedInfo.append("Welcome, " + userAttributes.get("name")+"<br><br>");
		   protectedInfo.append("e-mail: " + userAttributes.get("email")+"<br><br>");
		   protectedInfo.append("Access Token: " + userToken+"<br><br>");
		   }
		   else{
		   protectedInfo.append("NA");
		   }
		return protectedInfo;
	}

}
