package com.lixavier.manage.model;


public class GoodsDescription {

    public static final String GOODS_ID = "商品id";
    public static final String DESCRIPTION = "描述";


    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 描述
     */
    private String description;
    

    public Long getGoodsId(){
        return goodsId;
    }
    
    public void setGoodsId(Long goodsId){
        this.goodsId = goodsId;
    }

    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }

    /**
    * The following custom property definition
    */

}