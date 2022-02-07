package com.example.dp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class RecommandProvider_8003 {

    public static void main(String[] args){
        SpringApplication.run(RecommandProvider_8003.class,args);
    }
}
