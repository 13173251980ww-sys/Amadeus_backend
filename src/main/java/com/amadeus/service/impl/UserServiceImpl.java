package com.amadeus.service.impl;


import com.amadeus.exception.ServiceException;
import com.amadeus.mapper.UserMapper;
import com.amadeus.pojo.User;
import com.amadeus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        if(!StringUtils.hasText(username) || !StringUtils.hasText(password)){
            throw new ServiceException(400, "用户名或密码不能为空");
        }

        return userMapper.login(username, password);
    }
}
