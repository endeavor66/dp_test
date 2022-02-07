package com.example.dp.serviceImpl;

import com.example.dp.mapper.ShopDao;
import com.example.dp.pojo.Shop;
import com.example.dp.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    public boolean addShop(Shop shop){
        return shopDao.addShop(shop);
    }

    @Override
    public Shop queryById(String id){
        return shopDao.queryById(id);
    }

    @Override
    public List<Shop> queryAll(){
        return shopDao.queryAll();
    }

}
