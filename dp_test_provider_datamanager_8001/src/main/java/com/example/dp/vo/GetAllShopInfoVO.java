package com.example.dp.vo;

import java.util.List;

public class GetAllShopInfoVO {
    List<GetShopInfoVO> allShopInfo;

    public GetAllShopInfoVO(List<GetShopInfoVO> allShopInfo) {
        this.allShopInfo = allShopInfo;
    }

    public List<GetShopInfoVO> getAllShopInfo() {
        return allShopInfo;
    }

    public void setAllShopInfo(List<GetShopInfoVO> allShopInfo) {
        this.allShopInfo = allShopInfo;
    }
}
