package com.example.dp.vo;

import com.example.dp.po.Shop;

import java.util.List;

public class LoginVO {
    String id;
    String name;
    List<Shop> shops;

    public LoginVO(String id, String name, List<Shop> shops) {
        this.id = id;
        this.name = name;
        this.shops = shops;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
