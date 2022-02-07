package com.example.dp.controller;

import com.example.dp.service.ClientService;
import com.example.dp.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "门店端微服务测试", tags = "门店端微服务测试")
@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    /**
     *获取门店(id)近几天(days)销量预测数据
     * @param id
     * @return
     */
    @ApiOperation(value = "获取门店(id)近几天(days)销量预测数据", notes = "getStoreSalePredict")
    @GetMapping("/getStoreSalePredict")
    public Object getStoreSalePredict(@RequestParam String id,@RequestParam int days){return clientService.getStoreSalePredict(id,days);}

    /**
     * 获取门店(id)销量预测数据(没用到)
     * @param id
     * @return
     */
    @ApiOperation(value = "获取门店销量预测数据",notes="getStoreSaleNumPredict")
    @GetMapping("/getStoreSaleNumPredict")
    public ResponseVO getStoreSaleNumPredict(String id){return clientService.getStoreSaleNumPredict(id);}

    /**
     * 获取特定商品的销量预测数据(没用到)
     * @param id
     * @return
     */
    @ApiOperation(value = "获取特定商品的销量预测数据",notes = "getGoodsSaleNumPredict")
    @GetMapping("/getGoodsSaleNumPredict")
    public ResponseVO getGoodsSaleNumPredict(String id){
        return clientService.getGoodsSaleNumPredict(id);
    }
}
