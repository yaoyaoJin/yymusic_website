package com.music.yymusic_website.controller;


import com.alibaba.fastjson.JSONObject;
import com.music.yymusic_website.domain.Singer;
import com.music.yymusic_website.service.SingerService;
import com.music.yymusic_website.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/singer")
@RestController  //直接返回JSON对象
@CrossOrigin
public class SingerController {
    @Autowired
    private SingerService singerService;
    //添加歌手
    //设置路径及提交方式
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String name=request.getParameter("name").trim();
        String sex=request.getParameter("sex").trim();
        String picture=request.getParameter("picture").trim();
        String birth=request.getParameter("birth").trim();
        String location=request.getParameter("location").trim();
        String introduction=request.getParameter("introduction");
        //设置时间日期格式  转换生日为Date格式
        DateFormat dateFormat=new SimpleDateFormat("yyy-MM-dd");
        Date birthDate=new Date();
        try {
            birthDate=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //将获取的属性保存到歌手对象中
        Singer singer=new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPicture(picture);
        singer.setLocation(location);
        singer.setBirth(birthDate);
        singer.setIntroduction(introduction);
        //添加歌手
        boolean res=singerService.addSinger(singer);
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

    //修改歌手信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String id=request.getParameter("id").trim();
        String name=request.getParameter("name").trim();
        String sex=request.getParameter("sex").trim();
        //String picture=request.getParameter("picture").trim();
        String birth=request.getParameter("birth").trim();
        String location=request.getParameter("location").trim();
        String introduction=request.getParameter("introduction");
        //设置时间日期格式  转换生日为Date格式
        DateFormat dateFormat=new SimpleDateFormat("yyy-MM-dd");
        Date birthDate=new Date();
        try {
            birthDate=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //将获取的属性保存到歌手对象中
        //Singer singer=new Singer(Integer.parseInt(id),name,new Byte(sex),picture,birthDate,location,introduction);
        Singer singer=new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        //singer.setPicture(picture);
        singer.setLocation(location);
        singer.setBirth(birthDate);
        singer.setIntroduction(introduction);
        //修改歌手信息
        boolean res=singerService.updateSinger(singer);
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

    //删除歌手
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSinger_By_Id(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String id=request.getParameter("id").trim();

        //删除歌手
        boolean res=singerService.deleteSinger(Integer.parseInt(id));
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

    //根据主键查询歌手信息
    @RequestMapping(value = "/serchSinger_By_Id",method = RequestMethod.GET)
    public Object searchSinger_By_Id(HttpServletRequest request){
        String id=request.getParameter("id").trim();
        //查询歌手信息  直接返回查询结果对象
        return singerService.searchSinger_By_Id(Integer.parseInt(id));
    }

    //查询所有歌手信息
    @RequestMapping(value = "/All_Singer",method = RequestMethod.GET)
    public Object search_All_Singer(HttpServletRequest request) {
        return singerService.search_All_Singer();
    }

    //根据歌手名称查询(模糊查询)
    @RequestMapping(value = "/searchSinger_By_Name",method = RequestMethod.GET)
    public Object searchSinger_By_Name(HttpServletRequest request){
        String name=request.getParameter("name");
        //模糊查询  需在mapper文件里用 like 匹配
        return singerService.searchSinger_By_Name("%"+name+"%");
    }

    //更新歌手图片???????????????????????????????????????????????????????????????????????????????????
    @RequestMapping(value = "/updateSinger_Picture",method = RequestMethod.POST)
    public Object updateSinger_Picture(@RequestParam("file") MultipartFile pictureFile,@RequestParam("id") int id){
        JSONObject jsonObject=new JSONObject();
        if(pictureFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName=System.currentTimeMillis()+pictureFile.getOriginalFilename();
        //文件路径
        /*
        * System.getProperty("user.dir")可以取得目前工作的路径，
         *System.getProperty("file.separator")是取得文件分隔符，例如在window环境的
         *文件分陋符是"\",而Unix环境的文件分隔符刚好相反，是"/".
         * */
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"singerPicture";
        //文件不存在时，新增该路径
        File file1=new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storePicture_Path="/img/singerPicture/"+fileName;
        try{
            pictureFile.transferTo(dest);
            Singer singer=new Singer();
            singer.setId(id);
            singer.setPicture(storePicture_Path);
            //更新
            boolean flag=singerService.updateSinger(singer);
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
}
