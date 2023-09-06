package com.hzy.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.common.enums.AppHttpCodeEnum;
import com.hzy.model.user.dtos.LoginDto;
import com.hzy.model.user.pojos.ApUser;
import com.hzy.user.mapper.ApUserMapper;
import com.hzy.user.service.ApUserService;
import com.hzy.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: ApUserServiceImpl
 * @Author zxwyhzy
 * @Date: 2023/9/6 20:14
 * @Version 1.0
 */

@Service
@Transactional // 事务
@Slf4j
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

    /**
     *  app 端登录功能
     * @param dto 登录参数
     * @return
     */
    @Override
    public ResponseResult login(LoginDto dto) {
        // 1.正常登录

        if (StringUtils.isNotBlank(dto.getPhone()) && StringUtils.isNotBlank(dto.getPassword())){
            // 1.1 根据手机号查询用户信息
            LambdaQueryWrapper<ApUser> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(ApUser::getPhone,dto.getPhone());
            ApUser one = getOne(queryWrapper);
            if (one == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"用户信息不存在");
            }
            // 1.2 比对密码
            // 用户输入的密码
            String password = dto.getPassword();
            // 密码加密盐
            String salt = one.getSalt();

            String pswd = DigestUtils.md5DigestAsHex((password+salt).getBytes());
            if (!pswd.equals(one.getPassword())){
                return ResponseResult.setAppHttpCodeEnum(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
            // 登录成功 返回数据(jwt + user)
            String token = AppJwtUtil.getToken(one.getId().longValue());
            Map<String ,Object> map = new HashMap<>();
            map.put("token",token);
            one.setSalt("");
            one.setPassword("");
            map.put("user",one);

            return ResponseResult.okResult(map);
        }else {
            // 登录成功 返回数据(jwt)
            Map<String ,Object> map = new HashMap<>();
            map.put("token",AppJwtUtil.getToken(0l));
            return ResponseResult.okResult(map);
        }
    }
}
