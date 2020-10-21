package com.web.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.api.user.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Integer>{
	public UserInfo findByUserEmail(String userEmail);
}
