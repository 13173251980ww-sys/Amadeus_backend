package com.amadeus.mapper;

import com.amadeus.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper{

    @Select("select id,username from users where username = #{username} and password = #{password}")
    public User login(String username, String password);
}

