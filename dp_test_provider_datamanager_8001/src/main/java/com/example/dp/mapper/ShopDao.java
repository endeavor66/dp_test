package com.example.dp.mapper;

import com.example.dp.pojo.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopDao {

    public boolean addShop(Shop shop);

    public Shop queryById(String id);

    public List<Shop> queryAll();
}
