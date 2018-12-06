package com.lixavier.manage.validate;

import com.lixavier.manage.model.Menu;
import com.lixavier.manage.result.Result;

import static com.lixavier.manage.util.ValidationUtil.*;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <service层菜单验证类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public class MenuValidate {

    public static boolean validateForCreate(Menu menu, Result<?> message) {

        if (isMoreThan(message, Menu.NAME, menu.getName(), 50)) {
            return false;
        }

        if (isMoreThan(message, Menu.URL, menu.getUrl(), 200)) {
            return false;
        }

        if (isMoreThan(message, Menu.PERMS, menu.getPerms(), 500)) {
            return false;
        }

        if (isMoreThan(message, Menu.ICON, menu.getIcon(), 50)) {
            return false;
        }

        if (isNull(message, Menu.ORDER_NUM, menu.getOrderNum())) {
            return false;
        }
        return true;
    }

    public static boolean validateForUpdate(Menu menu, Result<?> message) {
        if (isNull(message, Menu.ID, menu.getId())) {
            return false;
        }
        return validateForCreate(menu, message);
    }
}