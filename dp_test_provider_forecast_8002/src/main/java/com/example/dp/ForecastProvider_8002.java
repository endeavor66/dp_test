package com.example.dp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ForecastProvider_8002 {

    public static void main(String[] args){
        SpringApplication.run(ForecastProvider_8002.class,args);
    }
}
