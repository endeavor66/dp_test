package com.example.dp.service;

import com.example.dp.pojo.Shop;

import java.util.List;

public interface ShopService {

    public boolean addShop(Shop shop);

    public Shop queryById(String id);

    public List<Shop> queryAll();


}
