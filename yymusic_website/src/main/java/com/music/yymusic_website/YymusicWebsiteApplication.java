package com.music.yymusic_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
//@MapperScan("com.music.yymusic_website.dao")
@MapperScan("com.music.yymusic_website.dao")  //使用tk.mybatis的MapperScan
public class YymusicWebsiteApplication {

    public static void main(String[] args) {

        SpringApplication.run(YymusicWebsiteApplication.class, args);
    }

}
