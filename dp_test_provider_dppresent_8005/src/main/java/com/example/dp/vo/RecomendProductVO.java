package com.example.dp.vo;

import com.example.dp.po.Material;

import java.util.List;

public class RecomendProductVO {
    String id;
    String name;
    String reason;
    List<Material> materials;

    public RecomendProductVO(String id, String name, String reason, List<Material> materials) {
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

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
}
