package com.music.yymusic_website.service.impl;

import com.music.yymusic_website.dao.AdminDao;
import com.music.yymusic_website.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public boolean verifyPassword(String username, String password) {
        if(adminDao.verifyPassword(username,password)>0) {
            return true;
        }else {
            return false;
        }
    }
}
