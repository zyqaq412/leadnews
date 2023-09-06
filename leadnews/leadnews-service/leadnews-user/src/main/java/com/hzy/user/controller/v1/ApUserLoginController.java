package com.hzy.user.controller.v1;

import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.user.dtos.LoginDto;
import com.hzy.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: ApUserLoginController
 * @Author zxwyhzy
 * @Date: 2023/9/6 20:01
 * @Version 1.0
 */

@RestController
@RequestMapping("/api/v1/login")
@Api(value = "app端用户登录" ,tags = "app端用户登录")
public class ApUserLoginController {
    @Autowired
    private ApUserService apUserService;

    @PostMapping("/login_auth")
    @ApiOperation("用户登录")
    public ResponseResult login(@RequestBody LoginDto dto){

        return apUserService.login(dto);
    }
}
