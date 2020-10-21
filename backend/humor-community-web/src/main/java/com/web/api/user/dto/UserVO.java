package com.web.api.user.dto;

public class UserVO {
	private String userEmail;
	private String userPassword;
	private String userNickname;
	private String userRoll;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserRoll() {
		return userRoll;
	}
	public void setUserRoll(String userRoll) {
		this.userRoll = userRoll;
	}
}
