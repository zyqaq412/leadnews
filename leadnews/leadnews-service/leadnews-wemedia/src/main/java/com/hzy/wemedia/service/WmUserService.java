package com.hzy.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.media.dtos.WmLoginDto;
import com.hzy.model.media.pojos.WmUser;

public interface WmUserService extends IService<WmUser> {

    /**
     * 自媒体端登录
     * @param dto
     * @return
     */
    public ResponseResult login(WmLoginDto dto);

}