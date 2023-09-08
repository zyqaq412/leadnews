package com.hzy.model.article.dtos;

/**
 * @title: ArticleHomeDto
 * @Author zxwyhzy
 * @Date: 2023/9/8 16:19
 * @Version 1.0
 */
import lombok.Data;

import java.util.Date;

@Data
public class ArticleHomeDto {

    // 最大时间
    Date maxBehotTime;
    // 最小时间
    Date minBehotTime;
    // 分页size
    Integer size;
    // 频道ID
    String tag;
}
