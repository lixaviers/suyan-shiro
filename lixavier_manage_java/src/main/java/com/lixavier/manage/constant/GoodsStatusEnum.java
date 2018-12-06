package com.lixavier.manage.constant;

/**
 * @Comments: 商品状态
 */
public enum GoodsStatusEnum {
	NORMAL                      ((byte) 0, "正常"),
	;

	private byte value;
	private String desc;

	GoodsStatusEnum(byte value, String desc){
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
        for (GoodsStatusEnum element : GoodsStatusEnum.values()) {
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
		for (GoodsStatusEnum element : GoodsStatusEnum.values()) {
			if (element.value == value.byteValue()) {
				return element.desc;
			}
		}
		return "";
	}
}
