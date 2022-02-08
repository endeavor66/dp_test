package com.example.dp.serviceImpl;

import com.example.dp.mapper.DatePlatFormMapper;
import com.example.dp.pojo.*;
import com.example.dp.service.DatePlatFormService;
import com.example.dp.service.PredictService;
import com.example.dp.service.RecommendService;
import com.example.dp.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DatePlatFormServiceImpl implements DatePlatFormService {
    @Autowired
    private DatePlatFormMapper datePlatFormMapper;

    @Autowired
    private PredictService predictService;



    //此方法的userName实际是用户id
    @Override
    public ResponseVO loginResult(String userName, String passWord){
        try {


            ManagerVO result = datePlatFormMapper.selectManagerInfoByName(userName);
            System.out.println(userName);
            System.out.println(passWord);

            if(result.getCode().equals(passWord))
                return ResponseVO.buildSuccess(result);
            else
                return ResponseVO.buildFailure("passWord not correct");
        }
        catch(Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("ID not exits");
        }
    }

    //此方法的userName实际是用户id
    @Override
    public ResponseVO loginInfo(String userName){
        try {
            ManagerVO userInfo = datePlatFormMapper.selectManagerInfoByName(userName);
            String id  = userName;
            List<String> shopIdList = datePlatFormMapper.selectShopIdByUserName(userInfo.getName());
            List<ShopInfNeed> shops = new ArrayList<>();
            for(int i = 0;i<shopIdList.size();i++){
                String shop_id = shopIdList.get(i);
                String name = datePlatFormMapper.selectNameByShopId(shop_id);
                shops.add(new ShopInfNeed(shop_id,name));
            }
            ShopVO result=new ShopVO(id,userInfo.getName(),shops);
            return ResponseVO.buildSuccess(result);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO getStoreInfById(String id){
        try {
            Store store=datePlatFormMapper.selectStoreByStoreId(id);
            ShopVObyD shopVObyD=new ShopVObyD(store.getId(),store.getShop_name(),store.getShop_location(),store.getShop_type(),store.getFound_date(),store.getShop_synopsis(),store.getShop_director());
            return ResponseVO.buildSuccess(shopVObyD);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO getRecommendProductInfByStoreId(String storeId){
        try{
            //返回值
            List<RecommendProductInfVO> result=new ArrayList<>();
            //根据门店id获取推荐商品id的List
            List<String> recommendProductIdList=datePlatFormMapper.getRecommendProductListByShopId(storeId);
            for(String s:recommendProductIdList){
                RecommendProductInfVO r=new RecommendProductInfVO();
                r.setId(s);
                r.setName(datePlatFormMapper.getRecommendProductNameByProductId(s));
                r.setReason(datePlatFormMapper.getRecommendReasonByProductId(s));
                //根据产品id获取对应原料id的List
                List<String> materialsId=datePlatFormMapper.getMaterialListByProductId(s);
                List<MaterialSuppliersVO> materials=new ArrayList<>();
                for(String m:materialsId){
                    MaterialSuppliersVO material=new MaterialSuppliersVO();
                    material.setId(m);
                    material.setName(datePlatFormMapper.getMaterialNameById(m));
                    //根据原料id获取供应商id的List
                    List<String> supplierIdList=datePlatFormMapper.getSupplierIdByMaterialId(m);
                    List<Supplier> suppliers=new ArrayList<>();
                    for(String p:supplierIdList){
                        double price=datePlatFormMapper.getPriceBySupplierIdAndMaterialId(p,m);
                        SupplierByD supplierByD=datePlatFormMapper.getSupplierById(p);
                        suppliers.add(new Supplier(supplierByD,price));
                    }
                    material.setAllSuppliers(suppliers);
                    materials.add(material);
                }
                r.setMaterials(materials);
                result.add(r);
            }
            RecommendProductListVO recommendProductListVO=new RecommendProductListVO(result);
            return ResponseVO.buildSuccess(recommendProductListVO);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO getPredictSales(String storeId, String commodityId, String type){
        //TODO：这边拿不到数据库的数据
        try{
            List<Double> actual = new ArrayList<>();
            List<String> times = new ArrayList<>();
            List<Double> predict =new ArrayList<>();
            List<GetPredictSalesVO> returnResult =new ArrayList<>();
            int dayCount =0;

            System.out.println(type);

            if(type.equals("day")) {
                dayCount = 3;
            }
            else if(type.equals("week")) {
                dayCount = 7;
            }
            else if(type.equals("month")) {
                dayCount = 30;
            }

            for(int i = dayCount;i>=1;i--){
                Calendar calendar1 = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                calendar1.add(Calendar.DATE, 1-i);
                String days_ago = sdf.format(calendar1.getTime());
                System.out.println(dayCount);

                System.out.println(days_ago);
                Date date = sdf.parse(days_ago);
                times.add(days_ago);
                //根据商品id，门店id，日期去predict_product_dp中获得predict
                Double predictResult = datePlatFormMapper.selectPredictNumFromPredict_product_dp(storeId,commodityId,date);
                //根据商品id，门店id，日期去sales_dp中获得实际销量
                Double actualResultAgo = datePlatFormMapper.selectActualNumFromSales_dp(storeId,commodityId,date);
                predict.add(predictResult);
                actual.add(actualResultAgo);
            }

            for(int j =1;j<=dayCount;j++){
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                calendar.add(Calendar.DATE, j);
                String days_after = sdf1.format(calendar.getTime());
                Date date1 = sdf1.parse(days_after);
                times.add(days_after);
                //获得未来某一天的预测量
                Double predictResultAfter = datePlatFormMapper.selectPredictNumFromPredict_product_dp(storeId,commodityId,date1);
                predict.add(predictResultAfter);
            }
                /*
                Calendar calendar1 = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    calendar1.add(Calendar.DATE, -2 );
                    String twoDays_ago = sdf.format(calendar1.getTime());

                    calendar.add(Calendar.DATE, 3);
                    String threeDays_after = sdf1.format(calendar.getTime());




                //i为1的情况下，时间顺序有问题
                for(int i = 3;i>=1;i--){
                    Calendar calendar1 = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    calendar1.add(Calendar.DATE, 1-i);
                    String days_ago = sdf.format(calendar1.getTime());
                    Date date = sdf.parse(days_ago);
                    times.add(days_ago);
                    //根据商品id，门店id，日期去predict_product_dp中获得predict
                    Double predictResult = datePlatFormMapper.selectPredictNumFromPredict_product_dp(storeId,commodityId,date);
                    //根据商品id，门店id，日期去sales_dp中获得实际销量
                    Double actualResultAgo = datePlatFormMapper.selectActualNumFromSales_dp(storeId,commodityId,date);
                    predict.add(predictResult);
                    actual.add(actualResultAgo);
                }

                for(int j =1;j<=3;j++){
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                    calendar.add(Calendar.DATE, j);
                    String days_after = sdf1.format(calendar.getTime());
                    Date date1 = sdf1.parse(days_after);
                    times.add(days_after);
                    //获得未来某一天的预测量
                    Double predictResultAfter = datePlatFormMapper.selectPredictNumFromPredict_product_dp(storeId,commodityId,date1);
                    predict.add(predictResultAfter);
                }

                returnResult.add(new GetPredictSalesVO(times,actual,predict));
            }

            else if(type.equals("week")){
                for(int i = 7;i>=1;i--){
                    Calendar calendar1 = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    calendar1.add(Calendar.DATE, 1-i);
                    String days_ago = sdf.format(calendar1.getTime());
                    Date date = sdf.parse(days_ago);
                    times.add(days_ago);
                    //根据商品id，门店id，日期去predict_totalsales_dp中获得predict
                    Double predictResult = datePlatFormMapper.selectPredictNumFromPredict_product_dp(storeId,commodityId,date);
                    Double actualResultAgo = datePlatFormMapper.selectActualNumFromSales_dp(storeId,commodityId,date);
                    predict.add(predictResult);
                    actual.add(actualResultAgo);
                }

                for(int j =1;j<=7;j++){
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                    calendar.add(Calendar.DATE, j);
                    String days_after = sdf1.format(calendar.getTime());
                    Date date1 = sdf1.parse(days_after);
                    times.add(days_after);
                    //获得未来某一天的预测量
                    Double predictResultAfter = datePlatFormMapper.selectPredictNumFromPredict_product_dp(storeId,commodityId,date1);
                    predict.add(predictResultAfter);
                }
                returnResult.add(new GetPredictSalesVO(times,actual,predict));
            }

            if(type.equals("month")){
                for(int i = 30;i>=1;i--){
                    Calendar calendar1 = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    calendar1.add(Calendar.DATE, 1-i);
                    String days_ago = sdf.format(calendar1.getTime());
                    Date date = sdf.parse(days_ago);
                    times.add(days_ago);
                    //根据商品id，门店id，日期去predict_totalsales_dp中获得predict
                    Double predictResult = datePlatFormMapper.selectPredictNumFromPredict_product_dp(storeId,commodityId,date);
                    Double actualResultAgo = datePlatFormMapper.selectActualNumFromSales_dp(storeId,commodityId,date);
                    predict.add(predictResult);
                    actual.add(actualResultAgo);
                }

                for(int j =1;j<=30;j++){
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                    calendar.add(Calendar.DATE, j);
                    String days_after = sdf1.format(calendar.getTime());
                    Date date1 = sdf1.parse(days_after);
                    times.add(days_after);
                    //获得未来某一天的预测量
                    Double predictResultAfter = datePlatFormMapper.selectPredictNumFromPredict_product_dp(storeId,commodityId,date1);
                    predict.add(predictResultAfter);
                }
                */
                returnResult.add(new GetPredictSalesVO(times,actual,predict));
            return ResponseVO.buildSuccess(returnResult.get(0));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO getPredicrtReturn(String storeId, String commodityId){
        //TODO：这边拿不到数据库的数据
        try{
            //根据商品id从productList_dp中获得对应商品的名字
            String name = datePlatFormMapper.selectNameByProductId(commodityId);
            List<String> times = new ArrayList<>();//注意这边声明的不是date型
            List<Integer> result = new ArrayList<>();
            List<PredictReturnVO> returnResult = new ArrayList<>();


            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            for(int i =1;i<=3;i++) {
                calendar.add(Calendar.DATE, i);
                String days_after = sdf1.format(calendar.getTime());
                times.add(days_after);
            }

            /*Calendar calendar1 = Calendar.getInstance();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            calendar1.add(Calendar.DATE, 1);
            String one_days_after = sdf1.format(calendar1.getTime());

            Calendar calendar2 = Calendar.getInstance();
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
            calendar2.add(Calendar.DATE, 2);
            String two_days_after = sdf2.format(calendar2.getTime());

            Calendar calendar3 = Calendar.getInstance();
            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
            calendar3.add(Calendar.DATE, 3);
            String three_days_after = sdf3.format(calendar3.getTime());

            List<String> timeList = Arrays.asList( one_days_after, two_days_after, three_days_after);
            times.addAll(timeList);

*/
            for(int i =0;i<times.size();i++){
                Date date = sdf1.parse(times.get(i));
                //根据时间，店铺id，商品id获得那一天的退货数
                int num = datePlatFormMapper.selectNumByTimeAndStoreIdAndcommodityId(date,storeId,commodityId);
                result.add(num);
            }
            returnResult.add(new PredictReturnVO(commodityId,name,times,result));

            return ResponseVO.buildSuccess(returnResult.get(0));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }


    @Override
    public ResponseVO getProductRelationInf(String product_id1, String product_id2){
        try{
            //根据产品id1，产品id2直接获得产品关联表对象
            RecommendRelation result =datePlatFormMapper.selectRecommendRelationById(product_id1,product_id2);
            return ResponseVO.buildSuccess(result);//?这边的对象里面的数据可以得到吗
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO getAllPredictSales(List<String> storeId, String type){
        //TODO： 语法错误
        try{
            /*List<String> times_ago = new ArrayList<>();
            List<String> times_after =new ArrayList<>();
            List<Double> predict_ago = new ArrayList<>();
            List<Double> predict_after =new ArrayList<>();*/
            List<Integer> actual = new ArrayList<>();
            List<String> times = new ArrayList<>();
            List<Double> predict =new ArrayList<>();

            List<String> timesTotal = new ArrayList<>();
            /*List<Integer> actualSale = new ArrayList<>();
             */
            List<Integer> actualTotal = new ArrayList<>();
            List<Double> predictTotal = new ArrayList<>();
            List<GetAllPredictSalesVO> result = new ArrayList<>();

            int daycount =0;
            if(type.equals("day")) {
                daycount = 3;
            }
            else if(type.equals("week")) {
                daycount = 7;
            }
            else if(type.equals("month")) {
                daycount = 30;
            }
            for(int i = 0;i<storeId.size();i++){
                String shopId = storeId.get(i);
                for(int j = daycount;j>=1;j--){
                    Calendar calendar1 = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    calendar1.add(Calendar.DATE, 1-j);
                    String days_ago = sdf.format(calendar1.getTime());
                    //获得具体的日期
                    Date date = sdf.parse(days_ago);
                    //根据门店id,日期从sales_dp获得当日门店的实际销量表
                    int actualSale = datePlatFormMapper.selectSalesNumByStoreIdAndDate(shopId,date);
                    //根据门店id,日期从predict_totalsales_dp中获取预测的总销量
                    Double predict_ago_total =datePlatFormMapper.selectPredictTotal(shopId,date);
                    predict.add(predict_ago_total);
                    /*int saleTotal =0;
                    for(int m = 0;m<actualSale.size();m++){
                        saleTotal =saleTotal+actualSale.get(m);
                    }*/
                    actual.add(actualSale);//获得每个门店对应天数的实际销量
                    times.add(days_ago);
                }


                for(int z =0;z<=daycount;z++) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                    calendar.add(Calendar.DATE, z);
                    String days_after = sdf1.format(calendar.getTime());
                    Date date1 = sdf1.parse(days_after);
                    //根据门店id,日期从predict_totalsales_dp中获取预测的总销量
                    Double predict_after_total = datePlatFormMapper.selectPredictTotal(shopId, date1);
                    predict.add(predict_after_total);
                    times.add(days_after);
                }

                /*times_ago.addAll(times_after);
                times.addAll(times_ago);//获得了times(2.1,2.2,2.3...2.6,2.1,2.2)

                predict_ago.addAll(predict_after);
                predict.addAll(predict_ago);//获得每个每个门店每天的预测销售总量(1,2,3,4,5,6)

                actualTotal.addAll(actual);//表中存的例如(25,32,32
                                                    // ,26,24,25)
                */
            }

            for(int i =0;i<daycount*2;i++){
                String days= times.get(i);
                timesTotal.add(days);
            }


            for(int i =0;i<daycount*2;i++){
                double sum =0.0;
                for(int j =i;j<predict.size();j=j+daycount*2){
                    sum = sum+(double)predict.get(j);
                }
                predictTotal.add(sum);
            }

            for(int i=0;i<daycount*2;i++){
                int totalSum =0;
                for(int j =i;j<actual.size();j=j+daycount){
                    totalSum = totalSum+actual.get(j);//写成i了
                }
                actualTotal.add(totalSum);
            }


            /*else if(type.equals("week")){
                for(int i = 0;i<storeId.size();i++){
                    String shopId = storeId.get(i);
                    for(int j = 1;j<=7;j++){
                        Calendar calendar1 = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        calendar1.add(Calendar.DATE, 1-j);
                        String days_ago = sdf.format(calendar1.getTime());
                        //获得具体的日期
                        Date date = sdf.parse(days_ago);
                        //跟门店id,日期获得当日门店的实际销量表
                        actualSale = datePlatFormMapper.selectSalesNumByStoreIdAndDate(shopId,date);
                        //根据门店id,日期从predict_totalsales_dp中获取预测的总销量
                        Double predict_ago_total =datePlatFormMapper.selectPredictTotal(shopId,date);
                        predict_ago.add(predict_ago_total);
                        int saleTotal =0;
                        for(int m = 0;m<actualSale.size();m++){
                            saleTotal =saleTotal+actualSale.get(m);
                        }
                        actual.add(saleTotal);//获得每个门店对应天数的实际销量
                        times_ago.add(days_ago);
                    }


                    for(int z =1;z<=7;z++) {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                        calendar.add(Calendar.DATE, z);
                        String days_after = sdf1.format(calendar.getTime());
                        Date date1 = sdf1.parse(days_after);
                        //根据门店id,日期从predict_totalsales_dp中获取预测的总销量
                        Double predict_after_total = datePlatFormMapper.selectPredictTotal(shopId, date1);
                        predict_after.add(predict_after_total);
                        times_after.add(days_after);
                    }

                    times_ago.addAll(times_after);
                    times.addAll(times_ago);

                    predict_ago.addAll(predict_after);
                    predict.addAll(predict_ago);

                    actualTotal.addAll(actual);
                }

                for(int i =0;i<14;i++){
                    String days= times.get(i);
                    timesTotal.add(days);
                }


                for(int i =0;i<14;i++){
                    double sum =0.0;
                    for(int j =i;j<predict.size();j=j+14){
                        sum = sum+(double)predict.get(j);
                    }
                    predictTotal.add(sum);
                }

                for(int i=0;i<14;i++){
                    int totalSum =0;
                    for(int j =i;j<actualTotal.size();j=j+7){
                        totalSum = totalSum+actualTotal.get(i);
                    }
                    actualSale.add(totalSum);
                }
            }

            else if(type.equals("month")){
                for(int i = 0;i<storeId.size();i++){
                    String shopId = storeId.get(i);
                    for(int j = 1;j<=30;j++){
                        Calendar calendar1 = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        calendar1.add(Calendar.DATE, 1-j);
                        String days_ago = sdf.format(calendar1.getTime());
                        //获得具体的日期
                        Date date = sdf.parse(days_ago);
                        //跟门店id,日期获得当日门店的实际销量表
                        actualSale = datePlatFormMapper.selectSalesNumByStoreIdAndDate(shopId,date);
                        //根据门店id,日期从predict_totalsales_dp中获取预测的总销量
                        Double predict_ago_total =datePlatFormMapper.selectPredictTotal(shopId,date);
                        predict_ago.add(predict_ago_total);
                        int saleTotal =0;
                        for(int m = 0;m<actualSale.size();m++){
                            saleTotal =saleTotal+actualSale.get(m);
                        }
                        actual.add(saleTotal);//获得每个门店对应天数的实际销量
                        times_ago.add(days_ago);
                    }


                    for(int z =1;z<=30;z++) {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                        calendar.add(Calendar.DATE, z);
                        String days_after = sdf1.format(calendar.getTime());
                        Date date1 = sdf1.parse(days_after);
                        //根据门店id,日期从predict_totalsales_dp中获取预测的总销量
                        Double predict_after_total = datePlatFormMapper.selectPredictTotal(shopId, date1);
                        predict_after.add(predict_after_total);
                        times_after.add(days_after);
                    }

                    times_ago.addAll(times_after);
                    times.addAll(times_ago);

                    predict_ago.addAll(predict_after);
                    predict.addAll(predict_ago);

                    actualTotal.addAll(actual);

                }

                for(int i =0;i<60;i++){
                    String days= times.get(i);
                    timesTotal.add(days);
                }


                for(int i =0;i<60;i++){
                    double sum =0.0;
                    for(int j =i;j<predict.size();j=j+60){
                        sum = sum+(double)predict.get(j);
                    }
                    predictTotal.add(sum);
                }

                for(int i=0;i<60;i++){
                    int totalSum =0;
                    for(int j =i;j<actualTotal.size();j=j+30){
                        totalSum = totalSum+actualTotal.get(i);
                    }
                    actualSale.add(totalSum);
                }
            }*/
            result.add(new GetAllPredictSalesVO(timesTotal,actualTotal,predictTotal));
            return ResponseVO.buildSuccess(result.get(0));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO getAllPredictReturn(List<String> storeId){
        //TODO: 语法错误
            try{
            List<String> times = new ArrayList<>();
            List<Double> returnNums =new ArrayList<>();

            List<Double> result = new ArrayList<>();
            List<GetAllPredictReturnVO> finalPredictReturn = new ArrayList<>();

            for(int i =1;i<=7;i++){
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                calendar.add(Calendar.DATE, i);
                String days_after = sdf1.format(calendar.getTime());
                Date date1 = sdf1.parse(days_after);
                times.add(days_after);
            //先求一日一个门店的退货量
                for(int j =0;j<storeId.size();j++){
                String shopid = storeId.get(j);//获取门店的id
                //根据门店id，日期获取当天该门店的退货量
                double returnNum = datePlatFormMapper.selectReturnNumByStoreIdAndDate(shopid,date1);
                returnNums.add(returnNum);//例如{12,12,11}
                }
                Double dayReturn =0.0;//位置要对
                for(int m =0;m<returnNums.size();m++){
                dayReturn =dayReturn+returnNums.get(m);
                }
                result.add(dayReturn);
            }
            finalPredictReturn.add(new GetAllPredictReturnVO(times,result));
            return ResponseVO.buildSuccess(finalPredictReturn.get(0));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO getAllShopInfo(List<String> storeId){
        //TODO: 未完成
        try{
            List<GetAllShopInfoVO> result = new ArrayList<>();
            for(int i =0;i<storeId.size();i++){

            }


            return ResponseVO.buildSuccess();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO importData(ImportDataVO importDataVO){
        try{
            switch (importDataVO.getType()){
                case "salesData":
                    List<SalesC> salesCS=importDataVO.getSalesCS();
                    //构建SalesDP对象数组
                    List<SalesDP> salesDPS=new ArrayList<>();
                    for(SalesC s:salesCS){
                        SalesDP salesDP=new SalesDP(s.getId(),importDataVO.getShopId(),s.getProduct_id(),s.getSales_date(),s.getNum(),s.getPrice());
                        salesDPS.add(salesDP);
                    }
                    //将SalesDP对象数组写入数据库
                    for(SalesDP s:salesDPS){
                        datePlatFormMapper.insertSalesDP(s);
                    }
                    //调用预测微服务更新预测值
                    predictService.DailyRenew(salesDPS);
                    return ResponseVO.buildSuccess("Success");
                case "returnData":
                    List<ReturnC> returnCS=importDataVO.getReturnCS();
                    //构建ReturnDP对象数组
                    List<ReturnDP> returnDPS=new ArrayList<>();
                    for(ReturnC c:returnCS){
                        //根据sales_id获取num
                        int num=datePlatFormMapper.getNumBySalesId(c.getSales_id());
                        ReturnDP returnDP=new ReturnDP(c.getId(),importDataVO.getShopId(),c.getSales_id(),c.getReturn_date(),c.getProduct_id(),num);
                        returnDPS.add(returnDP);
                    }
                    //将ReturnDP对象数组写入数据库
                    for(ReturnDP r:returnDPS){
                        datePlatFormMapper.insertReturnDP(r);
                    }
                    //调用预测微服务更新预测值
                    predictService.ReturnRenew(returnDPS);
                    return ResponseVO.buildSuccess("Success");
                case "commodityData":
                    break;
                case "supplierData":
                    break;
            }

            return ResponseVO.buildSuccess();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }
}
