package com.music.yymusic_website.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/*
* 歌曲
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Song implements Serializable {
    private int id;
    //歌手id
    private int singerId;
    private String name;
    //歌曲简介
    private String introduction;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //歌曲图片
    private String picture;
    //歌词
    private String lyric;
    //链接
    private String link;
}
