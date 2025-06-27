package com.anchal.blogApp.Service.Impl;

import com.anchal.blogApp.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication authenticate(String email, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @Override
    public String jwtToken(Authentication authentication) {
        return "";
    }
}
