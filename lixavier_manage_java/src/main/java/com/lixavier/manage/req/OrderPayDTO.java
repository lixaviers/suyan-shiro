package com.lixavier.manage.req;

import java.io.Serializable;
import java.util.List;

public class OrderPayDTO implements Serializable {
    /**
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;

            
    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 礼品卡
     */
    private List<String> giftCardList;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public List<String> getGiftCardList() {
        return giftCardList;
    }

    public void setGiftCardList(List<String> giftCardList) {
        this.giftCardList = giftCardList;
    }

}