package com.music.yymusic_website.controller;

import com.alibaba.fastjson.JSONObject;
import com.music.yymusic_website.domain.Singer;
import com.music.yymusic_website.domain.Song;
import com.music.yymusic_website.domain.SongList;
import com.music.yymusic_website.service.SongListService;
import com.music.yymusic_website.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/songList")
@CrossOrigin
public class SongListController {
    @Autowired
    SongListService songListService;
    //添加歌单
    //设置路径及提交方式
    @RequestMapping(value = "/addSongList",method = RequestMethod.POST)
    public Object addSongList(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String title=request.getParameter("title").trim();
        String picture=request.getParameter("picture").trim();
        String introduction=request.getParameter("introduction");
        String style=request.getParameter("style").trim();

        //将获取的属性保存到歌单对象中
        SongList songList=new SongList();
        songList.setTitle(title);
        songList.setPicture(picture);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        //添加歌单
        boolean res=songListService.addSongList(songList);
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

    //修改歌单信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSongList(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String id=request.getParameter("id").trim();
        String title=request.getParameter("title").trim();
        //String picture=request.getParameter("picture").trim();
        String style=request.getParameter("style").trim();
        String introduction=request.getParameter("introduction");
        //将获取的属性保存到歌单对象中
        //SongList songList=new SongList(Integer.parseInt(id),name,new Byte(sex),picture,birthDate,location,introduction);
        SongList songList=new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        //songList.setPicture(picture);
        songList.setStyle(style);
        songList.setIntroduction(introduction);
        //修改歌单信息
        boolean res=songListService.updateSongList(songList);
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

    //删除歌单
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSongList_By_Id(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String id=request.getParameter("id").trim();

        //删除歌单
        boolean res=songListService.deleteSongList(Integer.parseInt(id));
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

    //根据主键查询歌单信息
    @RequestMapping(value = "/serchSongList_By_Id",method = RequestMethod.GET)
    public Object searchSongList_By_Id(HttpServletRequest request){
        String id=request.getParameter("id").trim();
        //查询歌单信息  直接返回查询结果对象
        return songListService.searchSongList_By_Id(Integer.parseInt(id));
    }

    //查询所有歌单信息
    @RequestMapping(value = "/All_SongList",method = RequestMethod.GET)
    public Object search_All_SongList(HttpServletRequest request) {
        return songListService.search_All_SongList();
    }

    //根据歌单标题查询(模糊查询)
    @RequestMapping(value = "/searchSongList_Like_Title",method = RequestMethod.GET)
    public Object searchSongList_Like_Title(HttpServletRequest request){
        String title=request.getParameter("title");
        //模糊查询  需在mapper文件里用 like 匹配
        return songListService.searchSongList_Like_Title(title);
    }

    //根据歌单标题直接查询
    @RequestMapping(value = "/searchSongList_By_Title",method = RequestMethod.GET)
    public Object searchSongList_By_Title(HttpServletRequest request){
        String title=request.getParameter("title");
        //模糊查询  需在mapper文件里用 like 匹配
        return songListService.searchSongList_By_Title(title);
    }

    //根据歌单风格查询(模糊查询)
    @RequestMapping(value = "/searchSongList_Like_Style",method = RequestMethod.GET)
    public Object searchSongList_Like_Style(HttpServletRequest request){
        String style=request.getParameter("style");
        //模糊查询  需在mapper文件里用 like 匹配
        return songListService.searchSongList_By_Style(style);
    }

    //更新歌单图片
    @RequestMapping(value = "/updateSongList_Picture",method = RequestMethod.POST)
    public Object updateSongList_Picture(@RequestParam("file") MultipartFile pictureFile,@RequestParam("id") int id){
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
                +System.getProperty("file.separator")+"songListPicture";
        //文件不存在时，新增该路径
        File file1=new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storePicture_Path="/img/songListPicture/"+fileName;
        try{
            pictureFile.transferTo(dest);
            SongList songList=new SongList();
            songList.setId(id);
            songList.setPicture(storePicture_Path);
            //更新
            boolean flag=songListService.updateSongList(songList);
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
