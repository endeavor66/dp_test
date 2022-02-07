package com.example.dp.vo;

import java.util.List;
import lombok.Builder;

@Builder
public class GetPredictSalesVO {
    List<String> times;
    List<Double> actual;
    List<Double> predict;

    public GetPredictSalesVO() {
    }

    public GetPredictSalesVO(List<String> times, List<Double> actual, List<Double> predict) {
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

    public List<Double> getActual() {
        return actual;
    }

    public void setActual(List<Double> actual) {
        this.actual = actual;
    }

    public List<Double> getPredict() {
        return predict;
    }

    public void setPredict(List<Double> predict) {
        this.predict = predict;
    }
}
