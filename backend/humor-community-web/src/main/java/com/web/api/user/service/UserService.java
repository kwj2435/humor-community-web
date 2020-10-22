package com.web.api.user.service;

import org.springframework.stereotype.Service;

import com.web.api.user.dto.UserVO;
import com.web.api.user.entity.UserInfo;
import com.web.api.user.repository.UserRepository;

@Service
public class UserService {
	
	public UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserVO getUserInfoByUserEmail(String userEmail) {
		UserVO userVO = new UserVO();
		UserInfo user = userRepository.findByUserEmail(userEmail);
		userVO.setUserEmail(user.getUserEmail());
		userVO.setUserPassword(user.getUserPassword());
		
		return userVO;
	}
	public UserInfo setUserInfo(UserVO userVO) throws IllegalArgumentException{
		if(userVO.getUserEmail().equals("") || userVO.getUserPassword().equals("")) {
			throw new IllegalArgumentException();
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setUserEmail(userVO.getUserEmail());
		userInfo.setUserPassword(userVO.getUserPassword());
		userInfo.setUserNickname(userVO.getUserNickname());
		userInfo.setUserRoll("USER");
		
		return userRepository.save(userInfo);
	}
}
