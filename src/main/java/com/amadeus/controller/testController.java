package com.amadeus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/test")
@Slf4j
public class testController {

    @GetMapping
    public String test(){
        log.info("进入测试控制器");
        return "测试控制器";
    }
}
