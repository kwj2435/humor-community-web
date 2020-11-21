package com.web.api.security.dto;

import com.web.api.user.dto.UserVO;

public class AuthenticationResponse {
	private String accessToken;
	private String refreshToken;
	private UserVO userInfo;
	
	public AuthenticationResponse(String accessToken,String refreshToken,UserVO userVO) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.userInfo = userVO;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public UserVO getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserVO userInfo) {
		this.userInfo = userInfo;
	}
	
}
