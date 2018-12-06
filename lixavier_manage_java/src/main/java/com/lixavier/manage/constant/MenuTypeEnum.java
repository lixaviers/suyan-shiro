package com.lixavier.manage.constant;

/**
 * @Comments: 资源类型
 */
public enum MenuTypeEnum {
	MENU                    ((byte) 0, "菜单"),
	BUTTON                  ((byte) 1, "按钮"),
	API                     ((byte) 2, "接口"),
	;

	private byte value;
	private String desc;

	MenuTypeEnum(byte value, String desc){
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
        for (MenuTypeEnum element : MenuTypeEnum.values()) {
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
		for (MenuTypeEnum element : MenuTypeEnum.values()) {
			if (element.value == value.byteValue()) {
				return element.desc;
			}
		}
		return "";
	}
}
