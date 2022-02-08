package com.example.dp.po;

import lombok.Builder;

@Builder
public class Manager {
    String id ;
    String shop_id;
    String name;
    String code;
    int power;

    public Manager() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Manager(String id, String shop_id, String name, String code, int power){
        this.id =id;
        this.shop_id = shop_id;
        this.name =name;
        this.code = code;
        this.power = power;
    }
}
