package com.hzy.model.media.dtos;

import com.hzy.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * @title: WmMaterialDto
 * @Author zxwyhzy
 * @Date: 2023/9/9 16:51
 * @Version 1.0
 */
@Data
public class WmMaterialDto extends PageRequestDto {

    /**
     * 1 收藏
     * 0 未收藏
     */
    private Short isCollection;
}