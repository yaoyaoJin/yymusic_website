package com.music.yymusic_website.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/*
* 歌单
* */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SongList implements Serializable {
    private int id;
    //歌单标题
    private String title;
    //歌单封面
    private String picture;
    //歌单简介
    private String introduction;
    //歌单风格
    private String style;
}
