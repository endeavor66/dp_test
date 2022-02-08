package com.example.dp.vo;

import com.example.dp.po.ShopInfNeed;

import java.util.List;
import lombok.Builder;

@Builder
public class ShopVO {
    String id;
    String name;
    List<ShopInfNeed> shops;

    public ShopVO() {
    }

    public ShopVO(String id, String name, List<ShopInfNeed> shops) {
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

    public List<ShopInfNeed> getShops() {
        return shops;
    }

    public void setShops(List<ShopInfNeed> shops) {
        this.shops = shops;
    }
}
