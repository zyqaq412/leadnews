package com.hzy.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.user.dtos.LoginDto;
import com.hzy.model.user.pojos.ApUser;

/**
 * @title: ApUserService
 * @Author zxwyhzy
 * @Date: 2023/9/6 20:13
 * @Version 1.0
 */
public interface ApUserService extends IService<ApUser> {

    ResponseResult login(LoginDto dto);
}
