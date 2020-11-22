package com.web.api.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.api.user.dto.UserVO;
import com.web.api.user.service.UserService;

//UserDetailsService는 DB에서 유저 정보를 가져오는 역할을 한다.
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    // * Client 로그인시 비교할 데이터를 DB에서 검색하여 User 객체로 반환 ( 이후 Security에서 자동으로 이메일,비밀번호 비교
    // )
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        UserVO userVO = userService.getUserInfoByUserEmail(userEmail);

        if (userVO.getUserEmail().equals(userEmail)) {
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(userVO.getUserEmail(), userVO.getUserPassword(), roles);
        } else {
            throw new UsernameNotFoundException("User not found :" + userEmail);
        }

    }
}
