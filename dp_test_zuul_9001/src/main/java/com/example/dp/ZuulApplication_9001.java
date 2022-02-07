package com.example.dp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication_9001 {

    public static void main(String[] args){
        SpringApplication.run(ZuulApplication_9001.class,args);

    }
}
