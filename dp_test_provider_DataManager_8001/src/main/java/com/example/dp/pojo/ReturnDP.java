package com.example.dp.pojo;

import java.util.Date;

public class ReturnDP {
    String id;
    String shop_id;
    String sales_id;
    Date return_date;
    String product_id;
    int num;

    public ReturnDP(String id, String shop_id, String sales_id, Date return_date, String product_id, int num) {
        this.id = id;
        this.shop_id = shop_id;
        this.sales_id = sales_id;
        this.return_date = return_date;
        this.product_id = product_id;
        this.num = num;
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

    public String getSales_id() {
        return sales_id;
    }

    public void setSales_id(String sales_id) {
        this.sales_id = sales_id;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
