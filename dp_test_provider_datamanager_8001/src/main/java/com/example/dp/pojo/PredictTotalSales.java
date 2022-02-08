package com.example.dp.pojo;

import java.util.Date;

public class PredictTotalSales {
        String id;
        String shop_id;
        Date date;
        double result;//预测的结果
        double actual;//实际的销售结果

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }

    public PredictTotalSales(String id, String shop_id, Date date, double result, double actual){
            this.id =id;
            this.shop_id = shop_id ;
            this.date = date;
            this.result =result;
            this.actual =actual;
        }


}
