package com.web.api.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.api.user.dto.UserVO;
import com.web.api.user.entity.UserInfo;
import com.web.api.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<UserInfo> setUserInfo(UserVO userVO) throws Exception{
		UserInfo userInfo = userService.setUserInfo(userVO);
		return new ResponseEntity<UserInfo>(userInfo,HttpStatus.OK);
	}
	/*
	@PostMapping("/login")
	public ResponseEntity<UserInfo> doLogin(UserVO userVO){
		return new ResponseEntity<UserInfo>
	}
	*/
}
