package org.example.wqhotelsystem.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by wuqi on 2023/1/16
 */

@Configuration
public class DruidConfig {
    /*
       将自定义的 Druid数据源添加到容器中，不再让 Spring Boot 自动创建
       绑定全局配置文件中的 druid 数据源属性到 com.alibaba.druid.pool.DruidDataSource从而让它们生效
       @ConfigurationProperties(prefix = "spring.datasource")：作用就是将 全局配置文件中
       前缀为 spring.datasource的属性值注入到 com.alibaba.druid.pool.DruidDataSource 的同名参数中
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();

    }

    // 后台监控：相当于web.xml，配置ServletRegistrationBean
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        // 后台登录进行账号密码配置
        HashMap<String, String> initParameters = new HashMap<>();

        // 增加配置，登录的key是固定的，loginUsername  loginPassword
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","admin123");

        // 允许谁可以访问，如果后面的参数为空代表谁都可以访问，指定参数只能指定的参数进行访问
        initParameters.put("allow","");

        // 禁止谁可以访问
//        initParameters.put("koko","192.168.43.21");


        servletRegistrationBean.setInitParameters(initParameters); // 设置初始化参数
        return servletRegistrationBean;
    }

    // filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        // 可以过滤的请求
        HashMap<String, String> initParameters = new HashMap<>();
        // 以下不进行统计
        initParameters.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        return bean;
    }

}
