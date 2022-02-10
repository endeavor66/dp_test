package com.example.dp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FileProcessProvider_8006 {
    public static void main(String[] args){
        SpringApplication.run(FileProcessProvider_8006.class,args);
    }
}