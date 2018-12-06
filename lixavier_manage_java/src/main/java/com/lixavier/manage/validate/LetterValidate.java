package com.lixavier.manage.validate;

import com.lixavier.manage.model.Letter;
import com.lixavier.manage.result.Result;

import static com.lixavier.manage.util.ValidationUtil.isMoreThan;
import static com.lixavier.manage.util.ValidationUtil.isNull;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <service层站内信验证类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public class LetterValidate {

    public static boolean validateForCreate(Letter letter, Result<?> message) {

        if (isMoreThan(message, Letter.TITLE, letter.getTitle(), 100)) {
            return false;
        }

        if (isMoreThan(message, Letter.CONTENT, letter.getContent(), 2000)) {
            return false;
        }
        return true;
    }
    
    public static boolean validateForUpdate(Letter letter, Result<?> message) {
        if (isNull(message,Letter.ID, letter.getId())) {
            return false;
        }
        return validateForCreate(letter, message);
    }
}