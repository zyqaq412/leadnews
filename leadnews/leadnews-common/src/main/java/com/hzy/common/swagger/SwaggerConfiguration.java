package com.hzy.common.swagger;

/**
 * @title: SwaggerConfiguration
 * @Author zxwyhzy
 * @Date: 2023/9/6 21:13
 * @Version 1.0
 */
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // 该注解是Springfox-swagger框架提供的使用Swagger注解，该注解必须加
@EnableKnife4j //该注解是`knife4j`提供的增强注解,
// Ui提供了例如动态参数、参数过滤、接口排序等增强功能,如果你想使用这些增强功能就必须加该注解，否则可以不用加
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    /**
     *  knife4j
     * @return
     */
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("1.0")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.hzy"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("头条新闻API文档")
                .description("头条新闻API文档")
                .version("1.0")
                .build();
    }






    /**
     *  Swagger
     */
/*    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                // 要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors.basePackage("com.hzy"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        Contact contact = new Contact("zyqaq412","","");
        return new ApiInfoBuilder()
                .title("头条新闻-平台管理API文档")
                .description("头条新闻后台api")
                .contact(contact)
                .version("1.0.0").build();
    }*/
}
