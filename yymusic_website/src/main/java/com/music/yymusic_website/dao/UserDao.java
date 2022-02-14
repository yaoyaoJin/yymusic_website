package com.music.yymusic_website.dao;

import com.music.yymusic_website.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
 * 用户管理
 * 增删改查方法
 * */

@Repository
public interface UserDao {
    //添加用户
    public int addUser(User user);
    //修改用户
    public int updateUser(User user);
    //删除用户
    public int deleteUser(Integer id);
    //根据主键查询整个对象
    public User searchUser_By_Id(int id);
    //查询所有用户
    public List<User> search_All_User();
    //根据用户名查询用户列表
    public User searchUser_By_Name(String name);
    //根据账号名称验证密码
    public int verifyUserPassword(String name,String password);

}
