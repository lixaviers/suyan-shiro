package com.lixavier.manage.validate;

import com.lixavier.manage.model.OrderGoods;
import com.lixavier.manage.result.Result;

import static com.lixavier.manage.util.ValidationUtil.*;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <service层订单商品验证类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public class OrderGoodsValidate {

    public static boolean validateForCreate(OrderGoods orderGoods, Result<?> message) {

        if (isNull(message,OrderGoods.USER_ID, orderGoods.getUserId())) {
            return false;
        }

        if (isNull(message,OrderGoods.ORDER_ID, orderGoods.getOrderId())) {
            return false;
        }
        if (isEmptyOrMoreThan(message, OrderGoods.ORDER_NO, orderGoods.getOrderNo(), 32)) {
            return false;
        }

        if (isNull(message,OrderGoods.GOODS_ID, orderGoods.getGoodsId())) {
            return false;
        }
        if (isEmptyOrMoreThan(message, OrderGoods.GOODS_NAME, orderGoods.getGoodsName(), 255)) {
            return false;
        }

        if (isNull(message,OrderGoods.GOODS_NUMBER, orderGoods.getGoodsNumber())) {
            return false;
        }

        if (isNull(message,OrderGoods.GOODS_PRICE, orderGoods.getGoodsPrice())) {
            return false;
        }

        if (isNull(message,OrderGoods.TOTAL_PRICE, orderGoods.getTotalPrice())) {
            return false;
        }

        if (isNull(message,OrderGoods.PROMOTION_PRICE, orderGoods.getPromotionPrice())) {
            return false;
        }

        if (isNull(message,OrderGoods.COMMON_STATUS, orderGoods.getCommonStatus())) {
            return false;
        }
        return true;
    }
    
    public static boolean validateForUpdate(OrderGoods orderGoods, Result<?> message) {
        if (isNull(message,OrderGoods.ID, orderGoods.getId())) {
            return false;
        }
        return validateForCreate(orderGoods, message);
    }
}