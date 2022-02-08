package com.example.dp.mapper;

import com.example.dp.pojo.RecommendResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecommendMapper {
    //获取所有推荐结果表中的id
    List<String> selectAllRecommendResultId();

    //把结果放进推荐结果表中
    void insertRecommendResult(RecommendResult recommendResult);

    //根据成品id1和成品id2从recommand_relation中获取关联度
    String getRelation_pra(String product_id1, String product_id2);
}
