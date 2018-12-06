package com.lixavier.manage.validate;



import com.lixavier.manage.model.GiftCard;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;

import java.math.BigDecimal;

import static com.lixavier.manage.util.ValidationUtil.*;
import static com.lixavier.manage.util.ValidationUtil.isNull;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <service层礼品卡验证类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-21>
 * @Modify Date: <2018-11-21>
 * @Version: <1.0>
 */
public class GiftCardValidate {

    public static boolean validateForCreate(GiftCard giftCard, Result<?> message) {

        if (isNull(message, "数量", giftCard.getCount())) {
            return false;
        }

        if (isNull(message, GiftCard.PRICE, giftCard.getPrice())) {
            return false;
        }

        if (giftCard.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            message.setErrorCode(ResultCode.FIELD_LENGTH_MUST_LESS, GiftCard.PRICE, 0);
            return false;
        }


        if (isNull(message, GiftCard.END_TIME, giftCard.getEndTime())) {
            return false;
        }

        return true;
    }

    public static boolean validateForUpdate(GiftCard giftCard, Result<?> message) {
        if (isNull(message, GiftCard.ID, giftCard.getId())) {
            return false;
        }
        return validateForCreate(giftCard, message);
    }
}