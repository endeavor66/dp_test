package com.example.dp.service;

import com.example.dp.po.Sales;
import com.example.dp.po.ReturnDP;
import com.example.dp.po.SalesDP;
import com.example.dp.vo.ResponseVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ForecastService {

    List<Integer> DailyRenew(List<SalesDP> newdata);

    ResponseVO ReturnRenew(List<ReturnDP> newdata);

    ResponseVO SalesModelInit(List<SalesDP> newdata);

    ResponseVO ReturnModelInit(List<ReturnDP> newdata);

    ResponseVO test();

    ResponseVO test2();
}
