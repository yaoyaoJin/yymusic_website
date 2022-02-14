package com.music.yymusic_website.dao;

import com.music.yymusic_website.domain.ListSong;
import com.music.yymusic_website.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* 歌单歌曲关联查询
* */
@Repository
public interface ListSongDao {
    //向歌单里添加歌曲-----添加一条关联记录
    public int addSong_To_List(ListSong listSong);
    //删除歌单里的歌曲------删除一条关联记录
    public int deleteSong_From_List(int songId,int songListId);
    //根据歌单id查询歌曲列表
    //public List<Song> searchSong_By_ListId(int songListId);
    public List<ListSong> searchSong_By_ListId(int songListId);
    //根据id查询整个对象
    public ListSong searchListSong_By_Id(int id);
    //查询所有歌单歌曲列表
    public List<ListSong> searchAll_ListSong();
    //修改歌单歌曲列表
    public int updateListSong(ListSong listSong);

}
