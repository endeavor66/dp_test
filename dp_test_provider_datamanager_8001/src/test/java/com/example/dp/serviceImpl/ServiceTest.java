package com.example.dp.serviceImpl;

import com.example.dp.mapper.DatePlatFormMapper;
import com.example.dp.vo.ManagerVO;
import com.example.dp.vo.ResponseVO;
import com.example.dp.vo.StoreVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Autowired
    DatePlatFormServiceImpl datePlatFormService;

    @Autowired
    private DatePlatFormMapper datePlatFormMapper;


    //DatePlatFormService
    @Test
    public void loginResultTest(){
        ResponseVO responseVO=datePlatFormService.loginResult("jack","123456");
        System.out.println(responseVO.getData());
    }

    @Test
    public void loginInfoTest(){
        ResponseVO responseVO=datePlatFormService.loginInfo("benson");
        System.out.println(responseVO.getData());
    }

    @Test
    public void getStoreInfByIdTest(){
        ResponseVO responseVO=datePlatFormService.getStoreInfById("000001");
        List<StoreVO> data = (List<StoreVO>) responseVO.getData();
        System.out.println(data.get(0).getShop_name());
    }

    @Test
    public void getPredictSalesTest(){
        ResponseVO responseVO=datePlatFormService.getPredictSales("0001","0001","2");
        System.out.println(responseVO.getData());
    }

    @Test
    public void getPredicrtReturnTest(){
        ResponseVO responseVO=datePlatFormService.getPredicrtReturn("0001","0002");
        System.out.println(responseVO.getData());
    }

    @Test
    public void getProductRelationInfTest(){
        ResponseVO responseVO=datePlatFormService.getProductRelationInf("000001","0002");
        System.out.println(responseVO.getData());
    }

    @Test
    public void getAllPredictSalesTest(){
        List<String> strings=new ArrayList<>();
        ResponseVO responseVO=datePlatFormService.getAllPredictSales(strings,"0002");
        System.out.println(responseVO.getData());
    }

    @Test
    public void getAllPredictReturnTest(){
        List<String> strings=new ArrayList<>();
        ResponseVO responseVO=datePlatFormService.getAllPredictReturn(strings);
        System.out.println(responseVO.getData());
    }

    //PredictService

}
