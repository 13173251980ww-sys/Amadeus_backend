package com.amadeus.controller;

import com.amadeus.pojo.Result;
import com.amadeus.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件:{}",file);
        if(!file.isEmpty()){
            //获取原文件名
            String originalFilename=file.getOriginalFilename();
            //获取原扩展名
            String extName=originalFilename.substring(originalFilename.lastIndexOf("."));
            //随机UUID生成唯一文件名
            String uniqueFileName= UUID.randomUUID().toString().replace("-","")+extName;
            //上传文件
            String url = aliyunOSSOperator.upload(file.getBytes(), uniqueFileName);
            return Result.success(url);
        }
        return Result.error(400,"上传失败");
    }
}
