package com.cn.wk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by wk on 2019/3/15.
 */
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){

        return "hello";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","你好！");

        //resources\templates\success.html
        return "success";
    }
}
