package com.demo.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demo.mvc.mapper")
public class DemoAliPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAliPayApplication.class, args);
    }

}

