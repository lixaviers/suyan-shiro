package com.lixavier.manage.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

    /**
     * 格式化用户名
     * lixavier l***r
     * @param userName
     * @return
     */
    public static String formatUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return "";
        }
        String start = userName.substring(0, 1);
        String end = userName.substring(userName.length() - 1);
        return start + "***" + end;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        System.out.println(formatUserName("lixavier"));
    }
}
