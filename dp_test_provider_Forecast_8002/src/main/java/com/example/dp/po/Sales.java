package com.example.dp.po;

public class Sales {
    /**
     * 销售订单id
     */
    String id;

    /**
     * 类型
     */
    String ShopId;


    /**
     * 类型
     */
    String productId;

    /**
     * 类型
     */
    int num;

    /**
     * 类型
     */
    Double price;

    /**
     * 类型
     */
    Double totalPrice;

    /**
     * 类型
     */
    String  date;


    public Sales(String id, String ShopId, String productId, int num, Double price, Double totalPrice, String date ) {
        this.id = id;
        this.ShopId = ShopId;
        this.productId = productId;
        this.num = num;
        this.price = price;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        this.ShopId = shopId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
