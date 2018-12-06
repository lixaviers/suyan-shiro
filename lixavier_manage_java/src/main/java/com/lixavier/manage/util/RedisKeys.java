package com.lixavier.manage.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Redis所有Keys
 */
public class RedisKeys {
    public static final String PREFIX = "lixavier:";
    public static final String SESSION = "sessionid:";

    public static String getShiroSessionKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        StringBuilder sbBuilder = new StringBuilder(50);
        sbBuilder.append(PREFIX);
        sbBuilder.append(SESSION);
        sbBuilder.append(key);
        return sbBuilder.toString();
    }

}
