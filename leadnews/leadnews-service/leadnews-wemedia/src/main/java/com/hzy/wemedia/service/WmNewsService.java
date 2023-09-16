package com.hzy.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.media.dtos.WmNewsPageReqDto;
import com.hzy.model.media.pojos.WmNews;

/**
 * @title: WmNewsService
 * @Author zxwyhzy
 * @Date: 2023/9/16 11:26
 * @Version 1.0
 */
public interface WmNewsService extends IService<WmNews> {

    /**
     * 查询文章（条件查询）
     * @param dto
     * @return
     */
    ResponseResult findAll(WmNewsPageReqDto dto);

}
