package com.anchal.blogApp.Service.Impl;

import com.anchal.blogApp.Model.Entities.Users;
import com.anchal.blogApp.Repository.UserRepo;
import com.anchal.blogApp.Service.UserSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserSerivce {

    private final UserRepo userRepo;

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Optional<Users> findByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }
}
