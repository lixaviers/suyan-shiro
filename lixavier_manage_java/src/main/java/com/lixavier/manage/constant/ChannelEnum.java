package com.lixavier.manage.constant;

/**
 * @Comments: 渠道类型
 */
public enum ChannelEnum {

	ACCOUNT_BALANCE              ((byte) 1, "账户余额"),
	BACKGROUND_TOP_UP            ((byte) 2, "后台充值"),
	;




	private byte value;
	private String desc;

	ChannelEnum(byte value, String desc){
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
        for (ChannelEnum element : ChannelEnum.values()) {
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
		for (ChannelEnum element : ChannelEnum.values()) {
			if (element.value == value.byteValue()) {
				return element.desc;
			}
		}
		return "";
	}
}
