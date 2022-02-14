package com.music.yymusic_website.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
* Swagger配置类   帮助生成接口文档
* 1. 配置文档信息
* 2. 配置生成规则
* */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // Docket用来封装接口文档信息
    public Docket getDocket(){

        /*ApiInfoBuilder apiInfoBuilder=new ApiInfoBuilder();
        apiInfoBuilder.title("《yymusic_website》后端接口说明")
                .description("此文档详细描述了yymusic_website项目后端接口规范")
                .version("v1.0.1")
                .contact(new Contact("瑶瑶","","18292402798.139.com"));
        ApiInfo apiInfo1=apiInfoBuilder.build();*/
        //Docket 支持链式调用
        // 指定文档风格
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())   // 指定生成的文档封面信息
                .select()// apis 设置为哪些接口生成文档
                .apis(RequestHandlerSelectors.basePackage("com.music.yymusic_website.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("《yymusic_website》后端接口说明")
                .description("此文档详细描述了yymusic_website项目后端接口规范")
                .version("v1.0.1")
                .contact(new Contact("瑶瑶","","18292402798.139.com"))
                .build();

    }
}
