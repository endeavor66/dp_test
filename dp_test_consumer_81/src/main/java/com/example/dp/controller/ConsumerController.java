package com.example.dp.controller;

import com.example.dp.ProviderApi.DataManagerApi;
import com.example.dp.pojo.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    private DataManagerApi dataManagerApi;

    //通过resttemplate的两种方法向提供者请求
    //private static final String REST_URL_PREFIX = "http://localhost:8001";
    //private static final String REST_URL_PREFIX = "http://DP_TEST_PROVIDER_DATAMANAGER";

    //现在用feign向提供者请求
    @RequestMapping("/datamanager/shop/get/{id}")
    public Shop get(@PathVariable("id") String id){
        return dataManagerApi.queryById(id);
    }

    @RequestMapping("/datamanager/shop/add")
    public boolean add(Shop shop){
        return dataManagerApi.addShop(shop);
    }

    @RequestMapping("/datamanager/shop/list")
    public List<Shop> list(){
        return dataManagerApi.queryAll();
    }
}
