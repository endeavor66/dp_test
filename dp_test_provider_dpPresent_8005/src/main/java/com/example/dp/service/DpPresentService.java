package com.example.dp.service;

import com.example.dp.vo.ResponseVO;
import com.example.dp.vo.form.LoginForm;

import java.util.List;

public interface DpPresentService {
    Object login(String id,String code);

    Object loginData(String managerId);

    Object getShopInfo(String id);

    Object getRecommendProduct(String storeId);

    Object getPredictSales(String storeId, String commodityId, String type);

    Object getPredictReturn(String storeId,String commodityId);

    Object getAllPredictSales(List<String> storeId, String type);

    Object getAllPredictReturn(List<String> storeId);

    Object getAllShopInfo(List<String> storeId);

    Object importData(String fileContent,String type,String shopId);
}
