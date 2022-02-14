package com.music.yymusic_website.service;

import com.music.yymusic_website.domain.Singer;

import java.util.List;

public interface SingerService {
    //添加歌手 返回是否成功
    public boolean addSinger(Singer singer);
    //修改歌手
    public boolean updateSinger(Singer singer);
    //删除歌手
    public boolean deleteSinger(Integer id);
    //根据主键查询整个对象
    public Singer searchSinger_By_Id(int id);
    //查询所有歌手
    public List<Singer> search_All_Singer();
    //根据名字查询歌手列表
    public List<Singer> searchSinger_By_Name(String name);
}
