package com.example.dp.ProviderApi;


import com.example.dp.po.Shop;
import com.example.dp.vo.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@FeignClient(name = "dp-test-provider-datamanager", url ="172.19.241.210:8001")
@FeignClient(name = "dp-test-provider-datamanager", url ="172.17.0.5:8001")
public interface DataManagerApi {

    @GetMapping("/shop/list")
    public List<Shop> queryAll();

    //根据用户名，密码返回是否正确
    @RequestMapping("/provider_DataManager/login")
    ResponseVO loginResult(@RequestParam("userName")String userName, @RequestParam("passWord")String passWord);

    //根据用户名获取该用户拥有的门店列表
    @RequestMapping("/provider_DataManager/loginInfo")
    ResponseVO loginInfo(String userName);

}
