package com.example.dp.pojo;

public class PredictReturn_whithoutID {

    String shop_id;

    String product_id;

    String date;

    int return_num;

    public PredictReturn_whithoutID(String shop_id, String product_id, String date, int return_num) {
        this.shop_id = shop_id;
        this.product_id = product_id;
        this.date = date;
        this.return_num = return_num;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getReturn_num() {
        return return_num;
    }

    public void setReturn_num(int return_num) {
        this.return_num = return_num;
    }
}
