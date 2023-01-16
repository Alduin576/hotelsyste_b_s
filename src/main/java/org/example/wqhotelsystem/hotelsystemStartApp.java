package org.example.wqhotelsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by wuqi on 2023/1/16
 */
@SpringBootApplication
@MapperScan("org.example.wqhotelsystem.mapper")
public class hotelsystemStartApp {
    //启动
    public static void main(String[] args) {
        SpringApplication.run(hotelsystemStartApp.class,args);
    }

}
