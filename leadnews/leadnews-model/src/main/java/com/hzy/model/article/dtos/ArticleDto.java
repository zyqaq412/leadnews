package com.hzy.model.article.dtos;

import com.hzy.model.article.pojos.ApArticle;
import lombok.Data;

/**
 * @title: ArticleDto
 * @Author zxwyhzy
 * @Date: 2023/9/18 12:49
 * @Version 1.0
 */
@Data
public class ArticleDto  extends ApArticle {
    /**
     * 文章内容
     */
    private String content;
}
