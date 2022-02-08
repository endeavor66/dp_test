package com.example.dp.pojo;

import java.util.Date;

public class PredictProduct_whithoutID {

    String shop_id;
    Date forecast_date;
    String product_id;
    Integer forecast_result;





    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public Date getForecast_date() {
        return forecast_date;
    }

    public void setForecast_date(Date forecast_date) {
        this.forecast_date = forecast_date;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public double getForecast_result() {
        return forecast_result;
    }

    public void setForecast_result(Integer forecast_result) {
        this.forecast_result = forecast_result;
    }

    public PredictProduct_whithoutID(String shop_id, Date forecast_date, String product_id, Integer forecast_result){
        this.shop_id = shop_id ;
        this.forecast_date = forecast_date;
        this.product_id =product_id;
        this.forecast_result =forecast_result;
    }
}
