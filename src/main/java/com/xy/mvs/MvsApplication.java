package com.xy.mvs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.xy")
@MapperScan("com.xy.mvs.mapper")
public class MvsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvsApplication.class, args);
    }
}
