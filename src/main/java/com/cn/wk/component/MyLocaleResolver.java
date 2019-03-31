package com.cn.wk.component;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义区域信息解析器，实现自己控制 使用语言信息
 * 可以在链接上携带区域信息
 * Created by wk on 2019/3/16.
 */
public class MyLocaleResolver implements LocaleResolver {

    //解析区域信息
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取url请求上的属性值
        String l = httpServletRequest.getParameter("l");
        //实例化区域信息(获取默认区域信息)
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)){
            //对属性值进行分割
            String[] split = l.split("_");
            //参数1：语言信息， 参数2：国家信息
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, @Nullable HttpServletResponse httpServletResponse, @Nullable Locale locale) {

    }
}
