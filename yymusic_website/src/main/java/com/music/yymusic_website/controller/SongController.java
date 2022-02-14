package com.music.yymusic_website.controller;

import com.alibaba.fastjson.JSONObject;
import com.music.yymusic_website.domain.Singer;
import com.music.yymusic_website.domain.Song;
import com.music.yymusic_website.service.SongService;
import com.music.yymusic_website.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/song")
@CrossOrigin
public class SongController {
    @Autowired
    SongService songService;
    @RequestMapping(value = "/addSong",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request,@RequestParam("file") MultipartFile songLink_File){
        JSONObject jsonObject=new JSONObject();
        String singerId =request.getParameter("singerId").trim();
        String name=request.getParameter("name").trim();
        String introduction=request.getParameter("introduction").trim();
        String picture="/img/songPicture/default.jpg";
        String lyric=request.getParameter("lyric");
        //上传歌曲
        if(songLink_File.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌曲上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName=System.currentTimeMillis()+songLink_File.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"songLink";
        //文件不存在时，新增该路径
        File file1=new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeLink_Path="/songLink/"+fileName;
        try{
            songLink_File.transferTo(dest);
            Song song=new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setPicture(picture);
            song.setLyric(lyric);
            song.setLink(storeLink_Path);
            //更新
            boolean flag=songService.addSong(song);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("songLink",storeLink_Path);
                return jsonObject;
            }
        }catch(IOException e){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌曲上传失败"+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject;
    }

    //根据歌手id查询歌曲
    @RequestMapping(value = "/searchSong_By_SingerId",method = RequestMethod.GET)
    public Object serchSong_By_SingerId(HttpServletRequest request){
        String singerId=request.getParameter("singerId");
        return songService.searchSong_By_SingerId(Integer.parseInt(singerId));
    }
    //修改歌曲信息
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSong(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String id =request.getParameter("id").trim();
        String name=request.getParameter("name").trim();
        String introduction=request.getParameter("introduction").trim();
        String lyric=request.getParameter("lyric");
        //保存到歌曲对象中
        Song song=new Song();
        song.setId(Integer.parseInt(id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);
        boolean flag=songService.updateSong(song);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        //修改失败！！
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }
    //删除歌曲
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteSong_By_Id(HttpServletRequest request){
        String id=request.getParameter("id").trim();
        //删除歌曲
        boolean res=songService.deleteSong(Integer.parseInt(id));
        return res;
    }

    //更新歌曲图片
    @RequestMapping(value = "/updateSongPicture",method = RequestMethod.POST)
    public Object updateSong_Picture(@RequestParam("file") MultipartFile songPicture_File,@RequestParam("id") int id){
        JSONObject jsonObject=new JSONObject();
        if(songPicture_File.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"图片上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName=System.currentTimeMillis()+songPicture_File.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songPicture";
        //文件不存在时，新增该路径
        File file1=new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storePicture_Path="/img/songPicture/"+fileName;
        try{
            songPicture_File.transferTo(dest);
            Song song=new Song();
            song.setId(id);
            song.setPicture(storePicture_Path);
            //更新
            boolean flag=songService.updateSong(song);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("picture",storePicture_Path);
                return jsonObject;
            }
        }catch(IOException e){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"图片上传失败"+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject;
    }
    //更新歌曲
    @RequestMapping(value = "/updateSongLink",method = RequestMethod.POST)
    public Object updateSong_Link(@RequestParam("file") MultipartFile songLink_File,@RequestParam("id") int id){
        JSONObject jsonObject=new JSONObject();
        //上传歌曲
        if(songLink_File.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName=System.currentTimeMillis()+songLink_File.getOriginalFilename();
        //文件路径
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"songLink";
        //文件不存在时，新增该路径
        File file1=new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际文件地址
        File dest=new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeLink_Path="/songLink/"+fileName;
        try{
            songLink_File.transferTo(dest);
            Song song=new Song();
            song.setId(id);
            song.setLink(storeLink_Path);
            //更新
            boolean flag=songService.updateSong(song);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("songLink",storeLink_Path);
                return jsonObject;
            }
        }catch(IOException e){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败"+e.getMessage());
            e.printStackTrace();
        }
        return jsonObject;
    }

    //根据歌曲id查询歌曲
    @RequestMapping(value = "/searchSong_By_Id",method = RequestMethod.GET)
    public Object searchSong_By_Id(HttpServletRequest request){
        String id=request.getParameter("id");
        //System.out.println(id);
        return songService.searchSong_By_Id(Integer.parseInt(id));
    }

    //根据歌名查询歌曲
    @RequestMapping(value = "/searchSong_By_Name",method = RequestMethod.GET)
    public Object searchSong_By_Name(HttpServletRequest request){
        String name=request.getParameter("songName");
        return songService.searchSong_By_Name(name);
    }
    //根据歌名模糊查询歌曲
    @RequestMapping(value = "/searchSong_Like_Name",method = RequestMethod.GET)
    public Object searchSong_Like_Name(HttpServletRequest request){
        String name=request.getParameter("songName");
        return songService.searchSong_By_Name(name);
    }

    //查询所有歌曲
    @RequestMapping(value = "/allSong",method = RequestMethod.GET)
    public Object search_All_Song(){
        return songService.search_All_Song();
    }
}
