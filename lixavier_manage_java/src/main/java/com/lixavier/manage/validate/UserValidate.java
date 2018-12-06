package com.lixavier.manage.validate;

import com.lixavier.manage.model.User;
import com.lixavier.manage.result.Result;

import static com.lixavier.manage.util.ValidationUtil.*;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <service层系统验证类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public class UserValidate {

    public static boolean validateForCreate(User user, Result<?> message) {
        if (isEmptyOrLessThan(message, User.USER_NAME, user.getUserName(), 6)) {
            return false;
        }
        if (isMoreThan(message, User.USER_NAME, user.getUserName(), 16)) {
            return false;
        }
        if (isEmptyOrMoreThan(message, User.NICK_NAME, user.getNickName(), 16)) {
            return false;
        }

        if (isEmptyOrLessThan(message, User.PASSWORD, user.getPassword(), 6)) {
            return false;
        }

        if (isMoreThan(message, User.PASSWORD, user.getPassword(), 16)) {
            return false;
        }

        if (isMoreThan(message, User.EMAIL, user.getEmail(), 100)) {
            return false;
        }

        if (isMoreThan(message, User.MOBILE, user.getMobile(), 100)) {
            return false;
        }

        if (isNull(message, User.IS_ADMIN, user.getIsAdmin())) {
            return false;
        }
        return true;
    }

    public static boolean validateForUpdate(User user, Result<?> message) {
        if (isNull(message, User.ID, user.getId())) {
            return false;
        }

        if (isEmptyOrMoreThan(message, User.NICK_NAME, user.getNickName(), 16)) {
            return false;
        }

        if (isMoreThan(message, User.EMAIL, user.getEmail(), 100)) {
            return false;
        }

        if (isMoreThan(message, User.MOBILE, user.getMobile(), 100)) {
            return false;
        }

        if (isNull(message, User.IS_ADMIN, user.getIsAdmin())) {
            return false;
        }
        return true;
    }

    public static boolean validateForUpdateBalance(User user, Result<?> message) {
        if (isNull(message, User.ID, user.getId())) {
            return false;
        }

        if (isNull(message, "金额", user.getBalance())) {
            return false;
        }
        return true;
    }
}