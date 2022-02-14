package com.music.yymusic_website.controller;


import com.alibaba.fastjson.JSONObject;
import com.music.yymusic_website.service.AdminService;
import com.music.yymusic_website.service.impl.AdminServiceImpl;
import com.music.yymusic_website.utils.Consts;
import com.music.yymusic_website.utils.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;

@RestController //即@Controller和@ResponseBody结合，只能返回实体模型
@Api(value = "提供管理员登录校验功能",tags = "管理员")
@CrossOrigin //允许跨域
public class AdminController {
    @Autowired
    private AdminService adminService;
    //判断是否登陆成功
    @ApiOperation("管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String",name = "name",value = "登录名",required = true),
            @ApiImplicitParam(dataType = "String",name = "password",value = "登陆密码",required = false,defaultValue = "123456")
    })
    @RequestMapping(value = "/admin/login_test/status",method = RequestMethod.POST)
    public ResultVO login(@RequestParam("name") String name,@RequestParam("password") String password){
        if(adminService.verifyPassword(name,password)){
            return new ResultVO(1,"登陆成功","name");
        }
        return new ResultVO(0,"用户名或密码错误",null);
    }

    @RequestMapping(value = "/admin/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String name=request.getParameter("name");
        String password =request.getParameter("password");
        if(adminService.verifyPassword(name,password)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登陆成功");
            session.setAttribute(Consts.NAME,name);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return jsonObject;
    }


}
