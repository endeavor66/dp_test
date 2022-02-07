package com.example.dp.service;

import com.example.dp.vo.ResponseVO;

import java.util.Date;
import java.util.List;

public interface RecommendService {
    //获取推荐算法所需要的数据，根据门店id,商店id，日期获取对应门店的对应产品的销售量
    ResponseVO getSalesNum(List<String> id);
    //将推荐结果存入推荐结果表中
    ResponseVO insertRecommendResult(String shop_id, Date recommand_date, String product_id, double weight, String reason);
    //获取商品关联信息
    ResponseVO getProductRelationInf(String product_id1, String product_id2);
}
