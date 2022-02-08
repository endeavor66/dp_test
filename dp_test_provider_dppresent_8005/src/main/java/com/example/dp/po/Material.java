package com.example.dp.po;

import lombok.Builder;

import java.util.List;

@Builder
public class Material {
    String id;
    String name;
    List<Supplier> allSuppliers;

    public Material() {
    }

    public Material(String id, String name, List<Supplier> allSuppliers) {
        this.id = id;
        this.name = name;
        this.allSuppliers = allSuppliers;
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

    public List<Supplier> getAllSuppliers() {
        return allSuppliers;
    }

    public void setAllSuppliers(List<Supplier> allSuppliers) {
        this.allSuppliers = allSuppliers;
    }
}
