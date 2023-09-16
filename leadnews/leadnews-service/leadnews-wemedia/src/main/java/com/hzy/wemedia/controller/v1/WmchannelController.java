package com.hzy.wemedia.controller.v1;

import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.wemedia.service.WmChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: WmchannelController
 * @Author zxwyhzy
 * @Date: 2023/9/16 10:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/channel")
public class WmchannelController {


    @Autowired
    private WmChannelService wmChannelService;

    @GetMapping("/channels")
    public ResponseResult findAll(){
        return wmChannelService.findAll();
    }
}