package com.hzy.model.media.dtos;

import lombok.Data;

import java.util.List;
import java.util.Date;

/**
 * @title: WmNewsDto
 * @Author zxwyhzy
 * @Date: 2023/9/16 11:57
 * @Version 1.0
 */
@Data
public class WmNewsDto {

    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 频道id
     */
    private Integer channelId;
    /**
     * 标签
     */
    private String labels;
    /**
     * 发布时间
     */
    private Date publishTime;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章封面类型  0 无图 1 单图 3 多图 -1 自动
     */
    private Short type;
    /**
     * 提交时间
     */
    private Date submitedTime;
    /**
     * 状态 提交为1  草稿为0
     */
    private Short status;

    /**
     * 封面图片列表 多张图以逗号隔开
     */
    private List<String> images;
}