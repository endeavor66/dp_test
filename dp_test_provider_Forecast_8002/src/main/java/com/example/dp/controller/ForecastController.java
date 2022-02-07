package com.example.dp.controller;

import com.example.dp.po.Sales;
import com.example.dp.po.ReturnDP;
import com.example.dp.po.SalesDP;
import com.example.dp.service.ForecastService;
import com.example.dp.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "预测微服务测试", tags = "预测微服务测试")
@RestController
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    @ApiOperation(value = "测试", notes = "test")
    @GetMapping("/test")
    public ResponseVO test(){
        return forecastService.test();
    }

    @ApiOperation(value = "测试2", notes = "test2")
    @GetMapping("/test2")
    public ResponseVO test2(){
        return forecastService.test2();
    }

    @ApiOperation(value = "销售预测更新", notes = "DailyRenew")
    @GetMapping("/DailyRenew")
    public List<Integer> DailyRenew(List<SalesDP> newdata){
        return forecastService.DailyRenew(newdata);
    }

    @ApiOperation(value = "退货预测更新", notes = "ReturnRenew")
    @GetMapping("/ReturnRenew")
    public ResponseVO ReturnRenew(List<ReturnDP> newdata){
        return forecastService.ReturnRenew(newdata);
    }

    @ApiOperation(value = "销售预测模型初始化/更新", notes = "SalesModelInit")
    @PostMapping("/SalesModelInit")
    public ResponseVO SalesModelInit(List<SalesDP> newdata){
        return forecastService.SalesModelInit(newdata);
    }

    @ApiOperation(value = "退货预测模型初始化/更新", notes = "ReturnModelInit")
    @PostMapping("/ReturnModelInit")
    public ResponseVO ReturnModelInit(List<ReturnDP> newdata){
        return forecastService.ReturnModelInit(newdata);
    }



}
