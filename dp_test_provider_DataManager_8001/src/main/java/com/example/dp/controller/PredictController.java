package com.example.dp.controller;
import com.example.dp.ProviderApi.ForecastProviderApi;

import com.example.dp.pojo.PredictProduct_whithoutID;
import com.example.dp.pojo.ReturnDP;
import com.example.dp.pojo.SalesDP;
import com.example.dp.service.PredictService;
import com.example.dp.vo.ResponseVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Api(value = "预测微服务测试", tags = "预测微服务测试")
@RestController
public class PredictController {
    @Autowired
    private PredictService predictService;
    //private DateManagerApi.DataManagerApi dataManagerApi;

    @Autowired
    private ForecastProviderApi forecastProviderApi;

    @RequestMapping("/provider_DataManager/predict/getSalesNum")
    public ResponseVO getSalesNum(List<String> id){
        return predictService.getSalesNum(id);
    }

    @GetMapping("/provider_DataManager/predict/DailyRenew")
    public ResponseVO postDailyRenew(List<SalesDP> newdata) throws InterruptedException {
        return predictService.DailyRenew(newdata);
    }

    @GetMapping("/provider_DataManager/predict/ReturnRenew")
    public ResponseVO postReturnRenew(List<ReturnDP> newdata) throws InterruptedException {
        return predictService.ReturnRenew(newdata);
    }

    @PostMapping("/provider_DataManager/predict/postSalesModelInit")
    public ResponseVO postSalesModelInit(List<SalesDP> newdata) throws InterruptedException {
        return predictService.SalesModelInit(newdata);
    }

    @PostMapping("/provider_DataManager/predict/postReturnModelInit")
    public ResponseVO postReturnModelInit(List<ReturnDP> newdata) throws InterruptedException {
        return predictService.ReturnModelInit(newdata);
    }


    @RequestMapping("/provider_DataManager/insertPredictResult")
    public ResponseVO insertPredictResult(List<PredictProduct_whithoutID> newdata){
        return predictService.insertPredictResult_product(newdata);
    }
}
