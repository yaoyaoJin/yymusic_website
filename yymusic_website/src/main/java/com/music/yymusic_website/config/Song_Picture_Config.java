package com.music.yymusic_website.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 歌曲图片  歌单封面
* 地址定位
* */
@Configuration
public class Song_Picture_Config implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //歌曲图片定位
        registry.addResourceHandler("/img/songPicture/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"songPicture"+System.getProperty("file.separator")
        );
        //歌单封面定位
        registry.addResourceHandler("/img/songListPicture/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"songListPicture"+System.getProperty("file.separator")
        );
    }


}
