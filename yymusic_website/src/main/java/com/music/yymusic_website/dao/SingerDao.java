package com.music.yymusic_website.dao;

import com.music.yymusic_website.domain.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
* 歌手管理
* 增删改查方法
* */

@Repository
public interface SingerDao {
    //添加歌手
    public int addSinger(Singer singer);
    //修改歌手
    public int updateSinger(Singer singer);
    //删除歌手
    public int deleteSinger(Integer id);
    //根据主键查询整个对象
    public Singer searchSinger_By_Id(int id);
    //查询所有歌手
    public List<Singer> search_All_Singer();
    //根据名字查询歌手列表
    public List<Singer> searchSinger_By_Name(String name);

}
