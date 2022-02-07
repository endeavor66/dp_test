package com.example.dp.vo;

import com.example.dp.po.Store;

import java.util.Date;
import lombok.Builder;

@Builder
public class StoreVO {
    private String id;

    private String shop_name;

    private String shop_location;

    private String shop_type;

    private String shop_synopsis;

    private Date found_date;

    private String shop_director;

    private int shop_capital;

    public StoreVO() {
    }

    public StoreVO(String id, String shop_name, String shop_location, String shop_type, String shop_synopsis, Date found_date, String shop_director, int shop_capital) {
        this.id = id;
        this.shop_name = shop_name;
        this.shop_location = shop_location;
        this.shop_type = shop_type;
        this.shop_synopsis = shop_synopsis;
        this.found_date = found_date;
        this.shop_director = shop_director;
        this.shop_capital = shop_capital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_location() {
        return shop_location;
    }

    public void setShop_location(String shop_location) {
        this.shop_location = shop_location;
    }

    public String getShop_type() {
        return shop_type;
    }

    public void setShop_type(String shop_type) {
        this.shop_type = shop_type;
    }

    public String getShop_synopsis() {
        return shop_synopsis;
    }

    public void setShop_synopsis(String shop_synopsis) {
        this.shop_synopsis = shop_synopsis;
    }

    public Date getFound_date() {
        return found_date;
    }

    public void setFound_date(Date found_date) {
        this.found_date = found_date;
    }

    public String getShop_director() {
        return shop_director;
    }

    public void setShop_director(String shop_director) {
        this.shop_director = shop_director;
    }

    public int getShop_capital() {
        return shop_capital;
    }

    public void setShop_capital(int shop_capital) {
        this.shop_capital = shop_capital;
    }

    public StoreVO(Store store){
        this.id = store.getId();
        this.shop_name = store.getShop_name();
        this.shop_location = store.getShop_location();
        this.shop_type = store.getShop_type();
        this.shop_synopsis=store.getShop_synopsis();
        this.found_date = store.getFound_date();
        this.shop_director = store.getShop_director();
        this.shop_capital= store.getShop_capital();
    }
}
