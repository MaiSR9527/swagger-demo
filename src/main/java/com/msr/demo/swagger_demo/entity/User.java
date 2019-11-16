package com.msr.demo.swagger_demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: maishuren
 * @Date: 2019/11/16 14:52
 */
@ApiModel("用户实体")
@Data
@AllArgsConstructor
public class User implements Serializable {
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("年龄")
    private String age;
}
