package com.hzy.article.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.article.mapper.ApArticleConfigMapper;
import com.hzy.article.mapper.ApArticleContentMapper;
import com.hzy.article.mapper.ApArticleMapper;
import com.hzy.article.service.ApArticleService;
import com.hzy.common.constants.ArticleConstants;
import com.hzy.file.service.FileStorageService;
import com.hzy.model.article.dtos.ArticleDto;
import com.hzy.model.article.dtos.ArticleHomeDto;
import com.hzy.model.article.pojos.ApArticle;
import com.hzy.model.article.pojos.ApArticleConfig;
import com.hzy.model.article.pojos.ApArticleContent;
import com.hzy.model.common.dtos.ResponseResult;
import com.hzy.model.common.enums.AppHttpCodeEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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

        // 保存文章 使用freemarker根据内容生成html文件上传到minio，访问文章详情时返回html文件路径
/*        CREATEHTML.submit(() ->{
            createHtml(articleDto,article.getId());
        });*/
        createHtml(articleDto,article.getId());

        return ResponseResult.okResult(article.getId());
    }
   // private static final ExecutorService CREATEHTML = Executors.newFixedThreadPool(10);

    @Autowired
    private Configuration configuration;

    @Autowired
    private FileStorageService fileStorageService;


    @Async
    public void createHtml(ArticleDto articleDto, Long id) {

        try {
            // 文章内容通过freemarker生成html文件
            StringWriter out = new StringWriter();
            Template template = configuration.getTemplate("article.ftl");

            Map<String, Object> params = new HashMap<>();
            params.put("content", JSONArray.parseArray(articleDto.getContent()));

            template.process(params, out);
            InputStream is = new ByteArrayInputStream(out.toString().getBytes());

            // 把html文件上传到minio中
            String path = fileStorageService
                    .uploadHtmlFile("", id + ".html", is);

            // 修改ap_article表，保存static_url字段
            ApArticle article = new ApArticle();
            article.setId(id);
            article.setStaticUrl(path);
            apArticleMapper.updateById(article);

        }catch (Exception e){

        }

    }

}