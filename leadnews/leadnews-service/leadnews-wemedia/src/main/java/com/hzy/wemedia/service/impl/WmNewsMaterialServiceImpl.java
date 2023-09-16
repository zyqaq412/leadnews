package com.hzy.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.model.media.pojos.WmNewsMaterial;
import com.hzy.wemedia.mapper.WmNewsMaterialMapper;
import com.hzy.wemedia.service.WmNewsMaterialService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @title: WmNewsMaterialServiceImpl
 * @Author zxwyhzy
 * @Date: 2023/9/16 12:12
 * @Version 1.0
 */
@Service
public class WmNewsMaterialServiceImpl extends ServiceImpl<WmNewsMaterialMapper, WmNewsMaterial> implements WmNewsMaterialService {
    @Override
    public void saveRelations(List<Integer> materialIds, Integer newsId, Short type) {
        List<WmNewsMaterial> list = new ArrayList<>();
        materialIds.forEach((materialId)->{
            list.add(new WmNewsMaterial(materialId,newsId,type));
        });
        saveBatch(list);
    }
}
