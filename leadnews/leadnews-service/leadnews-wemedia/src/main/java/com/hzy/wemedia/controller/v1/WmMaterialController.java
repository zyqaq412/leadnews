package com.hzy.wemedia.controller.v1;

import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.media.dtos.WmMaterialDto;
import com.hzy.wemedia.service.WmMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @title: WmMaterialController
 * @Author zxwyhzy
 * @Date: 2023/9/9 16:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/material")
public class WmMaterialController {


    @Autowired
    private WmMaterialService wmMaterialService;

    /**
     *  上传图片
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload_picture")
    public ResponseResult uploadPicture(MultipartFile multipartFile){
        return wmMaterialService.uploadPicture(multipartFile);
    }

    /**
     *  素材列表
     * @param dto
     * @return
     */
    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmMaterialDto dto){
        return wmMaterialService.findList(dto);
    }

}
