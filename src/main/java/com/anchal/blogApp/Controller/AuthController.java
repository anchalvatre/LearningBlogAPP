package com.anchal.blogApp.Controller;

import com.anchal.blogApp.Model.DTO.AuthResponse;
import com.anchal.blogApp.Model.DTO.LoginRequest;
import com.anchal.blogApp.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){

        Authentication auth = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String jwt = authenticationService.jwtToken(auth);

        //TODO - first try with authentication with email and password only by pssing hardcoded string and then try jwt
        AuthResponse authResponse = AuthResponse.builder().
                token(jwt).
                expireIn(5000).
                build();

        return ResponseEntity.ok(authResponse);
    }
}
