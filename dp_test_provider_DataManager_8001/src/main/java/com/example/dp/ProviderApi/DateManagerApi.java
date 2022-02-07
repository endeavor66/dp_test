package com.example.dp.ProviderApi;

import com.example.dp.pojo.RecommendRelation;
import com.example.dp.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface DateManagerApi {


    @FeignClient(name = "dp-test-provider-datamanager")
    public interface DataManagerApi {

        @PostMapping("/localhost8080/login/{name}/{code}")
        public boolean loginResult(@PathVariable("name") String userName, @PathVariable("code") String passWord);

        @GetMapping("//loginInfo/{name}")
        public List<ShopVO> loginInfo(@PathVariable("name") String userName);

        @GetMapping("/getShopInfo/{id}")
        public List<StoreVO> getStoreInfById(@PathVariable("id") String id);

        @GetMapping("/getRecommendProduct/{shop_id}")
        public List<RecommendProductInfVO> getRecommendProductInfByStoreId(@PathVariable("shop_id") String storeId);

        @GetMapping("/getPredictSales/{shop_id}/{commodity_id}/{type}")
        public List<GetPredictSalesVO> getPredictSales(@PathVariable("shop_id") String storeId, @PathVariable("commodity_id") String commodityId, @PathVariable("type") String type);

        @GetMapping("/getPredicrtReturn/{shop_id}/{commodity_id}")
        public List<PredictReturnVO> getPredicrtReturn(@PathVariable("shop_id") String storeId, @PathVariable("commodity_id") String commodityId);

        @GetMapping("/getPredicrtReturn/{product_id1}/{product_id2}")
        public RecommendRelation getProductRelationInf(@PathVariable("product_id1") String product_id1, @PathVariable("product_id2") String product_id2);
    }

}
