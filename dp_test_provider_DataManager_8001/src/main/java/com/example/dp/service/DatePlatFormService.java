package com.example.dp.service;

import com.example.dp.vo.ImportDataVO;
import com.example.dp.vo.ResponseVO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DatePlatFormService {
    //根据用户名，密码返回是否正确
    ResponseVO loginResult(String userName, String passWord);

    //根据用户名获取该用户拥有的门店列表
    ResponseVO loginInfo(String userName);

    //根据门店id获得对应的门店信息
    ResponseVO getStoreInfById(String id);

    //根据门店id获取最新推荐的产品信息
    ResponseVO getRecommendProductInfByStoreId(String storeId);

    //根据成品id从预测结果表中获取近期销量信息和预测信息
    ResponseVO getPredictSales(String storeId, String commodityId, String type);

    //获取预测的产品未来3天的退货量
    ResponseVO getPredicrtReturn(String storeId, String commodityId);

    //获取商品关联信息
    ResponseVO getProductRelationInf(String product_id1, String product_id2);

    //获取门店的预测总销量与实际总销量
    ResponseVO getAllPredictSales(List<String> storeId, String type);

    //获取未来7日的总的预测退货量
    ResponseVO getAllPredictReturn(List<String> storeId);

    //获取所有门店的总览信息
    ResponseVO getAllShopInfo(List<String> storeId);

    //导入数据
    ResponseVO importData(ImportDataVO importDataVO);

}
