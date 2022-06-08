package com.chengdu.longblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.chengdu.longblog.mapper")
@SpringBootApplication
public class LongblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LongblogApplication.class, args);
    }

}
