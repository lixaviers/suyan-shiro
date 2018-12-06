package com.lixavier.manage.constant;

public enum GiftCardStatusEnum {

    VALID                            ((byte) 0, "未使用"),
    IS_BINDING                       ((byte) 1, "已绑定"),
    USED                             ((byte) 2, "已使用"),
    INVALID                          ((byte) 3, "已失效"),
    ;

	private byte value;
	private String desc;

	GiftCardStatusEnum(byte value, String desc){
		this.value = value;
		this.desc = desc;
	}

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
    public static boolean exists(Byte status) {
        if (status == null) {
            return false;
        }
        byte s = status.byteValue();
        return exists(s);
    }

    public static boolean exists(byte s) {
        for (GiftCardStatusEnum element : GiftCardStatusEnum.values()) {
            if (element.value == s) {
                return true;
            }
        }
        return false;
    }

    public boolean equal(Byte val) {
        return val == null ? false : val.byteValue() == this.value;
    }


}
