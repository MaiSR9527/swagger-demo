package com.msr.demo.swagger_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: maishuren
 * @Date: 2019/11/16 13:40
 */
@RestController
public class SwaggerController {

    @GetMapping("/hello")
    public List hello() {
        return Arrays.asList(1, 2, 3, 4);
    }
}
