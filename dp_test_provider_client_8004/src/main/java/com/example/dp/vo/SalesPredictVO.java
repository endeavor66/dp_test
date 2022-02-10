package com.example.dp.vo;

import lombok.Builder;

public class SalesPredictVO {

    String times;

    Double actual;

    Double predict;

    public SalesPredictVO(String times, Double actual, Double predict) {
        this.times = times;
        this.actual = actual;
        this.predict = predict;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    public Double getPredict() {
        return predict;
    }

    public void setPredict(Double predict) {
        this.predict = predict;
    }
}
