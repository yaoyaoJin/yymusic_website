package com.music.yymusic_website.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/*
* 用户
* */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "User对象",description = "用户信息")
@Table(name = "user")         //指定对应数据库表名,大小写不要求，此处可以不加（表名和类名一致）
public class User implements Serializable {
    //属性类型最好为包装类
    @Column(name = "id") //指定对应的数据库表内字段名，此处可以属性名与字段名一致或满足对应规则）
    @Id //声明该字段为主键字段
    private Integer id;
    private String name;   // 昵称
    private String password;
    //@Column(name = "sex")
    private Byte sex;
    private String phoneNum;   //电话号码
    private String email;
    private Date birth;
    private String introduction;
    private String location;
    private String profilePhoto;   //头像
    private Date createTime;
    private Date updateTime;
}
