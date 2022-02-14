package com.music.yymusic_website.service.impl;

import com.music.yymusic_website.dao.ListSongDao;
import com.music.yymusic_website.domain.ListSong;
import com.music.yymusic_website.domain.Song;
import com.music.yymusic_website.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {
    @Autowired
    ListSongDao listSongDao;

    @Override
    public boolean addSong_To_List(ListSong listSong) {
        return listSongDao.addSong_To_List(listSong)>0;
    }

    @Override
    public boolean deleteSong_From_List(int songId,int songListId) {
        return listSongDao.deleteSong_From_List(songId,songListId)>0;
    }

    @Override
    public List<ListSong> searchSong_By_ListId(int songListId) {

        return listSongDao.searchSong_By_ListId(songListId);
    }

    @Override
    public ListSong searchListSong_By_Id(int id) {
        return listSongDao.searchListSong_By_Id(id);
    }

    @Override
    public List<ListSong> searchAll_ListSong() {
        return listSongDao.searchAll_ListSong();
    }

    @Override
    public boolean updateListSong(ListSong listSong) {
        return listSongDao.updateListSong(listSong)>0;
    }
}
