package com.music.yymusic_website.dao;

import com.music.yymusic_website.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
 * 歌手管理
 * 增删改查方法
 * */

@Repository
public interface SongDao {
    //添加歌曲
    public int addSong(Song song);
    //修改歌曲
    public int updateSong(Song song);
    //删除歌曲
    public int deleteSong(Integer id);
    //根据主键查询整个对象
    public Song searchSong_By_Id(int id);
    //查询所有歌曲
    public List<Song> search_All_Song();
    //根据名字查询歌曲列表
    public List<Song> searchSong_By_Name(String name);
    //根据名字模糊查询歌曲列表
    public List<Song> searchSong_Like_Name(String name);
    //根据歌手id查询歌曲列表
    public List<Song> searchSong_By_SingerId(int singerId);

}
