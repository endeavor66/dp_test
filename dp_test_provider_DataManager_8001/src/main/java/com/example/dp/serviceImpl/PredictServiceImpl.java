package com.example.dp.serviceImpl;

import com.example.dp.ProviderApi.ForecastProviderApi;
import com.example.dp.mapper.PredictMapper;
import com.example.dp.pojo.*;
import com.example.dp.service.PredictService;
import com.example.dp.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PredictServiceImpl implements PredictService {

    @Autowired
    private PredictMapper predictMapper;

    @Autowired
    private ForecastProviderApi forecastProviderApi;

    @Override
    public ResponseVO getSalesNum(List<String> id) {
        try {
            List<Double> result = new ArrayList<>();
            //获取表中对象数组
            List<PredictTotalSales> predictTotalSales = new ArrayList<>();
            for (String s : id) {
                predictTotalSales.add(predictMapper.selectProductInfById(s));
                result.add(predictTotalSales.get(Integer.parseInt(s)).getActual());
            }
            return ResponseVO.buildSuccess(result);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO insertPredictResult_product(List<PredictProduct_whithoutID> newdata) {
        try{
            //取出所有的预测表id
            List<String> predictResult_productNewestId = predictMapper.selectpredictResult_productNewestId();
            String id;
            if(predictResult_productNewestId.size()==0)
                id="00000001";
            else {
                id = autoIdAddOne(predictResult_productNewestId.get(0));
            }

            for(PredictProduct_whithoutID predictProduct_whithoutID : newdata){
                PredictProduct temp = new PredictProduct(id,predictProduct_whithoutID.getShop_id(),predictProduct_whithoutID.getForecast_date(),predictProduct_whithoutID.getProduct_id(),predictProduct_whithoutID.getForecast_result());

                predictMapper.insertPredictResult(temp);

            }


            return ResponseVO.buildSuccess("写入数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO insertPredictTotalSales_totalsales(List<PredictTotalSales_whithoutID> newdata) {
        try{
            //取出所有的预测表id
            List<String> predictTotalSalesNewestId = predictMapper.selectpredictTotalSalesNewestId();
            String id;
            if(predictTotalSalesNewestId.size()==0)
                id="00000001";
            else {
                id = autoIdAddOne(predictTotalSalesNewestId.get(0));
            }


            for(PredictTotalSales_whithoutID predictTotalSales_whithoutID : newdata){
                PredictTotalSales temp = new PredictTotalSales(id,predictTotalSales_whithoutID.getShop_id(),predictTotalSales_whithoutID.getDate(),predictTotalSales_whithoutID.getResult(),predictTotalSales_whithoutID.getActual());

                predictMapper.insertPredictTotalSales(temp);

            }


            return ResponseVO.buildSuccess("写入数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO insertPredictReturn_product(List<PredictReturn_whithoutID> newdata) {
        try{
            //取出所有的预测表id
            List<String> predictTotalSalesNewestId = predictMapper.selectpredictReturnNewestId();
            String id;
            if(predictTotalSalesNewestId.size()==0)
                id="00000001";
            else {
                id = autoIdAddOne(predictTotalSalesNewestId.get(0));
            }

            for(PredictReturn_whithoutID predictReturn_whithoutID : newdata){
                PredictReturn temp = new PredictReturn(id,predictReturn_whithoutID.getShop_id(),predictReturn_whithoutID.getProduct_id(),predictReturn_whithoutID.getDate(),predictReturn_whithoutID.getReturn_num());

                predictMapper.insertPredictReturn(temp);

            }


            return ResponseVO.buildSuccess("写入数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Async("taskExecutor")
    @Override
    public ResponseVO DailyRenew(List<SalesDP> newdata) throws InterruptedException{
        try{


            List<Integer> RenewData = forecastProviderApi.DailyRenew(newdata);
            //因为目前只是单步预测，所以可以只对结果数组的第一个元素进行处理，后需要多步预测的话可以改成遍历处理
            Integer data = RenewData.get(0);
            SalesDP sales = newdata.get(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
            //Date date = simpleDateFormat.parse(sales.getSales_date());
            Date date = sales.getSales_date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE,1);
            date = calendar.getTime();
            //System.out.println(date);   //Mon Sep 02 00:00:00 CST 2019
            //System.out.println(simpleDateFormat.format(date));  //2019-09-02
            PredictProduct_whithoutID predictProduct_whithoutID = new PredictProduct_whithoutID(sales.getShop_id(),date,sales.getProduct_id(),data);
            List<PredictProduct_whithoutID> predictProduct_whithoutIDS = new ArrayList<>();
            predictProduct_whithoutIDS.add(predictProduct_whithoutID);
            insertPredictResult_product(predictProduct_whithoutIDS);
            return ResponseVO.buildSuccess("写入数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Async("taskExecutor")
    @Override
    public ResponseVO ReturnRenew(List<ReturnDP> newdata) throws InterruptedException{
        try{
            List<Integer> RenewData = forecastProviderApi.ReturnRenew(newdata);
            //因为目前只是单步预测，所以可以只对结果数组的第一个元素进行处理，后需要多步预测的话可以改成遍历处理
            Integer data = RenewData.get(0);
            ReturnDP returnDP = newdata.get(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
            //Date date = simpleDateFormat.parse(sales.getDate());
            Date date = returnDP.getReturn_date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE,1);
            date = calendar.getTime();
            //System.out.println(date);   //Mon Sep 02 00:00:00 CST 2019
            //System.out.println(simpleDateFormat.format(date));  //2019-09-02
            PredictReturn_whithoutID predictReturn_whithoutID = new PredictReturn_whithoutID(returnDP.getShop_id(),returnDP.getProduct_id(),simpleDateFormat.format(date),data);
            List<PredictReturn_whithoutID> predictReturn_whithoutIDs = new ArrayList<>();
            predictReturn_whithoutIDs.add(predictReturn_whithoutID);
            insertPredictReturn_product(predictReturn_whithoutIDs);


            return ResponseVO.buildSuccess("写入数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }


    @Async("taskExecutor")
    @Override
    public ResponseVO SalesModelInit(List<SalesDP> newdata) throws InterruptedException{
        try{
            forecastProviderApi.SalesModelInit(newdata);

            return ResponseVO.buildSuccess("写入数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Async("taskExecutor")
    @Override
    public ResponseVO ReturnModelInit(List<ReturnDP> newdata) throws InterruptedException{
        try{
            forecastProviderApi.ReturnModelInit(newdata);

            return ResponseVO.buildSuccess("写入数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }
    /**
     * 自动生成ID的方法
     * @param oldId
     * @return
     */
    private String autoIdAddOne(String oldId){
        int num=Integer.parseInt(oldId);
        num=num+1;
        String numStr=String.valueOf(num);
        String zeroIn="";
        for (int i=0;i<8-numStr.length();i++){
            zeroIn=zeroIn+"0";
        }
        numStr=zeroIn+numStr;
        return numStr;
    }




}
