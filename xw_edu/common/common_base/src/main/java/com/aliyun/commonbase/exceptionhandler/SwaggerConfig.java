package com.aliyun.commonbase.exceptionhandler;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2020年05月17日
 *
 * @author 徐威
 * @version : 1.0
 */

@Configuration// 告诉程序这是配置类
@EnableSwagger2 //swagger注解
public class SwaggerConfig {

    // 访问地址: http://localhost:8004/swagger-ui.html
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*"))) // 接口文档对包含admin和error的信息不显示
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    // 设置Api文档信息
    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("网站-课程中心API文档")
                .description("本文档描述了课程中心微服务接口定义")
                .version("1.0")
                .contact(new Contact("java", "http://baidu.com", "1123@qq.com"))
                .build();
    }

}
