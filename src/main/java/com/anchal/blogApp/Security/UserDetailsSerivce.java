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
    public UserDetails loadUserByUsername(String emailOrUserName) throws UsernameNotFoundException {

        if (!userService.findByEmail(emailOrUserName).isEmpty()) {
            Users users = userService.findByEmail(emailOrUserName)
                    .orElseThrow(() -> new UsernameNotFoundException("Email not Found : " + emailOrUserName));
            return User.builder().
                    username(users.getEmail()).
                    password(users.getPassword()).
                    build();
        }

        if(!userService.findByUserName(emailOrUserName).isEmpty()){
            return userService.findByUserName(emailOrUserName).map(user -> {
                return User.builder()
                        .username(user.getUserName())
                        .password(user.getPassword())
                        .build();
            }).orElseThrow(()  -> new UsernameNotFoundException("UserName not found : " + emailOrUserName ));
        }

        throw new UsernameNotFoundException("User or email not found " + emailOrUserName);

    }
}
