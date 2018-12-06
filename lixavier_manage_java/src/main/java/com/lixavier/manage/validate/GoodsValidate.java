package com.lixavier.manage.validate;

import com.lixavier.manage.model.Goods;
import com.lixavier.manage.model.Order;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;

import java.math.BigDecimal;

import static com.lixavier.manage.util.ValidationUtil.*;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <service层商品验证类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public class GoodsValidate {

    public static boolean validateForCreate(Goods goods, Result<?> message) {

        if (isMoreThan(message, Goods.NAME, goods.getName(), 255)) {
            return false;
        }

        if (isMoreThan(message, Goods.PICTURE_URL, goods.getPictureUrl(), 255)) {
            return false;
        }
        return true;
    }

    public static boolean validateForUpdate(Goods goods, Result<?> message) {
        if (isNull(message, Goods.ID, goods.getId())) {
            return false;
        }
        return validateForCreate(goods, message);
    }
}