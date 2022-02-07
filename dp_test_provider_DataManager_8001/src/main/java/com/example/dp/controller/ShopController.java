package com.example.dp.controller;


import com.example.dp.pojo.Shop;
import com.example.dp.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/shop/add")
    public boolean addShop(Shop shop){
        return shopService.addShop(shop);
    }

    @GetMapping("/shop/get/{id}")
    public Shop queryById(@PathVariable("id") String id){
        return shopService.queryById(id);
    }

    @GetMapping("/shop/list")
    public List<Shop> queryAll(){
        return shopService.queryAll();
    }
}
