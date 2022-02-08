package com.example.dp.vo;

import lombok.Builder;

import java.util.List;

public class RecommendProductListVO {
    List<RecommendProductInfVO> recomendProductVOS;

    public RecommendProductListVO() {
    }

    public RecommendProductListVO(List<RecommendProductInfVO> recomendProductVOS) {
        this.recomendProductVOS = recomendProductVOS;
    }

    public List<RecommendProductInfVO> getRecomendProductVOS() {
        return recomendProductVOS;
    }

    public void setRecomendProductVOS(List<RecommendProductInfVO> recomendProductVOS) {
        this.recomendProductVOS = recomendProductVOS;
    }
}
