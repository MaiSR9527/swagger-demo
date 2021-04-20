package com.msr.demo.swagger.controller;

import com.msr.demo.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Site: https://www.maishuren.top
 * @Author: maishuren
 * @Date: 2019/11/16 14:24
 */
@Slf4j
@Api(tags = "用户相关相关的API")
@RestController
public class UserController {

    @ApiOperation(value = "获取用户信息",notes="添加用户信息")
    @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    @ApiResponse(code = 200,message = "请求成功",response = String.class)
    @PostMapping("/user")
    public String addUser(@RequestBody User user) {
        log.info("POST请求，接收到参数:{}", user.toString());
        return user.toString();
    }

    @ApiOperation(value = "更新用户", notes = "根据User对象更新用户")
    @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    @ApiResponse(code = 200,message = "请求成功",response = Boolean.class)
    @PutMapping("/user")
    public boolean updateUser(@RequestBody User user) {
        log.info("UPDATE请求，请求参数:{}", user);
        return true;
    }

    @ApiOperation(value = "删除用户", notes = "根据User对象删除用户")
    @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    @ApiResponse(code = 200,message = "请求成功",response = Boolean.class)
    @DeleteMapping("/user")
    public boolean deleteUser(@RequestBody User user) {
        log.info("DELETE请求，请求参数:{}", user);
        return true;
    }

    @ApiOperation(value = "获取用户列表", notes = "根据User对象查询用户信息")
    @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    @ApiResponse(code = 200,message = "请求成功",response = Boolean.class)
    @GetMapping("/user")
    public User findByUser(User user) {
        log.info("GET请求，请求参数:{}", user);
        return User.builder()
                .id(1)
                .age(18)
                .name("小明")
                .build();
    }
}
