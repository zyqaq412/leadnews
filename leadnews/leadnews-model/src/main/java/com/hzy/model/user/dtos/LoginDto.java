package com.hzy.model.user.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title: LoginDto
 * @Author zxwyhzy
 * @Date: 2023/9/6 20:01
 * @Version 1.0
 */

@Data
public class LoginDto {
    /**
     *  手机号
     */
    @ApiModelProperty(value = "手机号",required = true)
    private String phone;
    /**
     *  密码
     */
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
