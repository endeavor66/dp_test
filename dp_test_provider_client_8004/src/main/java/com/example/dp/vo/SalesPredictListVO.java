package com.example.dp.vo;

import lombok.Builder;

import java.util.List;

public class SalesPredictListVO {
    List<SalesPredictVO> salesPredictVOS;

    public SalesPredictListVO() {
    }

    public SalesPredictListVO(List<SalesPredictVO> salesPredictVOS) {
        this.salesPredictVOS = salesPredictVOS;
    }

    public List<SalesPredictVO> getSalesPredictVOS() {
        return salesPredictVOS;
    }

    public void setSalesPredictVOS(List<SalesPredictVO> salesPredictVOS) {
        this.salesPredictVOS = salesPredictVOS;
    }
}
