package com.amadeus.service;

import com.amadeus.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public User login(String username, String password);
}
