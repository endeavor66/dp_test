package com.example.dp.controller;

import com.example.dp.service.RecommendService;
import com.example.dp.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

    @RequestMapping("/provider_DataManager/recommend/getSalesNum")
    public ResponseVO getSalesNum(List<String> id){
        return recommendService.getSalesNum(id);
    }

    @RequestMapping("/provider_DataManager/insertRecommendResult")
    public ResponseVO insertRecommendResult(String shop_id, Date recommand_date, String product_id, double weight, String reason){
        return recommendService.insertRecommendResult(shop_id,recommand_date,product_id,weight,reason);
    }

    @RequestMapping("/provider_DataManager/recommend/getProductRelationInf")
    public ResponseVO getProductRelationInf(String product_id1, String product_id2){
        return recommendService.getProductRelationInf(product_id1,product_id2);
    }
}
