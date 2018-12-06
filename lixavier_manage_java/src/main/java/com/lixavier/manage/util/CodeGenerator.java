package com.lixavier.manage.util;

import java.util.Date;
import java.util.Random;

public class CodeGenerator {
    private static Random random = new Random();
    private final static String NUMBERS_CODE_UPPERLETTER = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
    private final static String NUMBERS_CODE_LETTER = "1234567890abcdefghijklmnopqrstuvwxyz";
    private final static String NUMBERS = "1234567890";
    /**
     * 礼品券码长度
     */
    private final static int CARD_CODE_STEP_LENGTH = 4;
    private final static int CARD_CODE_LENGTH = 4;
    /**
     * 文件名长度
     */
    private final static int FILE_LENGTH = 15;
    /**
     * 订单号长度
     */
    private final static int ORDER_NO_LENGTH = 10;

    /**
     * 生成length长度的随机码
     *
     * @param length
     * @return
     */
    private static String getRandomString(int length, String source) {
        if (length <= 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(source.length());
            sb.append(source.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成礼品券码
     *
     * @return
     */
    public static String generateCardCode() {
        StringBuilder stringBuilder = new StringBuilder(50);
        for (int i = 0; i < CARD_CODE_STEP_LENGTH; i++) {
            stringBuilder.append(getRandomString(CARD_CODE_LENGTH, NUMBERS_CODE_UPPERLETTER));
            if (i != CARD_CODE_STEP_LENGTH - 1) {
                stringBuilder.append("-");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 生成文件名
     *
     * @return
     */
    public static String generateFileName() {
        return DateUtil.format(new Date(), "yyyyMMddsss") + getRandomString(FILE_LENGTH, NUMBERS_CODE_LETTER);
    }

    /**
     * 生成订单号
     *
     * @return
     */
    public static String generateOrderNo() {
        return DateUtil.format(new Date(), "yyMMddHHmmss") + getRandomString(ORDER_NO_LENGTH, NUMBERS);
    }


}
