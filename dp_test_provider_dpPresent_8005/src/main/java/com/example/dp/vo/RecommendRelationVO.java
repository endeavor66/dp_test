package com.example.dp.vo;

import com.example.dp.po.RecommendRelation;

import java.util.Date;
import lombok.Builder;

@Builder
public class RecommendRelationVO {
    String id;
    Date date;
    String product_id1;
    String product_id2;
    String relation_pra;

    public RecommendRelationVO() {
    }

    public RecommendRelationVO(String id, Date date, String product_id1, String product_id2, String relation_pra) {
        this.id = id;
        this.date = date;
        this.product_id1 = product_id1;
        this.product_id2 = product_id2;
        this.relation_pra = relation_pra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProduct_id1() {
        return product_id1;
    }

    public void setProduct_id1(String product_id1) {
        this.product_id1 = product_id1;
    }

    public String getProduct_id2() {
        return product_id2;
    }

    public void setProduct_id2(String product_id2) {
        this.product_id2 = product_id2;
    }

    public String getRelation_pra() {
        return relation_pra;
    }

    public void setRelation_pra(String relation_pra) {
        this.relation_pra = relation_pra;
    }

    public RecommendRelationVO(RecommendRelation recommendRelation){
        this.id = recommendRelation.getId();
        this.date =recommendRelation.getDate();
        this.product_id1 =recommendRelation.getProduct_id1();
        this.product_id2 = recommendRelation.getProduct_id2();
        this.relation_pra =recommendRelation.getRelation_pra();
    }
}
