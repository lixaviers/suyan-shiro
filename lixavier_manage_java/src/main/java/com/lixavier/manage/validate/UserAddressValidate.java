package com.lixavier.manage.validate;

import com.lixavier.manage.model.UserAddress;
import com.lixavier.manage.result.Result;

import static com.lixavier.manage.util.ValidationUtil.*;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <service层用户收货地址验证类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-02>
 * @Modify Date: <2018-12-02>
 * @Version: <1.0>
 */
public class UserAddressValidate {

    public static boolean validateForCreate(UserAddress userAddress, Result<?> message) {

        if (isNull(message, UserAddress.USER_ID, userAddress.getUserId())) {
            return false;
        }
        if (isEmptyOrMoreThan(message, UserAddress.CONSIGNEE, userAddress.getConsignee(), 50)) {
            return false;
        }
        if (isEmptyOrMoreThan(message, UserAddress.MOBILE, userAddress.getMobile(), 32)) {
            return false;
        }

        if (isNull(message, UserAddress.PROVINCE_ID, userAddress.getProvinceId())) {
            return false;
        }

        if (isNull(message, UserAddress.CITY_ID, userAddress.getCityId())) {
            return false;
        }

        if (isNull(message, UserAddress.AREA_ID, userAddress.getAreaId())) {
            return false;
        }
        if (isEmptyOrMoreThan(message, UserAddress.ADDRESS, userAddress.getAddress(), 200)) {
            return false;
        }

        if (isNull(message, UserAddress.IS_DEFAULT, userAddress.getIsDefault())) {
            return false;
        }
        return true;
    }

    public static boolean validateForUpdate(UserAddress userAddress, Result<?> message) {
        if (isNull(message, UserAddress.ID, userAddress.getId())) {
            return false;
        }
        return validateForCreate(userAddress, message);
    }
}