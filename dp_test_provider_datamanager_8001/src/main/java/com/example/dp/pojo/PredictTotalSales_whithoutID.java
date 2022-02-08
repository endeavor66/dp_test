package com.example.dp.pojo;

import java.util.Date;

public class PredictTotalSales_whithoutID {


    String shop_id;

    Date date;

    Double result;

    Double actual;

    public PredictTotalSales_whithoutID( String shop_id, Date date, Double result, Double actual) {
        this.shop_id = shop_id;
        this.date = date;
        this.result = result;
        this.actual = actual;
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

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

}
