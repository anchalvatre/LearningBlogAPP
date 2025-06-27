package com.anchal.blogApp.Security;

import com.anchal.blogApp.Model.Entities.Users;
import com.anchal.blogApp.Service.UserSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsSerivce implements UserDetailsService {

    private final UserSerivce userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users users = userService.findByEmail(email).orElseThrow(() -> new InternalAuthenticationServiceException("Usersdfdf not Found"));
        return User.builder().
                username(users.getEmail()).
                password(users.getPassword()).
                build();

    }
}
