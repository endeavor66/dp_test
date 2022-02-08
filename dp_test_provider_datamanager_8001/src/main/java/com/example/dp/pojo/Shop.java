package com.example.dp.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Shop implements Serializable {
    private String shop_id;

    private String shop_name;

    private String shop_location;

    private String shop_type;

    private String shop_synopsis;

    private String found_date;

    private String shop_director;

    private int shop_capital;

    private String db_source;

    public Shop(String shop_id, String shop_name, String shop_type, String found_date) {
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_type = shop_type;
        this.found_date = found_date;
    }
}
