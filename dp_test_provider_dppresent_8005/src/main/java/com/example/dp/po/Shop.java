package com.example.dp.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

import lombok.Builder;

@Builder
public class Shop {
    private String shop_id;

    private String shop_name;

    private String shop_location;

    private String shop_type;

    private String shop_synopsis;

    private String found_date;

    private String shop_director;

    private int shop_capital;

    private String db_source;

    public Shop() {
    }

    public Shop(String shop_id, String shop_name, String shop_location, String shop_type, String shop_synopsis, String found_date, String shop_director, int shop_capital, String db_source) {
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_location = shop_location;
        this.shop_type = shop_type;
        this.shop_synopsis = shop_synopsis;
        this.found_date = found_date;
        this.shop_director = shop_director;
        this.shop_capital = shop_capital;
        this.db_source = db_source;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
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

    public String getFound_date() {
        return found_date;
    }

    public void setFound_date(String found_date) {
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

    public String getDb_source() {
        return db_source;
    }

    public void setDb_source(String db_source) {
        this.db_source = db_source;
    }
}
