package com.hzy.article.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.article.mapper.ApArticleConfigMapper;
import com.hzy.article.mapper.ApArticleContentMapper;
import com.hzy.article.mapper.ApArticleMapper;
import com.hzy.article.service.ApArticleService;
import com.hzy.common.constants.ArticleConstants;
import com.hzy.model.article.dtos.ArticleDto;
import com.hzy.model.article.dtos.ArticleHomeDto;
import com.hzy.model.article.pojos.ApArticle;
import com.hzy.model.article.pojos.ApArticleConfig;
import com.hzy.model.article.pojos.ApArticleContent;
import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.common.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Date;


/**
 * @title: ApArticleServiceImpl
 * @Author zxwyhzy
 * @Date: 2023/9/8 16:43
 * @Version 1.0
 */
@Service
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {

    // 单页最大加载的数字
    private final static short MAX_PAGE_SIZE = 50;

    @Autowired
    private ApArticleMapper apArticleMapper;

    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        //1.校验参数
        Integer size = dto.getSize();
        if (size == null || size == 0) {
            size = 10;
        }
        size = Math.min(size, MAX_PAGE_SIZE);
        dto.setSize(size);

        //类型参数检验
        if (!type.equals(ArticleConstants.LOADTYPE_LOAD_MORE) && !type.equals(ArticleConstants.LOADTYPE_LOAD_NEW)) {
            type = ArticleConstants.LOADTYPE_LOAD_MORE;
        }
        //文章频道校验
        if (StringUtils.isEmpty(dto.getTag())) {
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }

        //时间校验
        if (dto.getMaxBehotTime() == null) dto.setMaxBehotTime(new Date());
        if (dto.getMinBehotTime() == null) dto.setMinBehotTime(new Date());
        //2.查询数据
        List<ApArticle> apArticles = apArticleMapper.loadArticleList(dto, type);

        //3.结果封装
        ResponseResult responseResult = ResponseResult.okResult(apArticles);
        return responseResult;
    }

    @Autowired
    private ApArticleConfigMapper apArticleConfigMapper;
    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Override
    public ResponseResult save(ArticleDto articleDto) {
        // 检测参数
        if (articleDto == null || (articleDto.getContent() == null && articleDto.getTitle() == null)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        ApArticle article = new ApArticle();
        BeanUtils.copyProperties(articleDto, article);
        // 检测是否存在
        if (articleDto.getId() == null) {
            //不存在 保存(文章，文章内容，文章配置)
            // 保存文章
            save(article);
            // 保存配置
            ApArticleConfig articleConfig = new ApArticleConfig(article.getId());
            apArticleConfigMapper.insert(articleConfig);
            // 保存文章内容
            ApArticleContent apArticleContent = new ApArticleContent();
            apArticleContent.setContent(articleDto.getContent());
            apArticleContent.setArticleId(article.getId());
            apArticleContentMapper.insert(apArticleContent);


        } else {
            //存在 修改(文章，文章内容)
            updateById(article);
            ApArticleContent apArticleContent = new ApArticleContent();
            apArticleContent.setContent(articleDto.getContent());
            apArticleContentMapper.update(apArticleContent, Wrappers.<ApArticleContent>lambdaQuery()
                    .eq(ApArticleContent::getArticleId, article.getId()));

        }


        return ResponseResult.okResult(article.getId());
    }
}