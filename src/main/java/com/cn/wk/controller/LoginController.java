package com.cn.wk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wk on 2019/3/16.
 */

@Controller
public class LoginController {

    /**
     * 登录
     * @param username ：用户名
     * @param password ：密码
     * @param map ：错误信息
     * @return
     */
    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session){
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登录成功之后，防止表单重复提交，可以重定向。
            session.setAttribute("loginUser",username);
            //重定向到main.html
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名密码错误！");
            return "login";
        }


    }
}
