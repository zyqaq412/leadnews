package com.hzy.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.media.pojos.WmChannel;
import com.hzy.wemedia.mapper.WmChannelMapper;
import com.hzy.wemedia.service.WmChannelService;
import org.springframework.stereotype.Service;

/**
 * @title: WmChannelServiceImpl
 * @Author zxwyhzy
 * @Date: 2023/9/16 11:00
 * @Version 1.0
 */
@Service
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel> implements WmChannelService {
    /**
     * 查询所有频道
     * @return
     */
    @Override
    public ResponseResult findAll() {
        return ResponseResult.okResult(list());
    }
}
