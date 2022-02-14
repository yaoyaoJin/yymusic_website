package com.music.yymusic_website.service;

import com.music.yymusic_website.domain.User;
import com.music.yymusic_website.utils.ResultVO;

import java.util.List;

public interface UserService {
    //用户注册
    public ResultVO userRegister(String name,String password);
    //用户登录
    public ResultVO userLogin(String name,String password);
    //添加用户  返回是否成功
    public boolean addUser(User user);
    //修改用户
    public boolean updateUser(User user);
    //删除用户
    public boolean deleteUser(Integer id);
    //根据主键查询整个对象
    public User searchUser_By_Id(int id);
    //查询所有用户
    public List<User> search_All_User();
    //根据名字查询用户 列表
    public User searchUser_By_Name(String name);
    //根据账号名称修改密码
    public boolean verifyUserPassword(String name,String password);
}
