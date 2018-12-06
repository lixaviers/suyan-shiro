package com.lixavier.manage.req;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDTO implements Serializable {
    /**
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;

            
    /**
     * 收货地址
     */
    private Long userAddressId;

    /**
     * 商品列表
     */
    private List<OrderGoodsDTO> goodsList;

    /**
     * 订单留言
     */
    private String message;

    /**
     * 是否从购物车进入
     */
    private Boolean flag;

    public Long getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Long userAddressId) {
        this.userAddressId = userAddressId;
    }

    public List<OrderGoodsDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsDTO> goodsList) {
        this.goodsList = goodsList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}