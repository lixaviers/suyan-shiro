package com.lixavier.manage.constant;

/**
 * @Comments: 收支明细类型
 */
public enum AccountDetailTypeEnum {

	CASH                 ((byte) 1, "现金"),
//	GIFT_CARD            ((byte) 2, "礼品卡"),
	;




	private byte value;
	private String desc;

	AccountDetailTypeEnum(byte value, String desc){
		this.value = value;
		this.desc = desc;
	}

    public byte getValue() {
        return value;
    }

    public String getDesc() {
		return desc;
	}

	public static boolean exists(Byte status) {
        if (status == null) {
            return false;
        }
        byte s = status.byteValue();
        return exists(s);
    }

    public static boolean exists(byte s) {
        for (AccountDetailTypeEnum element : AccountDetailTypeEnum.values()) {
            if (element.value == s) {
                return true;
            }
        }
        return false;
    }

    public boolean equal(Byte val) {
        return val == null ? false : val.byteValue() == this.value;
    }

	public static String getDescByValue(Byte value) {
		if (value == null) {
			return "";
		}
		for (AccountDetailTypeEnum element : AccountDetailTypeEnum.values()) {
			if (element.value == value.byteValue()) {
				return element.desc;
			}
		}
		return "";
	}
}
