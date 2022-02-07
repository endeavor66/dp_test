package com.example.dp.po;
import lombok.Builder;

@Builder
public class ShopInfNeed {
    String id;
    String name;

    public ShopInfNeed() {
    }

    public ShopInfNeed(String id, String name) {
        this.id = id;
        this.name = name;
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
}
