package com.example.dp.pojo;

import lombok.Builder;

import java.util.Date;

public class ReturnC {
    String id;
    String sales_id;
    Date return_date;
    String product_id;
    String reason;
    String return_state;

    public ReturnC() {
    }

    public ReturnC(String id, String sales_id, Date return_date, String product_id, String reason, String return_state) {
        this.id = id;
        this.sales_id = sales_id;
        this.return_date = return_date;
        this.product_id = product_id;
        this.reason = reason;
        this.return_state = return_state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReturn_state() {
        return return_state;
    }

    public void setReturn_state(String return_state) {
        this.return_state = return_state;
    }
}
