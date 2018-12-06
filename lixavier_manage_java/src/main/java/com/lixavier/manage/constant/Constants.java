package com.lixavier.manage.constant;

import java.math.BigDecimal;

public interface Constants {

    String USER_SESSION_KEY = "lixavier_manage_user";

    /**
     * 商场订单前缀
     */
    String MALL_ORDER_PREFIX = "10";

    /**
     * 免运费金额
     */
    BigDecimal FREE_FREIGHT = new BigDecimal(88);

    /**
     * 运费金额
     */
    BigDecimal FREIGHT_AMOUNT = new BigDecimal(10);

}
