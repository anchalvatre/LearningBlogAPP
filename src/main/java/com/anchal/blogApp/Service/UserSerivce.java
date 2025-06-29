package com.anchal.blogApp.Service;


import com.anchal.blogApp.Model.Entities.Users;

import java.util.Optional;

public interface UserSerivce {

    Optional<Users> findByEmail(String email);
    Optional<Users> findByUserName(String userName);

}
