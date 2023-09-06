package com.hzy.model.user.dtos;

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
    private String phone;
    /**
     *  密码
     */
    private String password;
}
