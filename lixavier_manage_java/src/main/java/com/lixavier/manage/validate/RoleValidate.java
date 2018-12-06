package com.lixavier.manage.validate;

import com.lixavier.manage.model.Role;
import com.lixavier.manage.result.Result;

import static com.lixavier.manage.util.ValidationUtil.*;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <service层角色验证类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public class RoleValidate {

    public static boolean validateForCreate(Role role, Result<?> message) {

        if (isMoreThan(message, Role.ROLE_NAME, role.getRoleName(), 100)) {
            return false;
        }

        if (isMoreThan(message, Role.REMARK, role.getRemark(), 100)) {
            return false;
        }
        return true;
    }
    
    public static boolean validateForUpdate(Role role, Result<?> message) {
        if (isNull(message,Role.ID, role.getId())) {
            return false;
        }
        return validateForCreate(role, message);
    }
}