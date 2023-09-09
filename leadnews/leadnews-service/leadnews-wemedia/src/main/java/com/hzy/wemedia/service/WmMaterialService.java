package com.hzy.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.media.pojos.WmMaterial;
import org.springframework.web.multipart.MultipartFile;

/**
 * @title: WmMaterialService
 * @Author zxwyhzy
 * @Date: 2023/9/9 16:35
 * @Version 1.0
 */
public interface WmMaterialService extends IService<WmMaterial> {

    /**
     * 图片上传
     * @param multipartFile
     * @return
     */
    ResponseResult uploadPicture(MultipartFile multipartFile);



}
