package com.lixavier.manage.validate;

import com.lixavier.manage.model.UserRole;
import com.lixavier.manage.result.Result;

import static com.lixavier.manage.util.ValidationUtil.*;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <service层用户与角色对应关系验证类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public class UserRoleValidate {

    public static boolean validateForCreate(UserRole userRole, Result<?> message) {
        return true;
    }
    
    public static boolean validateForUpdate(UserRole userRole, Result<?> message) {
        if (isNull(message,UserRole.ID, userRole.getId())) {
            return false;
        }
        return validateForCreate(userRole, message);
    }
}