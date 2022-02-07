package com.example.dp.vo;

import java.util.List;

public class PredictReturnVO {
    String id ;//商品id
    String name; //商品名称
    List<String> times;
    List<Integer> result;

    public PredictReturnVO(String id, String name, List<String> times, List<Integer> result) {
        this.id = id;
        this.name = name;
        this.times = times;
        this.result = result;
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

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public List<Integer> getResult() {
        return result;
    }

    public void setResult(List<Integer> result) {
        this.result = result;
    }
}
