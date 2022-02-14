package com.music.yymusic_website.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {
    //验证密码是否正确
    public int verifyPassword(String username,String password);
}
