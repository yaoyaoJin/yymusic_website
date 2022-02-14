package com.music.yymusic_website.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 定位歌曲链接
* */
@Configuration
public class Song_Link_Config implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //歌曲链接定位
        registry.addResourceHandler("/songLink/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"songLink"
                        +System.getProperty("file.separator")
        );
    }


}
