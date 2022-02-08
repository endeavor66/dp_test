package com.example.dp.ProviderApi;

import com.example.dp.pojo.ReturnC;
import com.example.dp.pojo.ReturnDP;
import com.example.dp.pojo.SalesDP;
import com.example.dp.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "dp-test-provider-Forecast", url ="172.19.241.99:8002")
public interface ForecastProviderApi {

    @GetMapping("/DailyRenew")
    public List<Integer> DailyRenew(List<SalesDP> newdata);

    @GetMapping("/ReturnRenew")
    List<Integer> ReturnRenew(List<ReturnDP> newdata);

    @PostMapping("/SalesModelInit")
    public ResponseVO SalesModelInit(List<SalesDP> newdata);

    @PostMapping("/ReturnModelInit")
    public ResponseVO ReturnModelInit(List<ReturnDP> newdata);
}



