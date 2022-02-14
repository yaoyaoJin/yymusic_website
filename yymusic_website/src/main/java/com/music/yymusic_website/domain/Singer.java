package com.music.yymusic_website.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/*
* 歌手
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Singer implements Serializable {
    private int id;
    private String name;
    private Byte sex;
    private String picture; //歌手照片
    private Date birth;
    private String location;
    private String introduction;  //歌手简介
}
