package com.example.dp.vo;

import java.util.List;

public class RecommendProductInfVO {
    String id; //产品id
    String name;//产品名称
    String reason;//推荐理由
    List<MaterialSuppliersVO> materials;

    public RecommendProductInfVO() {
    }

    public RecommendProductInfVO(String id, String name, String reason, List<MaterialSuppliersVO> materials) {
        this.id = id;
        this.name = name;
        this.reason = reason;
        this.materials = materials;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public List<MaterialSuppliersVO> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialSuppliersVO> materials) {
        this.materials = materials;
    }
}
