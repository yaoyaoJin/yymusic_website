package com.music.yymusic_website.service.impl;

import com.music.yymusic_website.dao.SongDao;
import com.music.yymusic_website.domain.Song;
import com.music.yymusic_website.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongDao songDao;
    @Override
    public boolean addSong(Song song) {

        return songDao.addSong(song)>0;
    }

    @Override
    public boolean updateSong(Song song) {

        return songDao.updateSong(song)>0;
    }

    @Override
    public boolean deleteSong(Integer id) {

        return songDao.deleteSong(id)>0;
    }

    @Override
    public Song searchSong_By_Id(Integer id) {

        return songDao.searchSong_By_Id(id);
    }

    @Override
    public List<Song> search_All_Song() {

        return songDao.search_All_Song();
    }

    @Override
    public List<Song> searchSong_By_Name(String name) {

        return songDao.searchSong_By_Name(name);
    }

    // 根据歌名模糊查询
    @Override
    public List<Song> searchSong_Like_Name(String name) {

        return songDao.searchSong_Like_Name("%"+name+"%");
    }

    @Override
    public List<Song> searchSong_By_SingerId(Integer singerId) {

        return songDao.searchSong_By_SingerId(singerId);
    }
}
