package com.example.dp.ProviderApi;


import com.example.dp.pojo.Shop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "dp-test-provider-datamanager", url ="172.19.241.210:8001")
public interface DataManagerApi {

    @PostMapping("/shop/add")
    public boolean addShop(Shop shop);

    @GetMapping("/shop/get/{id}")
    public Shop queryById(@PathVariable("id") String id);

    @GetMapping("/shop/list")
    public List<Shop> queryAll();
}
