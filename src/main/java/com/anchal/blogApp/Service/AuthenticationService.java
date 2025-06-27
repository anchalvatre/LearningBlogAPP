package com.anchal.blogApp.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

public interface AuthenticationService {

    Authentication authenticate(String email, String password);
    String jwtToken(Authentication authentication);

}
