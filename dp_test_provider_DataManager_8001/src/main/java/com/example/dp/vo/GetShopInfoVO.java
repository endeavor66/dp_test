package com.example.dp.vo;

public class GetShopInfoVO {
    String id;
    String name;
    String location;
    int sales;
    String salesTrend;
    int returnNum;//今日退货率
    String returnRateTrend;

    public GetShopInfoVO(String id, String name, String location, int sales, String salesTrend, int returnNum, String returnRateTrend) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.sales = sales;
        this.salesTrend = salesTrend;
        this.returnNum = returnNum;
        this.returnRateTrend = returnRateTrend;
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

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getSalesTrend() {
        return salesTrend;
    }

    public void setSalesTrend(String salesTrend) {
        this.salesTrend = salesTrend;
    }

    public int getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(int returnNum) {
        this.returnNum = returnNum;
    }

    public String getReturnRateTrend() {
        return returnRateTrend;
    }

    public void setReturnRateTrend(String returnRateTrend) {
        this.returnRateTrend = returnRateTrend;
    }
}
