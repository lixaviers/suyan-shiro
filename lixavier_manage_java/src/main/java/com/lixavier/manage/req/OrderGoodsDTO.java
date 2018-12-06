package com.lixavier.manage.req;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderGoodsDTO implements Serializable {
    /**
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Long goodsId;
            
    /**
     * 商品数量
     */
    private Integer goodsNumber;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }
}