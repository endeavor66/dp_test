package com.example.dp.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.example.dp.ProviderApi.DataManagerApi;
import com.example.dp.po.*;
import com.example.dp.service.DpPresentService;
import com.example.dp.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class DpPresentServiceImpl implements DpPresentService {

    @Autowired
    private RestTemplate template;

    @Autowired
    private DataManagerApi dataManagerApi;

    private static final String url = "http://172.19.241.99:9001/datamanager";

    //学校云主机测试
    //private static final String url = "http://172.19.241.210:9001/datamanager";

    @Override
    public Object login(String id,String code){
        try{
            boolean result=false;

            //ResponseVO r = template.getForObject(url+"/provider_DataManager/login?userName="+id+"&passWord="+code, ResponseVO.class);
            //ResponseVO r = template.getForObject(url+"/shop/list", ResponseVO.class);
            ResponseVO r = dataManagerApi.loginResult(id,code);
            //List<Shop> r = dataManagerApi.queryAll();
            if(r.getStatus()==0){
                result=true;
            }

            /*
            //展示板
            if(id.equals("jack")&&code.equals("123456")){
                System.out.println("登录成功");
                result = true;
            }
            */
            //展示板
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public Object loginData(String managerId){
        try{
            ResponseVO r = template.getForObject(url+"/provider_DataManager/loginInfo?userName="+managerId, ResponseVO.class);
            System.out.println("loginData == "+r.getData());
            return r.getData();

            /*
            //展示版
            List<ShopInfNeed> shops = new ArrayList<>();
            shops.add(new ShopInfNeed("0001","KFC"));
            shops.add(new ShopInfNeed("0002","老乡鸡"));
            return new ShopVO("0001","jack",shops);
            //展示版
             */
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public Object getShopInfo(String id){
        try{
            /*临时
            ResponseVO r = template.getForObject(url+"/provider_DataManager/getShopInfo?id="+id, ResponseVO.class);
            System.out.println("getShopInfo == "+r.getData());
            return r.getData();

             */

            //展示版
            return new ShopVObyD("0001", "KFC", "上海", "快餐连锁", new Date(), "上海市的第一家肯德基，见证了上海市区的变迁，多年历史，不变的却是为顾客服务的一片心意", "贾老板");
            //展示版
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public Object getRecommendProduct(String storeId){
        try{
            /*临时
            ResponseVO r = template.getForObject(url+"/provider_DataManager/getRecommendProduct?storeId="+storeId, ResponseVO.class);
            System.out.println("getRecommendProduct == "+r.getData());
            return r.getData();

             */

            //展示版
            List<RecomendProductVO> result=new ArrayList<>();

            List<Supplier> allSuppliers = new ArrayList<>();
            allSuppliers.add(new Supplier("0001", "开心农场", "盐城", 0.6, 6, 0.8, 0.6));
            allSuppliers.add(new Supplier("0002", "精选农场", "苏州", 0.9, 6, 0.8, 0.6));

            List<Material> materials = new ArrayList<>();
            materials.add(new Material("0001", "鸡蛋", allSuppliers));

            RecomendProductVO r=new RecomendProductVO("0001", "蛋黄酥", "与本点商品“榴莲酥”相似", materials);
            RecomendProductVO r2=new RecomendProductVO("0002", "冰糖雪梨", "与本点商品“可口可乐”相似", materials);
            RecomendProductVO r3=new RecomendProductVO("0003", "盐焗腰果", "与本点商品“花生”相似", materials);

            result.add(r);
            result.add(r2);
            result.add(r3);

            ResponseVO res = new ResponseVO(0,"success",result);

            RecommendProductListVO ans = new RecommendProductListVO(result);


            return ans;
            //展示版
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public Object getPredictSales(String storeId, String commodityId, String type){
        try{
            /*临时
            ResponseVO r = template.getForObject(url+"/provider_DataManager/getPredictSales?storeId="+storeId+"&commodity="+commodityId+"&type="+type, ResponseVO.class);
            System.out.println("getPredictSales == "+r.getData());
            return r.getData();

             */

            //展示版
            List<Integer> actual = new ArrayList<>();
            List<String> times = new ArrayList<>();
            List<Integer> predict =new ArrayList<>();
            List list = Arrays.asList("2019/9/1","2019/9/2","2019/9/3","2019/9/4","2019/9/5","2019/9/6","2019/9/7","2019/9/8","2019/9/9","2019/9/10","2019/9/11","2019/9/12");
            times.addAll(list);
            List list1 = Arrays.asList(16,17,18,12,13,11,16,18,20,29,23,20);
            actual.addAll(list1);
            List list2 = Arrays.asList(19,16,18,20,11,13,18,16,15,26,29,20);
            predict.addAll(list2);

            PredictSalesVO returnResult = new PredictSalesVO(times,actual,predict);


            return returnResult;
            //展示版
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public Object getPredictReturn(String storeId,String commodityId){
        try{
            /*
            System.out.println(storeId);
            System.out.println(commodityId);

            ResponseVO r = template.getForObject(url+"/provider_DataManager/getPredicrtReturn?storeId="+storeId+"&commodity="+commodityId, ResponseVO.class);
            System.out.println("getPredictReturn == "+r.getData());
            return r.getData();

             */


            //展示版
            //List<PredictReturnVO> returnResult = new ArrayList<>();
            List<String> times = new ArrayList<>();
            List<Double> result = new ArrayList<>();
            List list = Arrays.asList("2019/9/1","2019/9/2","2019/9/3","2019/9/4","2019/9/5","2019/9/6","2019/9/7","2019/9/8","2019/9/9","2019/9/10","2019/9/11","2019/9/12");
            times.addAll(list);
            List list1 = Arrays.asList(0.2,0.3,0.1,0.1,0.5,0.3,0.8,0.0,0.3,0.9,0.2,0.1);
            result.addAll(list1);

            ResponseVO res = new ResponseVO(0,"success",new PredictReturnVO(commodityId,"盐焗腰果",times,result));

            //returnResult.add(new PredictReturnVO(commodityId,"盐焗腰果",times,result));
            return res.getData();
            //展示版
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public Object getAllPredictSales(List<String> storeId,String type){
        try{
            /*临时
            // /provider_DataManager/getProductRelationInf
            ResponseVO r = template.getForObject(url+"/provider_DataManager/getAllPredictSales?storeId="+storeId+"&type="+type, ResponseVO.class);
            System.out.println("getAllPredictSales == "+r.getData());
            return r.getData();

             */

            //展示版
            List<GetAllPredictSalesVO> result = new ArrayList<>();
            List<String> timesTotal = new ArrayList<>();
            List<Integer> actualTotal = new ArrayList<>();
            List<Double> predictTotal = new ArrayList<>();

            List list = Arrays.asList("2019/9/1","2019/9/2","2019/9/3","2019/9/4","2019/9/5","2019/9/6","2019/9/7","2019/9/8","2019/9/9","2019/9/10","2019/9/11","2019/9/12");
            timesTotal.addAll(list);
            List list1 = Arrays.asList(30,67,85,50,30,56,39,51,62,78,61,35);
            actualTotal.addAll(list1);
            List list2 = Arrays.asList(35.0,45.0,79.0,80.0,60.0,50.0,52.0,41.0,60.0,76.0,70.0,50.0);
            predictTotal.addAll(list2);



            result.add(new GetAllPredictSalesVO(timesTotal,actualTotal,predictTotal));
            return ResponseVO.buildSuccess(result);
            //展示版
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public Object getAllPredictReturn(List<String> storeId){
        try{
            /*临时
            ResponseVO r = template.getForObject(url+"/provider_DataManager/getAllPredictReturn?storeId="+storeId, ResponseVO.class);
            System.out.println("getAllPredictReturn == "+r.getData());
            return r.getData();

             */

            //展示版
            List<GetAllPredictReturnVO> finalPredictReturn = new ArrayList<>();
            List<String> times = new ArrayList<>();
            List<Double> result = new ArrayList<>();

            List list = Arrays.asList("2019/9/1","2019/9/2","2019/9/3","2019/9/4","2019/9/5","2019/9/6","2019/9/7","2019/9/8","2019/9/9","2019/9/10","2019/9/11","2019/9/12");
            times.addAll(list);

            List list1 = Arrays.asList(3,6,8,5,3,5,3,5,6,7,6,3);
            result.addAll(list1);




            finalPredictReturn.add(new GetAllPredictReturnVO(times,result));
            return ResponseVO.buildSuccess(finalPredictReturn);
            //展示版
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public Object getAllShopInfo(List<String> storeId){
        try{
            return "没写";
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public Object importData(String fileContent,String type,String shopId){
        try {
            switch (type){
                case "salesData":
                    List<SalesC> salesCList= JSONArray.parseArray(fileContent, SalesC.class);
                    //将对象List传给数据库微服务
                    ImportDataVO importDataVO=new ImportDataVO(type,salesCList,null,shopId);
                    ResponseVO r=template.postForObject(url+"/importData",importDataVO, ResponseVO.class);
                    return "Success";
                case "returnData":
                    List<ReturnC> returnCList = JSONArray.parseArray(fileContent, ReturnC.class);
                    //将对象List传给数据库微服务
                    ImportDataVO importDataVO1=new ImportDataVO(type,null,returnCList,shopId);
                    ResponseVO r1=template.postForObject(url+"/importData",importDataVO1, ResponseVO.class);
                    return "Success";
                case "commodityData":
                    break;
                case "supplierData":
                    break;
            }
            return "Failed";
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }
}
