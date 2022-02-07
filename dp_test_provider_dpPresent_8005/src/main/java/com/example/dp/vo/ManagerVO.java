package com.example.dp.vo;

import com.example.dp.po.Manager;
import lombok.Builder;

@Builder
public class ManagerVO {
    String id ;
    String shop_id;
    String name;
    String code;
    int power;

    public ManagerVO() {
    }

    public ManagerVO(String id, String shop_id, String name, String code, int power) {
        this.id = id;
        this.shop_id = shop_id;
        this.name = name;
        this.code = code;
        this.power = power;
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

    public ManagerVO(Manager manager){
        this.id =manager.getId();
        this.shop_id = manager.getShop_id();
        this.name =manager.getName();
        this.code = manager.getCode();
        this.power = manager.getPower();
    }
}
