package com.example.dp.vo;

public class AllShopInfoVO {
    String id;
    String name;
    String location;
    double sales;
    boolean salesRTrend;
    String returnRate;
    boolean returnRateTrend;

    public AllShopInfoVO(String id, String name, String location, double sales, boolean salesRTrend, String returnRate, boolean returnRateTrend) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.sales = sales;
        this.salesRTrend = salesRTrend;
        this.returnRate = returnRate;
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

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public boolean isSalesRTrend() {
        return salesRTrend;
    }

    public void setSalesRTrend(boolean salesRTrend) {
        this.salesRTrend = salesRTrend;
    }

    public String getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(String returnRate) {
        this.returnRate = returnRate;
    }

    public boolean isReturnRateTrend() {
        return returnRateTrend;
    }

    public void setReturnRateTrend(boolean returnRateTrend) {
        this.returnRateTrend = returnRateTrend;
    }
}
