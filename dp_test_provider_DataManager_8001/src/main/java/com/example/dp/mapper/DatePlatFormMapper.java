package com.example.dp.mapper;

import com.example.dp.pojo.*;
import com.example.dp.vo.ManagerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface DatePlatFormMapper {
    //根据userName获得登录对象情况
    ManagerVO selectManagerInfoByName(String userName);

    //根据usename从manager_dp中获得id
    String selectIdByUserName(String userName);

    //根据username从manager_dp中获得shop_id
    List<String> selectShopIdByUserName(String userName);

    //根据shop_id从shop_dp中获得name
    String selectNameByShopId(String shopId);

    //根据时间，店铺id，商品id获得那一天的退货数量
    int selectNumByTimeAndStoreIdAndcommodityId(@Param("date")Date date, @Param("storeId")String storeId, @Param("commodityId")String commodityId);

    //根据商品id，门店id，日期去sales_dp中获得过去实际销量
    Double selectActualNumFromSales_dp(@Param("storeId")String storeId,@Param("commodityId") String commodityId,@Param("date") Date date);

    //根据productId和storeId和forecastdate获得预测数量
    Double selectPredictNumFromPredict_product_dp(@Param("storeId")String storeId,@Param("commodityId") String commodityId,@Param("date") Date date);

    //跟门店id,日期获得当日门店的实际销量
    int selectSalesNumByStoreIdAndDate(String storeId, Date date);

    //根据门店id,日期从predict_totalsales_dp中获取预测的总销量
    Double selectPredictTotal(String storeId, Date date);

    //根据门店id获取全部的门店对象
    Store selectStoreByStoreId(String storeId);

    //根据product_id获得name
    String selectNameByProductId(String productId);

    //根据产品id1,产品id2获得推荐产品关联表的全部信息
    RecommendRelation selectRecommendRelationById(@Param("product_id1") String product_id1,@Param("product_id2") String product_id2);

    //根据门店id，日期从eturn_application_dp中获取当天该门店的退货量
    Double selectReturnNumByStoreIdAndDate(String storeId, Date date);

    //----------------------------------------------------------------------

    //根据门店id获取推荐商品id的List
    List<String> getRecommendProductListByShopId(String shopId);

    //根据商品id获取商品名称
    String getRecommendProductNameByProductId(String productId);

    //根据商品id获取推荐理由
    String getRecommendReasonByProductId(String productId);

    //根据商品id获取原料List
    List<String> getMaterialListByProductId(String productId);

    //根据原料id获取原料名称
    String getMaterialNameById(String id);

    //根据原料id获取供应商id
    List<String> getSupplierIdByMaterialId(String id);

    //根据供应商id获取Supplier对象
    SupplierByD getSupplierById(String id);

    //根据供应商id和原料id获取价格
    double getPriceBySupplierIdAndMaterialId(String sId,String mId);

    //根据sales的id获取num
    int getNumBySalesId(String id);

    //插入SalesDP到数据库
    int insertSalesDP(SalesDP salesDP);

    //插入ReturnDP到数据库
    int insertReturnDP(ReturnDP returnDP);
}
