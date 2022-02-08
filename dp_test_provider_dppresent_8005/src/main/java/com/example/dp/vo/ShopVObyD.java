package com.example.dp.vo;

import lombok.Builder;

import java.util.Date;

@Builder
public class ShopVObyD {
    String id;
    String name;
    String location;
    String type;
    Date date;
    String info;
    String managerName;

    public ShopVObyD() {
    }

    public ShopVObyD(String id, String name, String location, String type, Date date, String info, String managerName) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
        this.date = date;
        this.info = info;
        this.managerName = managerName;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
