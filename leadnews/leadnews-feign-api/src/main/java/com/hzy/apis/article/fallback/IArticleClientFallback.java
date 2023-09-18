package com.hzy.apis.article.fallback;



import com.hzy.apis.article.IArticleClient;
import com.hzy.model.article.dtos.ArticleDto;
import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.common.enums.AppHttpCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @title: IArticleClientFallback feign失败配置
 * @Author zxwyhzy
 * @Date: 2023/9/18 14:15
 * @Version 1.0
 */

@Component
public class IArticleClientFallback implements IArticleClient {
    @Override
    public ResponseResult saveArticle(ArticleDto dto)  {
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"获取数据失败");
    }
}
