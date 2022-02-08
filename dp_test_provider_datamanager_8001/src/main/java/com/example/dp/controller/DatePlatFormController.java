package com.example.dp.controller;

import com.example.dp.ProviderApi.DateManagerApi;
import com.example.dp.pojo.RecommendRelation;
import com.example.dp.service.DatePlatFormService;
import com.example.dp.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "数据管理微服务测试", tags = "数据管理微服务测试")
@RestController
public class DatePlatFormController {
    @Autowired
    private DatePlatFormService datePlatFormService;
    //private DateManagerApi.DataManagerApi dataManagerApi;

    @ApiOperation(value = "登录", notes = "login")
    @RequestMapping("/provider_DataManager/login")
    public Object loginResult(@RequestParam String userName,@RequestParam String passWord){
        return datePlatFormService.loginResult(userName,passWord);
    }

    @ApiOperation(value = "登录", notes = "login")
    @RequestMapping("/provider_DataManager/loginInfo")
    public Object loginInfo(String userName){
        return datePlatFormService.loginInfo(userName);
    }

    @ApiOperation(value = "登录", notes = "login")
    @RequestMapping("/provider_DataManager/getShopInfo")
    public Object getStoreInfById(String id){
        return datePlatFormService.getStoreInfById(id);
    }

    @ApiOperation(value = "登录", notes = "login")
    @RequestMapping("/provider_DataManager/getRecommendProduct")
    public Object getRecommendProductInfByStoreId(String storeId){
        return datePlatFormService.getRecommendProductInfByStoreId(storeId);
    }

    @ApiOperation(value = "登录", notes = "login")
    @RequestMapping("/provider_DataManager/getPredictSales")
    public Object getPredictSales(String storeId,String commodityId,String type){
        return datePlatFormService.getPredictSales(storeId,commodityId,type);
    }

    @ApiOperation(value = "登录", notes = "login")
    @RequestMapping("/provider_DataManager/getPredicrtReturn")
    public Object getPredicrtReturn(String storeId, String commodityId){
        return datePlatFormService.getPredicrtReturn(storeId,commodityId);
    }

    @ApiOperation(value = "登录", notes = "login")
    @RequestMapping("/provider_DataManager/dateplatform/getProductRelationInf")
    public Object getProductRelationInf(String product_id1, String product_id2){
        return datePlatFormService.getProductRelationInf(product_id1,product_id2);
    }

    @ApiOperation(value = "登录", notes = "login")
    @RequestMapping("/provider_DataManager/getAllPredictSales")
    public Object getAllPredictSales(List<String> storeId,String type){
        return datePlatFormService.getAllPredictSales(storeId,type);
    }

    @ApiOperation(value = "登录", notes = "login")
    @RequestMapping("/provider_DataManager/getAllPredictReturn")
    public Object getAllPredictReturn(List<String> storeId){
        return datePlatFormService.getAllPredictReturn(storeId);
    }

    @ApiOperation(value = "导入数据", notes = "import")
    @RequestMapping("/importData")
    public Object importData(ImportDataVO importDataVO){
        return datePlatFormService.importData(importDataVO);
    }
}
