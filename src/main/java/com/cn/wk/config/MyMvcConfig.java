package com.cn.wk.config;

import com.cn.wk.component.LoginHandlerInterceptor;
import com.cn.wk.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Map;


/**
 * 使用 WebMvcConfigurationSupport (新版本已弃用WebMvcConfigurerAdapter)可以来扩展SpringMVC的功能
 * Created by wk on 2019/3/16.
 */
@Configuration
public class MyMvcConfig implements  WebMvcConfigurer {
    //添加视图控制器
    @Override
    public  void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        //从浏览器发送 /atguigu 请求，来到 success页面
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //添加自定义拦截器
    @Override
    public  void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
            registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/login","/webjars/**");
    }

    //将自定义区域信息解析器加入到容器中
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
