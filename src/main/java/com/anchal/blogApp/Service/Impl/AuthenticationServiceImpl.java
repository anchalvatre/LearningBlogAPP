package com.anchal.blogApp.Service.Impl;

import com.anchal.blogApp.Model.Entities.Users;
import com.anchal.blogApp.Security.UserDetailsSerivce;
import com.anchal.blogApp.Service.AuthenticationService;
import com.anchal.blogApp.Service.UserSerivce;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${jwt.secret}")
    private String secertKey;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsSerivce userDetailsSerivce;

    @Override
    public Authentication authenticate(String email, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @Override
    public String jwtToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 50000))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    @Override
    public String extractToken(HttpServletRequest request) {
        return request.getHeader("Authorization").substring(7);
    }

    @Override
    public UserDetails validToken(String token) {
       String userName = getUserName(token);
       log.error("user name is " + userName);
        return userDetailsSerivce.loadUserByUsername(userName);
    }

    public String getUserName(String token) {
        log.error("enter getUserName");

        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token){
        log.error("enter extract Claims");
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSigningKey(){
       byte[] keys = secertKey.getBytes();
       log.error("secert key is " + Keys.hmacShaKeyFor(keys));
       return Keys.hmacShaKeyFor(keys);
    }

}
