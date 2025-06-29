package com.anchal.blogApp.Service;

import com.anchal.blogApp.Model.Entities.Users;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AuthenticationService {

    Authentication authenticate(String email, String password);

    String jwtToken(Authentication authentication);

    String extractToken(HttpServletRequest request);

    UserDetails validToken(String token);
}
