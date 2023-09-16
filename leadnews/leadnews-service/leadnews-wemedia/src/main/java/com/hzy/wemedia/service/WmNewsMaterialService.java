package com.hzy.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.model.media.pojos.WmNewsMaterial;

import java.util.List;

/**
 * @title: WmNewsMaterialService
 * @Author zxwyhzy
 * @Date: 2023/9/16 12:10
 * @Version 1.0
 */
public interface WmNewsMaterialService extends IService<WmNewsMaterial> {
    /**
     *  批量保存 文章与素材关联
     * @param materialIds 素材id
     * @param newsId 文章id
     * @param type 类型
     */
    void saveRelations (List<Integer> materialIds, Integer newsId, Short type);
}
