package com.music.yymusic_website.service;

import com.music.yymusic_website.domain.ListSong;
import com.music.yymusic_website.domain.Song;

import java.util.List;

public interface ListSongService {
    //向歌单里添加歌曲
    public boolean addSong_To_List(ListSong listSong);
    //删除歌单里的歌曲
    public boolean deleteSong_From_List(int songId,int songListId);
    //根据歌单id查询歌曲列表
    //public List<Song> searchSong_By_ListId(int songListId);
    public List<ListSong> searchSong_By_ListId(int songListId);
    //根据id查询整个对象
    public ListSong searchListSong_By_Id(int id);
    //查询整个歌单歌曲列表
    public List<ListSong> searchAll_ListSong();
    //更新歌单歌曲列表
    public boolean updateListSong(ListSong listSong);
}
