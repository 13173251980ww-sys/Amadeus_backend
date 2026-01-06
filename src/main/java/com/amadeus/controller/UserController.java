package com.amadeus.controller;

import com.amadeus.pojo.User;
import com.amadeus.pojo.Result;
import com.amadeus.service.UserService;
import com.amadeus.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User loginInfo){
        User user = userService.login(loginInfo.getUsername(), loginInfo.getPassword());

        if(user!=null){
            //登录成功,生成jwt令牌
            Map<String,Object> dataMap=new HashMap<>();
            dataMap.put("id",user.getId());
            dataMap.put("username",user.getUsername());

            String jwt= JwtUtil.generateJwt(dataMap);

            user.setToken(jwt);

            log.info("用户{}登录成功",user.getUsername());
            return Result.success(user);
        }else{
            log.info("登录失败");
            return Result.error(401,"用户名或密码错误");
        }
    }
}
