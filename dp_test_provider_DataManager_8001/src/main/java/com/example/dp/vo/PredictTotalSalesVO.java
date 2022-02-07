package com.example.dp.vo;

import com.example.dp.pojo.PredictTotalSales;

import java.util.Date;

public class PredictTotalSalesVO {
    String id;//当成产品id?
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

    public PredictTotalSalesVO(PredictTotalSales predictTotalSales){
        this.id =predictTotalSales.getId();
        this.shop_id = predictTotalSales.getShop_id();
        this.date = predictTotalSales.getDate();
        this.result =predictTotalSales.getResult();
        this.actual =predictTotalSales.getActual();
    }
}
