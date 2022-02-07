package com.example.dp.po;

import java.util.Date;
import lombok.Builder;

@Builder
public class PredictProduct {
    String id;
    String shop_id;
    Date forecast_date;
    String product_id;
    double forecast_result;

    public PredictProduct() {
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

    public void setForecast_result(double forecast_result) {
        this.forecast_result = forecast_result;
    }

    public PredictProduct(String id, String shop_id, Date forecast_date, String product_id, double forecast_result){
        this.id =id;
        this.shop_id = shop_id ;
        this.forecast_date = forecast_date;
        this.product_id =product_id;
        this.forecast_date =forecast_date;
    }
}
