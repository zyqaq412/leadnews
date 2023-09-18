package com.hzy.apis.article;

import com.hzy.model.article.dtos.ArticleDto;
import com.hzy.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @title: IArticleClient
 * @Author zxwyhzy
 * @Date: 2023/9/18 12:51
 * @Version 1.0
 */
@FeignClient("leadnews-article")
public interface IArticleClient {

    @PostMapping("/api/v1/article/save")
    ResponseResult saveArticle(ArticleDto articleDto);
}
