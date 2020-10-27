package com.web.api.security.dto;

public class AuthenticationResponse {
	private String token;
	private String userEmail;
	
	public AuthenticationResponse(String token,String userEmail) {
		this.token = token;
		this.userEmail = userEmail;
	}
	public String getToken() {
		return token;
	}
	public String getUserEmail() {
		return userEmail;
	}
}
