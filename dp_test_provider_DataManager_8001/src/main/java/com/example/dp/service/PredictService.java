package com.example.dp.service;


import com.example.dp.pojo.*;
import com.example.dp.vo.ResponseVO;

import java.util.Date;
import java.util.List;

public interface PredictService {
    //传入的是predict_totalsales_dp中的id
    ResponseVO getSalesNum(List<String> id);

    //将预测结果放入预测结果表predict_product_dp中
    ResponseVO insertPredictResult_product(List<PredictProduct_whithoutID> newdata);

    //将预测结果放入预测结果表predict_totalsales_dp中
    ResponseVO insertPredictTotalSales_totalsales(List<PredictTotalSales_whithoutID> newdata);

    //将预测结果放入预测结果表predict_return_dp中
    ResponseVO insertPredictReturn_product(List<PredictReturn_whithoutID> newdata);


    //predict_product_dp单个商品的销售预测计算，每次传进来的sales记录只能是同一家ERP，同一个产品id的两条连续日期记录，返回下一个周期的预测值，预测值包装在类中
    ResponseVO DailyRenew(List<SalesDP> newdata) throws InterruptedException;

    //predict_return_dp单个商品的退货预测计算，每次传进来的sales记录只能是同一家ERP，同一个产品id的两条连续日期记录，返回下一个周期的预测值，预测值包装在类中
    ResponseVO ReturnRenew(List<ReturnDP> newdata)throws InterruptedException;

    //传入sales记录进行销售预测模型的更新
    ResponseVO SalesModelInit(List<SalesDP> newdata)throws InterruptedException;

    //传入return记录进行销售预测模型的更新
    ResponseVO ReturnModelInit(List<ReturnDP> newdata)throws InterruptedException;
}
