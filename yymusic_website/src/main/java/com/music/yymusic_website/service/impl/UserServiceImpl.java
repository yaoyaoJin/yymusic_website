package com.music.yymusic_website.service.impl;

import com.music.yymusic_website.dao.UserDao;
import com.music.yymusic_website.domain.User;
import com.music.yymusic_website.service.UserService;
import com.music.yymusic_website.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    @Transactional  //开启事务(隔离级别：可重复读)
    public ResultVO userRegister(String name, String password) {
        //1. 首先查询该用户名是否被注册
        User user=userDao.searchUser_By_Name(name);
        //2.如果没有被注册则进行保存
        if(user==null){
            user=new User();
            user.setName(name);
            user.setEmail(password);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            int i=userDao.addUser(user);
            if(i>0){
                return new ResultVO(1,"注册成功！",null);
            }else{
                return new ResultVO(0,"注册失败！",null);
            }
        }else{
            return new ResultVO(-1,"用户名已被注册！",null);
        }
    }

    @Override
    public ResultVO userLogin(String name, String password) {
        //首先查询该用户是否存在
        User user=userDao.searchUser_By_Name(name);
        if(user==null){
            return new ResultVO(-1,"登陆失败，用户名不存在！",null);
        }else{
            String pwd=user.getPassword();
            if(pwd.equals(password)){
                return new ResultVO(1,"登陆成功！",user);
            }else{
                return new ResultVO(0,"登陆失败，密码错误！",null);
            }
        }
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user)>0;
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user)>0;
    }

    @Override
    public boolean deleteUser(Integer id) {
        return userDao.deleteUser(id)>0;
    }

    @Override
    public User searchUser_By_Id(int id) {
        return userDao.searchUser_By_Id(id);
    }

    @Override
    public List<User> search_All_User() {
        return userDao.search_All_User();
    }

    @Override
    public User searchUser_By_Name(String name) {
        return userDao.searchUser_By_Name(name);
    }

    @Override
    public boolean verifyUserPassword(String name, String password) {
        return userDao.verifyUserPassword(name,password)>0;
    }
}
