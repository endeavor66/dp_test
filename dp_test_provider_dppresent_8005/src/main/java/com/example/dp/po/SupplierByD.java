package com.example.dp.po;

import lombok.Builder;

@Builder
public class SupplierByD {
    String id;
    String name ;
    String location;
    int star;
    double time_parameter;
    double fare_parameter;

    public SupplierByD() {
    }

    public SupplierByD(String id, String name, String location, int star, double time_parameter, double fare_parameter) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.star = star;
        this.time_parameter = time_parameter;
        this.fare_parameter = fare_parameter;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public double getTime_parameter() {
        return time_parameter;
    }

    public void setTime_parameter(double time_parameter) {
        this.time_parameter = time_parameter;
    }

    public double getFare_parameter() {
        return fare_parameter;
    }

    public void setFare_parameter(double fare_parameter) {
        this.fare_parameter = fare_parameter;
    }
}
