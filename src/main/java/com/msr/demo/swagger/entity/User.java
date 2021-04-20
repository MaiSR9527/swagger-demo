package com.msr.demo.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Site: https://www.maishuren.top
 * @Author: maishuren
 * @Date: 2019/11/16 14:52
 */
@ApiModel("用户实体")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User implements Serializable {
    @ApiModelProperty("用户ID")
    private Integer id;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("登录密码")
    private String password;
    @ApiModelProperty("年龄")
    private Integer age;
}
