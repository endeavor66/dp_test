package com.example.dp.vo;

import com.example.dp.po.ReturnC;
import com.example.dp.po.SalesC;
import lombok.Builder;

import java.util.List;

@Builder
public class ImportDataVO {
    String type;
    List<SalesC> salesCS;
    List<ReturnC> returnCS;
    String shopId;

    public ImportDataVO() {
    }

    public ImportDataVO(String type, List<SalesC> salesCS, List<ReturnC> returnCS, String shopId) {
        this.type = type;
        this.salesCS = salesCS;
        this.returnCS = returnCS;
        this.shopId = shopId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SalesC> getSalesCS() {
        return salesCS;
    }

    public void setSalesCS(List<SalesC> salesCS) {
        this.salesCS = salesCS;
    }

    public List<ReturnC> getReturnCS() {
        return returnCS;
    }

    public void setReturnCS(List<ReturnC> returnCS) {
        this.returnCS = returnCS;
    }
}
