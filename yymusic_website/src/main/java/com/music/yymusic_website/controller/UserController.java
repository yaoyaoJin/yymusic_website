package com.music.yymusic_website.controller;


import com.alibaba.fastjson.JSONObject;
import com.music.yymusic_website.domain.User;
import com.music.yymusic_website.service.UserService;
import com.music.yymusic_website.utils.Consts;
import com.music.yymusic_website.utils.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(value = "用户相关功能接口",tags = "用户")
@RequestMapping("/user")
@RestController  //直接返回JSON对象
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    //用户登录
    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "username", value = "登陆账号", required = true),
            @ApiImplicitParam(dataType = "string", name = "password", value = "登陆密码", required = false, defaultValue = "123456")
    })
    @PostMapping("/login")
    public ResultVO userLogin(@RequestParam("username") String name,@RequestParam(value = "password",defaultValue = "123456") String password){
        return userService.userLogin(name,password);
    }
    //用户注册
    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "username", value = "注册账号", required = true),
            @ApiImplicitParam(dataType = "string", name = "password", value = "账号密码", required = true)
    })
    @PostMapping("/regist")
    public ResultVO userRegister(@RequestParam("username") String name,@RequestParam("password") String password){
        return userService.userRegister(name,password);
    }

    //添加用户
    //设置路径及提交方式
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addUser(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String name=request.getParameter("name").trim();
        String sex=request.getParameter("sex").trim();
        //头像在前端定义默认值
        String profilePhoto=request.getParameter("avator");
        String birth=request.getParameter("birth").trim();
        String location=request.getParameter("location").trim();
        String introduction=request.getParameter("introduction");
        String password=request.getParameter("password").trim();
        String phoneNum=request.getParameter("phoneNum").trim();
        String email=request.getParameter("email").trim();
        //设置时间日期格式  转换生日为Date格式
        DateFormat dateFormat=new SimpleDateFormat("yyy-MM-dd");
        Date birthDate=new Date();
        try {
            birthDate=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //将获取的属性保存到用户对象中
        User user=new User();
        user.setName(name);
        user.setSex(new Byte(sex));
        user.setLocation(location);
        user.setBirth(birthDate);
        user.setIntroduction(introduction);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNum(phoneNum);
        user.setProfilePhoto(profilePhoto);
        //添加用户
        boolean res=userService.addUser(user);
        //添加成功！
        if(res){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        //添加失败！！
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;

    }

    //查询所有用户信息
    @RequestMapping(value = "/All_User",method = RequestMethod.GET)
    public Object search_All_User(HttpServletRequest request) {
        return userService.search_All_User();
    }

    //修改用户信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateUser(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        //根据id修改
        String id=request.getParameter("id");
        String name=request.getParameter("name").trim();
        String sex=request.getParameter("sex").trim();
        //头像单独修改
        //String profilePhoto=request.getParameter("avator");
        String birth=request.getParameter("birth").trim();
        String location=request.getParameter("location").trim();
        String introduction=request.getParameter("introduction");
        String password=request.getParameter("password").trim();
        String phoneNum=request.getParameter("phoneNum").trim();
        String email=request.getParameter("email").trim();
        //设置时间日期格式  转换生日为Date格式
        DateFormat dateFormat=new SimpleDateFormat("yyy-MM-dd");
        Date birthDate=new Date();
        try {
            birthDate=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //将获取的属性保存到用户对象中
        //User User=new User(Integer.parseInt(id),name,new Byte(sex),picture,birthDate,location,introduction);
        User user=new User();
        user.setId(Integer.parseInt(id));
        user.setName(name);
        user.setSex(new Byte(sex));
        user.setLocation(location);
        user.setBirth(birthDate);
        user.setIntroduction(introduction);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNum(phoneNum);
        //user.setProfilePhoto(profilePhoto);
        //表单验证！   用户名和密码不能为空------未完成
/*        if(name.equals("")||password.equals("")){
            jsonObject.put(Consts.CODE,-1);
            jsonObject.put(Consts.MSG,"用户名或密码不能为空！");
            return jsonObject;
        }*/
        //修改用户信息
        boolean res=userService.updateUser(user);
        //修改成功！
        if(res){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        //添加失败！！
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;

    }

    //删除用户
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteUser_By_Id(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String id=request.getParameter("id").trim();

        //删除用户
        boolean res=userService.deleteUser(Integer.parseInt(id));
        //删除成功！
        if(res){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"删除成功");
            return jsonObject;
        }
        //删除失败！！
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"删除失败");
        return jsonObject;
    }

    //更新用户图片???????????????????????????????????????????????????????????????????????????????????
    @RequestMapping(value = "/updateUser_Picture",method = RequestMethod.POST)
    public Object updateUser_Picture(@RequestParam("file") MultipartFile pictureFile,@RequestParam("id") int id){
        JSONObject jsonObject=new JSONObject();
        if(pictureFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName=System.currentTimeMillis()+pictureFile.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"userPicture";
        //文件不存在时，新增该路径
        File file1=new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storePicture_Path="/img/userPicture/"+fileName;
        try{
            pictureFile.transferTo(dest);
            User user=new User();
            user.setId(id);
            user.setProfilePhoto(storePicture_Path);
            //更新
            boolean flag=userService.updateUser(user);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                return jsonObject;
            }
        }catch(IOException e){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败"+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject;
    }
/*
    //根据主键查询用户信息
    @RequestMapping(value = "/serchUser_By_Id",method = RequestMethod.GET)
    public Object searchUser_By_Id(HttpServletRequest request){
        String id=request.getParameter("id").trim();
        //查询用户信息  直接返回查询结果对象
        return UserService.searchUser_By_Id(Integer.parseInt(id));
    }


    //根据用户名称查询(模糊查询)
    @RequestMapping(value = "/searchUser_By_Name",method = RequestMethod.GET)
    public Object searchUser_By_Name(HttpServletRequest request){
        String name=request.getParameter("name");
        //模糊查询  需在mapper文件里用 like 匹配
        return UserService.searchUser_By_Name("%"+name+"%");
    }

    */
}
