package com.lixavier.manage.validate;

import com.lixavier.manage.model.UserCart;
import com.lixavier.manage.result.Result;

import static com.lixavier.manage.util.ValidationUtil.*;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <service层用户购物车验证类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public class UserCartValidate {

    public static boolean validateForCreate(UserCart userCart, Result<?> message) {

        if (isNull(message, UserCart.USER_ID, userCart.getUserId())) {
            return false;
        }

        if (isNull(message, UserCart.GOODS_ID, userCart.getGoodsId())) {
            return false;
        }

        if (isNull(message, UserCart.NUMBER, userCart.getNumber())) {
            return false;
        }
        return true;
    }

    public static boolean validateForUpdate(UserCart userCart, Result<?> message) {
        if (isNull(message, UserCart.ID, userCart.getId())) {
            return false;
        }
        return validateForCreate(userCart, message);
    }
}