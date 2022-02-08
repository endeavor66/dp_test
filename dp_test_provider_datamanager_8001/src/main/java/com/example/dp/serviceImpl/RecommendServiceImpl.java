package com.example.dp.serviceImpl;

import com.example.dp.mapper.PredictMapper;
import com.example.dp.mapper.RecommendMapper;
import com.example.dp.pojo.PredictTotalSales;
import com.example.dp.pojo.RecommendResult;
import com.example.dp.service.RecommendService;
import com.example.dp.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private RecommendMapper recommendMapper;
    @Autowired
    private PredictMapper predictMapper;

    @Override
    public ResponseVO getSalesNum(List<String> id) {
        try {
            List<Double> result = new ArrayList<>();
            List<PredictTotalSales> predictTotalSales = new ArrayList<>();
            for (String s : id) {
                predictTotalSales.add(predictMapper.selectProductInfById(s));
                result.add(predictTotalSales.get(Integer.parseInt(s)).getActual());
            }
            return ResponseVO.buildSuccess(result);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO insertRecommendResult(String shop_id, Date recommand_date, String product_id, double weight, String reason) {
        try{
            //取出所有推荐表id
            List<String> recommendResultIdList = recommendMapper.selectAllRecommendResultId();
            int []IdList =new int[recommendResultIdList.size()];
            int max=0;
            for(int i =0;i<IdList.length;i++){
                IdList[i]=Integer.parseInt( recommendResultIdList.get(i));
                if(IdList[i]>max){
                    max= IdList[i];
                }
            }
            int num =max+1;
            String id =String.valueOf(num);

            char[]ary1=id.toCharArray();
            char[]ary2={'0','0','0','0'};
            System.arraycopy(ary1,0,ary2,ary2.length-ary1.length,ary1.length);
            String newId=new String(ary2);//获得新增的id

            RecommendResult temp = new RecommendResult(newId,shop_id,recommand_date,product_id,weight,reason);

            recommendMapper.insertRecommendResult(temp);
            return ResponseVO.buildSuccess("写入数据成功");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");
        }
    }

    @Override
    public ResponseVO getProductRelationInf(String product_id1, String product_id2){
            try{
                String result = recommendMapper.getRelation_pra(product_id1,product_id2);
                return ResponseVO.buildSuccess(result);
            }catch (Exception e) {
                e.printStackTrace();
                return ResponseVO.buildFailure("Impl failed");
            }
    }
}
