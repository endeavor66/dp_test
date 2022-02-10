package com.example.dp.util;

import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;
import org.jpmml.evaluator.support_vector_machine.VoteDistribution;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SvrLoader {

    /**
     *  加载模型
     */
    public Evaluator loadPmml(String pmmlPath) {
        PMML pmml = new PMML();
        InputStream inputStream = null;
        try {
            // 读取resources文件夹下的pmml文件
            Resource resource = new ClassPathResource(pmmlPath);
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            return null;
        }
        InputStream is = inputStream;
        try {
            pmml = org.jpmml.model.PMMLUtil.unmarshal(is);
        } catch (SAXException | JAXBException e1) {
            e1.printStackTrace();
        } finally {
            //关闭输入流
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
        return modelEvaluatorFactory.newModelEvaluator(pmml);
    }




    /**
     *  分类预测
     */
    public Object predict(Evaluator evaluator,Map<String, Integer> featuremap) {

        List<InputField> inputFields = evaluator.getInputFields();
        // 从原始特征获取数据，作为模型输入
        Map<FieldName, FieldValue> arguments = new LinkedHashMap<FieldName, FieldValue>();
        for (InputField inputField : inputFields) {
            // 特征名称
            FieldName inputFieldName = inputField.getName();
            // 特征值
            Object rawValue = featuremap.get(inputFieldName.getValue());
            FieldValue inputFieldValue = inputField.prepare(rawValue);
            arguments.put(inputFieldName, inputFieldValue);
        }

        // 预测结果
        Map<FieldName, ?> results = evaluator.evaluate(arguments);
        List<TargetField> targetFields = evaluator.getTargetFields();
        Object targetFieldValue = null;
        for (TargetField targetField : targetFields) {
            FieldName targetFieldName = targetField.getName();
            targetFieldValue = results.get(targetFieldName);
            System.out.println("预测结果: " +  targetFieldValue);

        }
        return targetFieldValue;

    }




}
