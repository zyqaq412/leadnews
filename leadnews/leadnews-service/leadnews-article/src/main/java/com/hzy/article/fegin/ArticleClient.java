package com.hzy.article.fegin;

import com.hzy.apis.article.IArticleClient;
import com.hzy.article.service.ApArticleService;
import com.hzy.model.article.dtos.ArticleDto;
import com.hzy.model.common.dtos.ResponseResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: ArticleClient
 * @Author zxwyhzy
 * @Date: 2023/9/18 12:55
 * @Version 1.0
 */
@RestController
public class ArticleClient implements IArticleClient {
    @Autowired
    private ApArticleService apArticleService;
    @PostMapping("/api/v1/article/save")
    public ResponseResult saveArticle(@RequestBody ArticleDto articleDto) {
        return apArticleService.save(articleDto);
    }
}
