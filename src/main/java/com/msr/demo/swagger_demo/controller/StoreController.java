package com.msr.demo.swagger_demo.controller;

import com.msr.demo.swagger_demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: maishuren
 * @Date: 2019/11/16 14:24
 */
@Api(tags = "store相关的请求")
@RestController
public class StoreController {

    @ApiOperation("获取用户信息")
    @GetMapping("/user")
    public User getUser(){
        return new User("zhangsan","18");
    }
}
