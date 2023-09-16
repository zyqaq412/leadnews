package com.hzy.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.media.pojos.WmChannel;

/**
 * @title: WmChannelService
 * @Author zxwyhzy
 * @Date: 2023/9/16 11:00
 * @Version 1.0
 */
public interface WmChannelService extends IService<WmChannel> {

    /**
     * 查询所有频道
     * @return
     */
    ResponseResult findAll();


}