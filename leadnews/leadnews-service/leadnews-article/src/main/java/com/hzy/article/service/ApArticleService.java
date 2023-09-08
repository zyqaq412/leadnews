package com.hzy.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.model.article.dtos.ArticleHomeDto;
import com.hzy.model.article.pojos.ApArticle;
import com.hzy.model.common.dtos.ResponseResult;

/**
 * @title: ApArticleService
 * @Author zxwyhzy
 * @Date: 2023/9/8 16:43
 * @Version 1.0
 */
public interface ApArticleService extends IService<ApArticle> {

    /**
     *  加载文章列表
     * @param dto
     * @param type 1 加载更多，2 加载最新
     * @return
     */
    ResponseResult load(ArticleHomeDto dto,Short type);
}
