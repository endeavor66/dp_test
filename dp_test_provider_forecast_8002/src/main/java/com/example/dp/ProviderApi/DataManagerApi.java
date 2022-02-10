package com.example.dp.ProviderApi;

import com.example.dp.po.Shop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@FeignClient(name = "dp-test-provider-datamanager", url ="172.19.241.210:8001")
@FeignClient(name = "dp-test-provider-datamanager", url ="172.19.241.99:8001")
public interface DataManagerApi {

    @GetMapping("/shop/list")
    public List<Shop> queryAll();


}
