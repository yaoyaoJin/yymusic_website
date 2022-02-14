package com.music.yymusic_website.dao;

import com.music.yymusic_website.domain.Song;
import com.music.yymusic_website.domain.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
 * 歌单管理
 * 增删改查方法
 * */

@Repository
public interface SongListDao {
    //新建歌单
    public int addSongList(SongList songList);
    //修改歌单
    public int updateSongList(SongList songList);
    //删除歌单
    public int deleteSongList(int id);
    //根据主键查询整个对象
    public SongList searchSongList_By_Id(int id);
    //查询所有歌单
    public List<SongList> search_All_SongList();
    //根据标题模糊查询歌单列表
    public List<SongList> searchSongList_Like_Title(String title);
    //根据标题精确查找歌单列表
    public List<SongList> searchSongList_By_Title(String title);
    //根据风格查询歌单列表
    public List<SongList> searchSongList_By_Style(String style);

}
