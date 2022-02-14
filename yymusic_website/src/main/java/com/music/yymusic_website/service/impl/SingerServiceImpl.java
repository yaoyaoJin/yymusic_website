package com.music.yymusic_website.service.impl;


import com.music.yymusic_website.dao.SingerDao;
import com.music.yymusic_website.domain.Singer;
import com.music.yymusic_website.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerDao singerDao;

    @Override
    public boolean addSinger(Singer singer) {
        return singerDao.addSinger(singer)>0;
    }

    @Override
    public boolean updateSinger(Singer singer) {
        return singerDao.updateSinger(singer)>0;
    }

    @Override
    public boolean deleteSinger(Integer id) {
        return singerDao.deleteSinger(id)>0;
    }

    @Override
    public Singer searchSinger_By_Id(int id) {
        return singerDao.searchSinger_By_Id(id);
    }

    @Override
    public List<Singer> search_All_Singer() {
        return singerDao.search_All_Singer();
    }

    @Override
    public List<Singer> searchSinger_By_Name(String name) {
        return singerDao.searchSinger_By_Name(name);
    }
}
