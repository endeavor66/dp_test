package com.example.dp.vo;

import java.util.List;

public class GetAllPredictSalesVO {
    List<String> times;
    List<Integer> actual;
    List<Double> predict;

    public GetAllPredictSalesVO(List<String> times, List<Integer> actual, List<Double> predict) {
        this.times = times;
        this.actual = actual;
        this.predict = predict;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public List<Integer> getActual() {
        return actual;
    }

    public void setActual(List<Integer> actual) {
        this.actual = actual;
    }

    public List<Double> getPredict() {
        return predict;
    }

    public void setPredict(List<Double> predict) {
        this.predict = predict;
    }
}
