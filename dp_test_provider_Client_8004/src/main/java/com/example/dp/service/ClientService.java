package com.example.dp.service;

import com.example.dp.vo.ResponseVO;

public interface ClientService {

    Object getStoreSalePredict(String id,int days);

    ResponseVO getStoreSaleNumPredict(String id);

    ResponseVO getGoodsSaleNumPredict(String id);
}
