package com.cn.wk.config;

import com.cn.wk.filter.MyFilter;
import com.cn.wk.listener.MyListener;
import com.cn.wk.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 自定义服务器配置
 * Created by wk on 2019/3/28.
 */
@Configuration
public class MyServerConfig {

    /**
     * 配置嵌入式的Servlet容器
     * SpringBoot 2.X 以后废弃了（EmbeddedServletContainerCustomizer：嵌入式的Servlet容器的定制器；）
     *  用WebServerFactoryCustomizer替代
     * 来修改Servlet容器的配置
     */
    @Bean //一定要将这个定制器加入到容器中
    public WebServerFactoryCustomizer webServerFactoryCustomizer(){

        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            //定制嵌入式的servlet容器相关的规则
            @Override
            public void customize(ConfigurableWebServerFactory configurableWebServerFactory) {
                configurableWebServerFactory.setPort(8083);
            }
        };
    }

    /**
     * 注册Servlet组件
     * @return
     */
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean registrationBean =
                new ServletRegistrationBean(new MyServlet(),"/myServlet");
        return registrationBean;
    }

    /**
     * 注册Filter组件
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        //拦截的url
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }

    /**
     * 注册Listener组件
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean =
                new ServletListenerRegistrationBean<MyListener>(new MyListener());
        return registrationBean;
    }
}
