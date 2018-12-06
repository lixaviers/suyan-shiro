package com.lixavier.manage.constant;

/**
 * @Comments: 地址类型
 */
public enum AddressLevelEnum {
	COUNTRY                  ((byte) 1, "国家"),
	PROVINCE                 ((byte) 2, "省份"),
	CITY                     ((byte) 3, "城市"),
	AREA                     ((byte) 4, "区"),
	;

	private byte value;
	private String desc;

	AddressLevelEnum(byte value, String desc){
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
        for (AddressLevelEnum element : AddressLevelEnum.values()) {
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
		for (AddressLevelEnum element : AddressLevelEnum.values()) {
			if (element.value == value.byteValue()) {
				return element.desc;
			}
		}
		return "";
	}
}
