package com.music.yymusic_website.service.impl;

import com.music.yymusic_website.dao.SongListDao;
import com.music.yymusic_website.domain.SongList;
import com.music.yymusic_website.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 歌单service实现类
* */
@Service
public class SongListServiceImpl implements SongListService {
    @Autowired
    SongListDao songListDao;

    @Override
    public boolean addSongList(SongList songList) {
        return songListDao.addSongList(songList)>0;
    }

    @Override
    public boolean updateSongList(SongList songList) {
        return songListDao.updateSongList(songList)>0;
    }

    @Override
    public boolean deleteSongList(int id) {
        return songListDao.deleteSongList(id)>0;
    }

    @Override
    public SongList searchSongList_By_Id(int id) {
        return songListDao.searchSongList_By_Id(id);
    }

    @Override
    public List<SongList> search_All_SongList() {
        return songListDao.search_All_SongList();
    }

    @Override
    public List<SongList> searchSongList_By_Title(String title) {
        return songListDao.searchSongList_By_Title(title);
    }

    @Override
    public List<SongList> searchSongList_Like_Title(String title) {
        return songListDao.searchSongList_Like_Title("%"+title+"%");
    }

    @Override
    public List<SongList> searchSongList_By_Style(String style) {
        return songListDao.searchSongList_By_Style("%"+style+"%");
    }
}
