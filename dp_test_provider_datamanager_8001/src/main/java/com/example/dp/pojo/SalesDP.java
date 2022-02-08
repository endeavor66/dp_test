package com.example.dp.pojo;

import java.util.Date;

public class SalesDP {
    String id;
    String shop_id;
    String product_id;
    Date sales_date;
    int num;
    double price;
    double sales_sum;

    public SalesDP(String id, String shop_id, String product_id, Date sales_date, int num, double price) {
        this.id = id;
        this.shop_id = shop_id;
        this.product_id = product_id;
        this.sales_date = sales_date;
        this.num = num;
        this.price = price;
        this.sales_sum=num*price;
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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Date getSales_date() {
        return sales_date;
    }

    public void setSales_date(Date sales_date) {
        this.sales_date = sales_date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSales_sum() {
        return sales_sum;
    }

    public void setSales_sum(double sales_sum) {
        this.sales_sum = sales_sum;
    }
}
