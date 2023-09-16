package com.hzy.model.media.dtos;

import com.hzy.model.common.dtos.PageRequestDto;
import lombok.Data;
import java.util.Date;

/**
 * @title: WmNewsPageReqDto
 * @Author zxwyhzy
 * @Date: 2023/9/16 11:25
 * @Version 1.0
 */
@Data
public class WmNewsPageReqDto extends PageRequestDto {

    /**
     * 状态
     */
    private Short status;
    /**
     * 开始时间
     */
    private Date beginPubDate;
    /**
     * 结束时间
     */
    private Date endPubDate;
    /**
     * 所属频道ID
     */
    private Integer channelId;
    /**
     * 关键字
     */
    private String keyword;
}
