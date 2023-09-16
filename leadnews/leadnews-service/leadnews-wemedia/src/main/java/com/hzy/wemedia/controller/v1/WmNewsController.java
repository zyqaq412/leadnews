package com.hzy.wemedia.controller.v1;

import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.media.dtos.WmNewsPageReqDto;
import com.hzy.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: WmNewsController
 * @Author zxwyhzy
 * @Date: 2023/9/16 11:25
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {


    @Autowired
    private WmNewsService wmNewsService;

    @PostMapping("/list")
    public ResponseResult findAll(@RequestBody WmNewsPageReqDto dto){
        return  wmNewsService.findAll(dto);
    }
}
