package com.web.api.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.api.user.dto.UserVO;
import com.web.api.user.entity.UserInfo;
import com.web.api.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserVO getUserInfoByUserEmail(String userEmail) {
		UserVO userVO = new UserVO();
		UserInfo user = userRepository.findByUserEmail(userEmail);
		userVO.setUserEmail(user.getUserEmail());
		userVO.setUserPassword(user.getUserPassword());
		userVO.setUserNickname(user.getUserNickname());
		userVO.setUserRoll(user.getUserRoll());
		return userVO;
	}
	public UserInfo setUserInfo(UserVO userVO) throws IllegalArgumentException{
		
		
		if(userVO.getUserEmail().equals("") || userVO.getUserPassword().equals("")) {
			throw new IllegalArgumentException();
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setUserEmail(userVO.getUserEmail());
		userInfo.setUserPassword(passwordEncoder.encode(userVO.getUserPassword()));
		userInfo.setUserNickname(userVO.getUserNickname());
		userInfo.setUserRoll("USER");
		
		return userRepository.save(userInfo);
	}
}
