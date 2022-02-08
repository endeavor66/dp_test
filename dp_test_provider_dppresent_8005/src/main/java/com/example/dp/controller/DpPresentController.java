package com.example.dp.controller;

import com.example.dp.service.DpPresentService;
import com.example.dp.vo.ResponseVO;
import com.example.dp.vo.form.LoginForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "数据中台微服务测试", tags = "数据中台微服务测试")
@RestController
public class DpPresentController {
    @Autowired
    private DpPresentService dpPresentService;

    @ApiOperation(value = "测试", notes = "test")
    @GetMapping("/test")
    public Object remotetest(){
        System.out.println("get here");
        return "well";
    }

    /**
     * 登录
     * @return
     */
    @ApiOperation(value = "登录", notes = "login")
    @GetMapping("/login")
    public Object login(@RequestParam String id,@RequestParam String code){
        return dpPresentService.login(id,code);
    }

    @ApiOperation(value = "登录成功后的信息获取", notes = "loginData")
    @GetMapping("/loginData")
    public Object loginData(@RequestParam String managerId){return dpPresentService.loginData(managerId);}

    /**
     * 获取门店信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取门店信息", notes = "getShopInfo")
    @GetMapping("/getShopInfo")
    public Object getShopInfo(@RequestParam String id){
        return dpPresentService.getShopInfo(id);
    }

    /**
     * 获取推荐的商品详情清单
     * @param storeId
     * @return
     */
    @ApiOperation(value = "获取推荐的商品详情清单", notes = "getRecommendProduct")
    @GetMapping("/getRecommendProduct")
    public Object getRecommendProduct(@RequestParam String storeId){
        return dpPresentService.getRecommendProduct(storeId);
    }

    /**
     * 根据商品id获取实际和预测销量信息
     * @param storeId
     * @param commodityId
     * @param type
     * @return
     */
    @ApiOperation(value = "根据商品id获取实际和预测销量信息", notes = "getPredictSales")
    @GetMapping("/getPredictSales")
    public Object getPredictSales(@RequestParam String storeId,@RequestParam String commodityId,@RequestParam String type){
        return dpPresentService.getPredictSales(storeId,commodityId,type);
    }

    @ApiOperation(value = "获取预测的商品退货率", notes = "getPredictReturn")
    @GetMapping("/getPredictReturn")
    public Object getPredictReturn(@RequestParam String storeId,@RequestParam String commodityId){
        return dpPresentService.getPredictReturn(storeId,commodityId);
    }

    @ApiOperation(value = "获取门店的预测总销量与实际总销量", notes = "getAllPredictSales")
    @GetMapping("/getAllPredictSales")
    public Object getAllPredictSales(@RequestParam List<String> storeId, @RequestParam String type){
        return dpPresentService.getAllPredictSales(storeId,type);
    }

    @ApiOperation(value = "获取未来7日总的预测退货率", notes = "getAllPredictReturn")
    @GetMapping("/getAllPredictReturn")
    public Object getAllPredictReturn(@RequestParam List<String> storeId){
        return dpPresentService.getAllPredictReturn(storeId);
    }

    @ApiOperation(value = "获取所有门店的总览信息", notes = "getAllShopInfo")
    @GetMapping("/getAllShopInfo")
    public Object getAllShopInfo(@RequestParam List<String> storeId){
        return dpPresentService.getAllShopInfo(storeId);
    }

    @ApiOperation(value ="导入数据", notes = "importData")
    @PostMapping("/importData")
    public Object importData(@RequestParam String fileContent,@RequestParam String type,@RequestParam String shopId){
        return dpPresentService.importData(fileContent,type,shopId);
    }
}
