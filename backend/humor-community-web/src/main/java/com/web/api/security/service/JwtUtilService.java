package com.web.api.security.service;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtilService {

    final private long ACCESS_TOKEN_TIME = 1000L;
    final private long REFRESH_TOKEN_TIME = 1000L;
    private JwtUserDetailsService jwtUserDetailsService;
    private String SECRET_KEY = "communitySecure";

    // 1. 조회된 유저정보를 기반으로 로그인정보 검증 위해 검증토큰 발행
    public Authentication getAuthentication(String accessToken,String refreshToken) {
        
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(this.getUserEmailInToken(accessToken));
        // 만들어진 UsernamePasswordAuthenticationToken은 검증을 위해 AuthenticationManager의 인스턴스로
        // 전달된다.
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에 담겨있는 유저이메일 정보를 가져온다.
    public String getUserEmailInToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public String createToken(String userEmail, String roles, long validTime) {
        Claims claims = Jwts.claims().setSubject(userEmail);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(new Date(now.getTime() + validTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
    public Boolean isTokenExpired(String token){
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }
    public Claims extractAllClaims(String accessToken) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(accessToken)
                .getBody();
    }
    public String createAccessToken(String userEmail, String roles) {
        return createToken(userEmail, roles, ACCESS_TOKEN_TIME);
    }

    public String createRefreshToken(String userEmail, String roles) {
        return createToken(userEmail, roles, REFRESH_TOKEN_TIME);
    }
}
