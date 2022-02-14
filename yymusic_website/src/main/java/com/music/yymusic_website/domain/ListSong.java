package com.music.yymusic_website.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/*
* 歌单歌曲
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListSong implements Serializable {
    private int id;
    private int songId;   //歌曲id
    private int songListId; //歌单id
}
