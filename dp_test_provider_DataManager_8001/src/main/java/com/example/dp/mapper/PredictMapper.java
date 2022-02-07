package com.example.dp.mapper;

import com.example.dp.pojo.PredictProduct;
import com.example.dp.pojo.PredictReturn;
import com.example.dp.pojo.PredictTotalSales;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PredictMapper {
    //根据predict_totalsales_dp中的id获得对象
    PredictTotalSales selectProductInfById(String id);

    //将结果放入表中
    void insertPredictResult(PredictProduct predictProduct);

    List<String> selectpredictTotalSalesNewestId();

    List<String> selectpredictReturnNewestId();


    List<String> selectpredictResult_productNewestId();

    void insertPredictReturn(PredictReturn temp);

    void insertPredictTotalSales(PredictTotalSales temp);
}
