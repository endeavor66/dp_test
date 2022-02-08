package com.example.dp.vo;

import com.example.dp.pojo.RecommendResult;

import java.util.Date;

public class RecommendResultVO {
    String id;
    String shop_id;
    Date recommand_date;
    String product_id;
    double weight;
    String reason;

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

    public Date getRecommand_date() {
        return recommand_date;
    }

    public void setRecommand_date(Date recommand_date) {
        this.recommand_date = recommand_date;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RecommendResultVO(RecommendResult recommendResult){
        this.id = recommendResult.getId();
        this.shop_id = recommendResult.getShop_id();
        this.product_id = recommendResult.getProduct_id();
        this.recommand_date =recommendResult.getRecommand_date();
        this.weight =recommendResult.getWeight();
        this.reason =recommendResult.getReason();
    }
}
