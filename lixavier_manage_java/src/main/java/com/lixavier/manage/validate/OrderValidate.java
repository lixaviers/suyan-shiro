package com.lixavier.manage.validate;

import com.lixavier.manage.model.Order;
import com.lixavier.manage.req.GoodsDTO;
import com.lixavier.manage.req.OrderDTO;
import com.lixavier.manage.req.OrderGoodsDTO;
import com.lixavier.manage.req.OrderPayDTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.lixavier.manage.util.ValidationUtil.*;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <service层订单验证类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public class OrderValidate {

    public static boolean validateForCreate(OrderDTO order, Result<?> message) {

        if (isNull(message, "收货地址", order.getUserAddressId())) {
            return false;
        }

        if (CollectionUtils.isEmpty(order.getGoodsList())) {
            message.setErrorCode(ResultCode.COMMON_PARAM_INVALID, "商品列表");
            return false;
        }

        for (OrderGoodsDTO goodsDTO : order.getGoodsList()) {
            if (goodsDTO.getGoodsId() == null) {
                message.setErrorCode(ResultCode.COMMON_PARAM_INVALID, "商品");
                return false;
            }
            if (goodsDTO.getGoodsNumber() == null || goodsDTO.getGoodsNumber() < 1) {
                message.setErrorCode(ResultCode.COMMON_PARAM_INVALID, "商品");
                return false;
            }
        }

        if (isMoreThan(message, Order.MESSAGE, order.getMessage(), 512)) {
            return false;
        }

        return true;
    }
    public static boolean validateForPay(OrderPayDTO orderPayDTO, Result<?> message) {

        if (isNull(message, "订单号", orderPayDTO.getOrderNo())) {
            return false;
        }

        if (CollectionUtils.isNotEmpty(orderPayDTO.getGiftCardList())) {
            for (String giftCard : orderPayDTO.getGiftCardList()) {
                if (StringUtils.isEmpty(giftCard)) {
                    message.setErrorCode(ResultCode.COMMON_PARAM_INVALID, "礼品卡");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean validateForUpdate(Order order, Result<?> message) {
        if (isNull(message, Order.ID, order.getId())) {
            return false;
        }
//        return validateForCreate(order, message);
        return true;
    }
}