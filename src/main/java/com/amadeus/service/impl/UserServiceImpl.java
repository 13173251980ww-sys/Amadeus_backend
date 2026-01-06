package com.amadeus.service.impl;


import com.amadeus.mapper.UserMapper;
import com.amadeus.pojo.User;
import com.amadeus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }
}
