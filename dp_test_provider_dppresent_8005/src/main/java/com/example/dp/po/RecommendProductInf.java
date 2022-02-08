package com.example.dp.po;

import java.util.List;
import lombok.Builder;

@Builder
public class RecommendProductInf {
    String id; //产品id
    String name;//产品名称
    String reason;//推荐理由
    List<Material> materials;
    List<Supplier> suppliers;

    public RecommendProductInf() {
    }

    public RecommendProductInf(String id, String name, String reason, List<Material> materials, List<Supplier> suppliers) {
        this.id = id;
        this.name = name;
        this.reason = reason;
        this.materials = materials;
        this.suppliers = suppliers;
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

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
