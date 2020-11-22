package com.web.api.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.web.api.common.exception.CustomJwtExpiredException;
import com.web.api.security.service.JwtUserDetailsService;
import com.web.api.security.service.JwtUtilService;

import io.jsonwebtoken.ExpiredJwtException;
import javassist.tools.web.BadHttpRequest;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtilService jwtUtilService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String accessToken = request.getHeader("X-AUTH-ACCESSTOKEN");
        final String refreshToken = request.getHeader("X-AUTH-REFRESHTOKEN");
        String refreshTokenEmail = null;
        
        if(accessToken != null) {
            try {
                if(jwtUtilService.isTokenExpired(accessToken)) {
                    Authentication authentication = jwtUtilService.getAuthentication(accessToken,refreshToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }catch(ExpiredJwtException e) {
                try {
                    if(refreshToken != null && jwtUtilService.isTokenExpired(refreshToken)) {
                        refreshTokenEmail = jwtUtilService.getUserEmailInToken(refreshToken);
                        String newAccessToken = jwtUtilService.createAccessToken(refreshTokenEmail, "USER");
                        System.out.println("----------- Created New Access Token ------------");
                        response.setHeader("accessToken", newAccessToken);
                        Authentication authentication = jwtUtilService.getAuthentication(accessToken,refreshToken);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }catch(ExpiredJwtException i) {
                    throw new CustomJwtExpiredException("AccessToken, RefreshToken Expired");
                }catch(InternalAuthenticationServiceException j) {
                    throw new CustomJwtExpiredException("test");
                }
            }
        }
        // 발행된 Authentication을 가지고 Manager로 넘겨서 검증절차를 진행한다.
        filterChain.doFilter(request, response);
    }
}
