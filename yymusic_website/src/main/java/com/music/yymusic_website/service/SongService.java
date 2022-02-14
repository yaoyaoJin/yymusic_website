package com.music.yymusic_website.service;


import com.music.yymusic_website.domain.Song;

import java.util.List;

public interface SongService {
    //添加歌曲
    public boolean addSong(Song song);
    //修改歌曲
    public boolean updateSong(Song song);
    //删除歌曲
    public boolean deleteSong(Integer id);
    //根据主键查询整个对象
    public Song searchSong_By_Id(Integer id);
    //查询所有歌曲
    public List<Song> search_All_Song();
    //根据名字查询歌曲列表
    public List<Song> searchSong_By_Name(String name);
    //根据名字模糊查询歌曲列表
    public List<Song> searchSong_Like_Name(String name);
    //根据歌手查询歌曲列表
    public List<Song> searchSong_By_SingerId(Integer singerId);
}
