package com.web.api.security.dto;

import com.web.api.user.dto.UserVO;

public class AuthenticationResponse {
	private String token;
	private UserVO userInfo;
	
	public AuthenticationResponse(String token,UserVO userInfo) {
		this.token = token;
		this.userInfo = userInfo;
	}
	public String getToken() {
		return token;
	}
	public UserVO getUserInfo() {
		return userInfo;
	}
}
