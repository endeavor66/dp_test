package com.example.dp.vo;

import lombok.Builder;

import java.util.List;

@Builder
public class RecommendProductListVO {
    List<RecomendProductVO> recomendProductVOS;

    public RecommendProductListVO() {
    }

    public RecommendProductListVO(List<RecomendProductVO> recomendProductVOS) {
        this.recomendProductVOS = recomendProductVOS;
    }

    public List<RecomendProductVO> getRecomendProductVOS() {
        return recomendProductVOS;
    }

    public void setRecomendProductVOS(List<RecomendProductVO> recomendProductVOS) {
        this.recomendProductVOS = recomendProductVOS;
    }
}
