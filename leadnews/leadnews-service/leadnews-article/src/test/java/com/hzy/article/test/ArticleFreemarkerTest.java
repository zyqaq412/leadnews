package com.hzy.article.test;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hzy.article.ArticleApplication;
import com.hzy.article.mapper.ApArticleContentMapper;
import com.hzy.article.mapper.ApArticleMapper;
import com.hzy.file.service.FileStorageService;
import com.hzy.model.article.pojos.ApArticle;
import com.hzy.model.article.pojos.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: ArticleFreemarkerTest
 * @Author zxwyhzy
 * @Date: 2023/9/8 20:11
 * @Version 1.0
 */
@SpringBootTest(classes = ArticleApplication.class)
@RunWith(SpringRunner.class)
public class ArticleFreemarkerTest {

    @Autowired
    private Configuration configuration;

    @Autowired
    private FileStorageService fileStorageService;


    @Autowired
    private ApArticleMapper apArticleMapper;

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Test
    public void createStaticUrlTest() throws Exception {
        //1.获取文章内容
        ApArticleContent apArticleContent = apArticleContentMapper
                .selectOne(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId,
                        1302862387124125698L));
        if(apArticleContent != null && StringUtils.isNotBlank(apArticleContent.getContent())){
            //2.文章内容通过freemarker生成html文件
            StringWriter out = new StringWriter();
            Template template = configuration.getTemplate("article.ftl");

            Map<String, Object> params = new HashMap<>();
            params.put("content", JSONArray.parseArray(apArticleContent.getContent()));

            template.process(params, out);
            InputStream is = new ByteArrayInputStream(out.toString().getBytes());

            //3.把html文件上传到minio中
            String path = fileStorageService
                    .uploadHtmlFile("", apArticleContent.getArticleId() + ".html", is);

            //4.修改ap_article表，保存static_url字段
            ApArticle article = new ApArticle();
            article.setId(apArticleContent.getArticleId());
            article.setStaticUrl(path);
            apArticleMapper.updateById(article);
            System.out.println("成功");
        }
    }
    @Test
    public void testAll() throws Exception{
        LambdaQueryWrapper<ApArticleContent> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        List<ApArticleContent> l = apArticleContentMapper.selectList(lambdaQueryWrapper);

        for (ApArticleContent article : l){
            //2.文章内容通过freemarker生成html文件
            StringWriter out = new StringWriter();
            Template template = configuration.getTemplate("article.ftl");

            Map<String, Object> params = new HashMap<>();
            params.put("content", JSONArray.parseArray(article.getContent()));

            template.process(params, out);
            InputStream is = new ByteArrayInputStream(out.toString().getBytes());

            //3.把html文件上传到minio中
            String path = fileStorageService
                    .uploadHtmlFile("", article.getArticleId() + ".html", is);

            //4.修改ap_article表，保存static_url字段
            ApArticle temp = new ApArticle();
            temp.setId(article.getArticleId());
            temp.setStaticUrl(path);
            apArticleMapper.updateById(temp);
        }
    }
}
