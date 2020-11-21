package com.web.api.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.api.security.dto.AuthenticationResponse;
import com.web.api.security.service.JwtUserDetailsService;
import com.web.api.security.service.JwtUtilService;
import com.web.api.user.dto.UserVO;
import com.web.api.user.entity.UserInfo;
import com.web.api.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/api/user")
@Api(value="UserController V1")
public class UserController {
	
	@Autowired AuthenticationManager authenticationManager;
	@Autowired private UserService userService;
	@Autowired private JwtUtilService jwtUtilService; 
	@Autowired private JwtUserDetailsService jwtUserDetailsService;
	
	@ApiOperation("회원가입 처리")
	@PostMapping("/signup")
	public ResponseEntity<UserInfo> setUserInfo(UserVO userVO) throws IllegalArgumentException{
		
		UserInfo userInfo = userService.setUserInfo(userVO);
		
		return new ResponseEntity<UserInfo>(userInfo,HttpStatus.OK);
	}
	@ApiOperation("로그인 처리")
	@PostMapping("/login")
	public ResponseEntity<Object> doLogin(UserVO userVO) throws BadCredentialsException,InternalAuthenticationServiceException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userVO.getUserEmail(),userVO.getUserPassword()));
			/*
			 * authenticate - UsernamePasswordAuthenticationToken을 기본 AuthenticationProvider로 전달
			 * UserDetailsService를 사용하여 사용자 이름을 기반으로 사용자를 가져오고 해당 사용자의 비밀번호를 인증 토큰의 비밀번호와 비교
			 * Spring Security는 하나의 실제 AuthenticationManager만 구현
			 * */
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password",e);
		}
	
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userVO.getUserEmail());
		final String accessToken = jwtUtilService.createAccessToken(userDetails.getUsername(),"USER");	//유저이름, 권한List를 파라미터로 넣음
		final String refreshToken = jwtUtilService.createRefreshToken(userDetails.getUsername(),"USER");
		final UserVO responseUserInfo = userService.getUserInfoByUserEmail(userVO.getUserEmail());
		
		return ResponseEntity.ok(new AuthenticationResponse(accessToken,refreshToken,responseUserInfo));
	}
	@ApiOperation("이메일 중복 확인")
	@GetMapping("/emailCheck")
	public ResponseEntity<Map<String,Integer>> checkEmailUseable(@RequestParam("userEmail") String userEmail){
		System.out.println(userEmail);
		Map<String,Integer> result = new HashMap<String,Integer>();
		
		if(userService.checkEmailUseable(userEmail) != null) {
			result.put("result", 1);
		}else {
			result.put("result", 0);
		}
		
		return ResponseEntity.ok(result);
	}
}
