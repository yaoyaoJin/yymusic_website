package com.music.yymusic_website.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.music.yymusic_website.domain.ListSong;
import com.music.yymusic_website.service.ListSongService;
import com.music.yymusic_website.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.tokens.FlowEntryToken;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/listSong")
@CrossOrigin
public class ListSongController {
    @Autowired
    ListSongService listSongService;
    @RequestMapping(value = "/addSong",method = RequestMethod.POST)
    public Object addSong_To_List(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String songId=request.getParameter("songId");
        String songListId=request.getParameter("songListId");
        //将获取的数据放到歌单歌曲中
        ListSong listSong=new ListSong();
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        //向歌单歌曲中添加元素
        boolean res=listSongService.addSong_To_List(listSong);
        if(res){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    //根据歌单id查询歌曲
    @RequestMapping(value = "/searchSong_By_ListId",method = RequestMethod.GET)
    public Object searchSong_By_ListId(HttpServletRequest request){
        String songListId=request.getParameter("songListId");
        return listSongService.searchSong_By_ListId(Integer.parseInt(songListId));
    }

    //修改歌单歌曲列表
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateListSong(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        String id=request.getParameter("id");
        String songId=request.getParameter("songId");
        String songListId=request.getParameter("songListId");
        //将获取信息放到歌单歌曲对象中
        ListSong listSong=new ListSong(Integer.parseInt(id),Integer.parseInt(songId),Integer.parseInt(songListId));
        //更新
        boolean res=listSongService.updateListSong(listSong);
        if(res){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }
    //删除歌单歌曲
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteListSong(HttpServletRequest request){
        String songId=request.getParameter("songId");
        String songListId=request.getParameter("songListId");
        boolean res=listSongService.deleteSong_From_List(Integer.parseInt(songId),Integer.parseInt(songListId));
        return res;
    }


}
