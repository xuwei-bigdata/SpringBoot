package com.aliyun.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.aliyun"})
public class XwEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(XwEduApplication.class, args);
    }

}
