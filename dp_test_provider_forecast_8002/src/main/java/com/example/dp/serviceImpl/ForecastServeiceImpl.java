package com.example.dp.serviceImpl;


import com.alibaba.fastjson.JSONArray;
import com.example.dp.ProviderApi.DataManagerApi;
import com.example.dp.po.Sales;
import com.example.dp.po.Shop;
import com.example.dp.po.ReturnDP;
import com.example.dp.po.SalesDP;
import com.example.dp.service.ForecastService;
import com.example.dp.util.SvrLoader;
import com.example.dp.vo.ResponseVO;
import org.jpmml.evaluator.Evaluator;
import org.python.core.*;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



@Service
public class ForecastServeiceImpl implements ForecastService {

    @Autowired
    private DataManagerApi dataManagerApi;




    @Override
    public List<Integer> DailyRenew(List<SalesDP> newdata){
        List<Integer> forecastresult = new ArrayList<>();

        try{
            //SVR部分------------------------------------------------------------
            //加载pmml
            String pmmlPath = "svr.pmml";
            SvrLoader svrLoader = new SvrLoader();
            Evaluator model = svrLoader.loadPmml(pmmlPath);

            //准备pmml需要的数据
            Map<String, Integer> data = new HashMap<String, Integer>();
            data.put("x1", newdata.get(0).getNum());
            data.put("x2", newdata.get(1).getNum());

            //多步预测
//            int temp,k=3;
//            for(int i = 0;i<k;i++){
//                temp = (Integer)svrLoader.predict(model,data);
//                forecastresult.add(temp);
//                data.replace("x1",data.get("x2"));
//                data.replace("x2",temp);
//            }

            //LSTM部分-------------------------------------------------------------------------------
            List<Integer> b = new ArrayList<>();
            for(SalesDP salesDP: newdata){
                b.add(salesDP.getNum());
            }
            String jsonObjectdata = JSONArray.toJSONString(b);
            String[] args = new String[] { "python", "D:\\jupyter_notebook\\shujuzhongtai\\lstm_predict.py",jsonObjectdata};
            Process proc = Runtime.getRuntime().exec(args);// 执行py文件
            String line = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();

            //ARIMA部分--------------------------------------------------------------------------------------




            //单步预测
            //TODO: 将三个模型的结果用组合预测模型整合
            forecastresult.add((Integer)svrLoader.predict(model,data));

            //System.out.println("anwser = " + pyobj.toString());

            return forecastresult;
        }
        catch (Exception e){
            e.printStackTrace();
            return forecastresult;

        }

    }

    @Override
    public ResponseVO ReturnRenew(List<ReturnDP> newdata){

        try{

            //加载pmml
            String pmmlPath = "svr.pmml";
            SvrLoader svrLoader = new SvrLoader();
            Evaluator model = svrLoader.loadPmml(pmmlPath);

            //准备pmml需要的数据
            Map<String, Integer> data = new HashMap<String, Integer>();
            data.put("x1", newdata.get(0).getNum());
            data.put("x2", newdata.get(1).getNum());
            List<Integer> forecastresult = new ArrayList<>();
            //多步预测
//            int temp,k=3;
//            for(int i = 0;i<k;i++){
//                temp = (Integer)svrLoader.predict(model,data);
//                forecastresult.add(temp);
//                data.replace("x1",data.get("x2"));
//                data.replace("x2",temp);
//            }

            //单步预测
            forecastresult.add((Integer)svrLoader.predict(model,data));

            //System.out.println("anwser = " + pyobj.toString());

            return ResponseVO.buildSuccess(forecastresult);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");

        }

    }

    @Override
    public ResponseVO SalesModelInit(List<SalesDP> newdata){

        try{


            //开始调用python程序进行模型初始化

            //PythonInterpreter interpreter = new PythonInterpreter();
            //interpreter.execfile("D:\\jupyter_notebook\\shujuzhongtai\\SVR_init.py");

            //PyFunction func = (PyFunction) interpreter.get("svm_timeseries_prediction", PyFunction.class);

            List<String> times = new ArrayList<>();
            List<Integer> totalNum = new ArrayList<>();

            String temp = "";

//            times.add("\"2021-5-1\"");
//            times.add("\"2021-5-2\"");
//            times.add("\"2021-5-3\"");
//            times.add("\"2021-5-4\"");
//            times.add("\"2021-5-5\"");
//            totalNum.add(5);
//            totalNum.add(6);
//            totalNum.add(7);
//            totalNum.add(8);
//            totalNum.add(9);

            //完成对数据的载入
            for(SalesDP sales :newdata){
                temp = "\""+sales.getSales_date().toString()+"\"";
                times.add(temp);
                totalNum.add(sales.getNum());
            }
            //将数据转换成json，方便传参
            String jsonObjectA = JSONArray.toJSONString(times);
            String jsonObjectB = JSONArray.toJSONString(totalNum);
            System.out.println(jsonObjectA);
            //func.__call__(new PyString(jsonObjectA), new PyString(jsonObjectB),new PyInteger(10),new PyInteger(1));
            String[] args1 = new String[] { "python", "D:\\jupyter_notebook\\shujuzhongtai\\SVR_init.py", jsonObjectA, jsonObjectB };
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            //lstm初始化
            args1 = new String[] { "python", "D:\\jupyter_notebook\\shujuzhongtai\\lstm_init.py", jsonObjectB };


            //查看python文件运行输出
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

            //查看运行状态及报错
            int res = proc.waitFor();
            System.out.println("proc.waitFor() == "+res);
            BufferedReader isError = new BufferedReader(new InputStreamReader(proc.getErrorStream(),"gbk"));
            String line1 = null;
            while ((line1 = isError.readLine()) != null) {
                System.out.println(line1);
            }
            return ResponseVO.buildSuccess("running well");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");

        }

    }

    @Override
    public ResponseVO ReturnModelInit(List<ReturnDP> newdata){

        try{


            //开始调用python程序进行模型初始化

            //PythonInterpreter interpreter = new PythonInterpreter();
            //interpreter.execfile("D:\\jupyter_notebook\\shujuzhongtai\\SVR_init.py");

            //PyFunction func = (PyFunction) interpreter.get("svm_timeseries_prediction", PyFunction.class);

            List<String> times = new ArrayList<>();
            List<Integer> totalNum = new ArrayList<>();

            String temp = "";

//            times.add("\"2021-5-1\"");
//            times.add("\"2021-5-2\"");
//            times.add("\"2021-5-3\"");
//            times.add("\"2021-5-4\"");
//            times.add("\"2021-5-5\"");
//            totalNum.add(5);
//            totalNum.add(6);
//            totalNum.add(7);
//            totalNum.add(8);
//            totalNum.add(9);

            //完成对数据的载入
            for(ReturnDP returnDP :newdata){
                temp = "\""+returnDP.getReturn_date().toString()+"\"";
                times.add(temp);
                totalNum.add(returnDP.getNum());
            }
            //将数据转换成json，方便传参
            String jsonObjectA = JSONArray.toJSONString(times);
            String jsonObjectB = JSONArray.toJSONString(totalNum);
            System.out.println(jsonObjectA);
            //func.__call__(new PyString(jsonObjectA), new PyString(jsonObjectB),new PyInteger(10),new PyInteger(1));
            String[] args1 = new String[] { "python", "D:\\jupyter_notebook\\shujuzhongtai\\SVR_init.py", jsonObjectA, jsonObjectB };
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            //查看python文件运行输出
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

            //查看运行状态及报错
            int res = proc.waitFor();
            System.out.println("proc.waitFor() == "+res);
            BufferedReader isError = new BufferedReader(new InputStreamReader(proc.getErrorStream(),"gbk"));
            String line1 = null;
            while ((line1 = isError.readLine()) != null) {
                System.out.println(line1);
            }
            return ResponseVO.buildSuccess("running well");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");

        }

    }

    @Override
    public ResponseVO test(){
        try{
            String pmmlPath = "svr.pmml";


            SvrLoader svrLoader = new SvrLoader();
            Evaluator model = svrLoader.loadPmml(pmmlPath);
            Map<String, Integer> data = new HashMap<String, Integer>();
            //Double todo = 5.0;
            data.put("x1", 5);
            //data.put("x2",6.0);
            svrLoader.predict(model,data);



            //System.out.println("anwser = " + pyobj.toString());


            return ResponseVO.buildSuccess("ye");

        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");

        }
    }

    @Override
    public ResponseVO test2(){
        try{
            //Process proc;
//            try {
//                //proc = Runtime.getRuntime().exec("python D:\\jupyter_notebook\\shujuzhongtai\\SVR_init.py");// 执行py文件
//
//                List<String> a = new ArrayList<>();
//                List<Integer> b = new ArrayList<>();
//
//                a.add("2021-5-1");
//                a.add("2021-5-2");
//                a.add("2021-5-3");
//                b.add(5);
//                b.add(6);
//                b.add(7);
//                String jsonObjectA = JSONArray.toJSONString(a);
//                String jsonObjectA1 = String.valueOf(a);
//                String jsonObjectB = JSONArray.toJSONString(b);
//
//                String[] args1 = new String[] { "python", "D:\\jupyter_notebook\\shujuzhongtai\\init_test.py",String.valueOf(11),String.valueOf(12)};
//                Process proc = Runtime.getRuntime().exec(args1);// 执行py文件
//
//
//                //用输入输出流来截取结果
//                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//                String line = null;
//
//
//                while ((line = in.readLine()) != null) {
//                    System.out.println(line);
//                }
//                in.close();
//                proc.waitFor();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            //int a = 18;
            //int b = 23;

            List<String> a = new ArrayList<>();
            List<Integer> b = new ArrayList<>();

//            a.add("\"2021-5-1\"");
//            a.add("\"2021-5-2\"");
//            a.add("\"2021-5-3\"");
            b.add(5);
            b.add(6);
            b.add(7);
            b.add(8);
            b.add(5);
            b.add(6);
            b.add(7);
            b.add(8);
            b.add(5);
            b.add(6);
            b.add(7);
            b.add(8);

            String jsonObjectA = JSONArray.toJSONString(a);
            String jsonObjectA1 = String.valueOf(a);
            String jsonObjectB = JSONArray.toJSONString(b);
            //System.out.println(jsonObjectA);
            System.out.println(jsonObjectB);

            try {
                String[] args = new String[] { "python", "D:\\jupyter_notebook\\shujuzhongtai\\lstm_predict.py",jsonObjectB};
                Process proc = Runtime.getRuntime().exec(args);// 执行py文件

                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                in.close();
                proc.waitFor();
                int res=proc.waitFor();
                System.out.println("proc.waitFor() == "+res);
                BufferedReader isError = new BufferedReader(new InputStreamReader(proc.getErrorStream(),"gbk"));
                String line1 = null;
                while ((line1 = isError.readLine()) != null) {
                    System.out.println(line1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ResponseVO.buildSuccess("well");

        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("Impl failed");

        }
    }

}
