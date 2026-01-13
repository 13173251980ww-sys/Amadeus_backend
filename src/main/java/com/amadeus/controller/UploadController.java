package com.amadeus.controller;

import com.aliyun.oss.AliyunOSSOperator;
import com.amadeus.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件：{}",file.getOriginalFilename());
        //调用aliyun OSS进行文件上传
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        //返回结果
        return Result.success(url);
    }
}



