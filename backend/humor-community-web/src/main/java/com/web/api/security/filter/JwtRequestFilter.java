package com.web.api.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.web.api.security.service.JwtUserDetailsService;
import com.web.api.security.service.JwtUtilService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	@Autowired
	private JwtUtilService jwtUtilService;
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	// 1. Client 요청 헤더에서 Authorization 부분을 추출
	// 2. Jwt 존재시 토큰 유효성 검사
	// 3. Jwt 미존재시 로그인 절차 이후 토큰 발행
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain) throws ServletException,IOException{
		final String token = request.getHeader("X-AUTO-TOKEN");
		
		if (token != null) {
            Authentication authentication = jwtUtilService.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
		//발행된 Authentication을 가지고 Manager로 넘겨서 검증절차를 진행한다.
		filterChain.doFilter(request, response);
	}
}
