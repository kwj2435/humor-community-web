package com.web.api.security.service;


import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtilService {
	private JwtUserDetailsService jwtUserDetailsService;
	private String SECRET_KEY = "communitySecure";
	private long tokenValidTime = 30 * 60 * 1000L;
	
	//1. 조회된 유저정보를 기반으로 로그인정보 검증 위해 검증토큰 발행
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(this.getUserEmailInToken(token));
		//만들어진 UsernamePasswordAuthenticationToken은 검증을 위해 AuthenticationManager의 인스턴스로 전달된다.
		return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
	}
	//토큰에 담겨있는 유저이메일 정보를 가져온다.
	public String getUserEmailInToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}
	public String createToken(String userEmail,String roles) {
		Claims claims = Jwts.claims().setSubject(userEmail);
		claims.put("roles", roles);
		Date now = new Date();
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + tokenValidTime))
				.signWith(SignatureAlgorithm.HS256,SECRET_KEY)
				.compact();
	}
}
