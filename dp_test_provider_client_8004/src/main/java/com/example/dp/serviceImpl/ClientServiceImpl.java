package com.example.dp.serviceImpl;

import com.example.dp.service.ClientService;
import com.example.dp.vo.ResponseVO;
import com.example.dp.vo.SalesPredictListVO;
import com.example.dp.vo.SalesPredictVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {


    @Override
    public Object getStoreSalePredict(String id,int days){
        try{
            List<SalesPredictVO> salesPredictVOS=new ArrayList<>();
            salesPredictVOS.add(new SalesPredictVO("2020-03-03",20.0,31.1));
            SalesPredictListVO salesPredictListVO=new SalesPredictListVO(salesPredictVOS);
            return salesPredictListVO;
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO getStoreSaleNumPredict(String id){
        try{
            return ResponseVO.buildSuccess("test");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO getGoodsSaleNumPredict(String id){
        try{
            return ResponseVO.buildSuccess("test");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }
}
