package com.music.yymusic_website.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/*
* 管理员
* */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Admin implements Serializable {
    private Integer id;
    private String name;
    private String password;
}
