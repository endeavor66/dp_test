package com.example.dp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class dpPresentProvider_8005 {

    public static void main(String[] args){
        SpringApplication.run(dpPresentProvider_8005.class,args);
    }

    @Bean
    public RestTemplate initRestTemplate() {
        return new RestTemplate();
    }
}
