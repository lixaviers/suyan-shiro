package com.lixavier.manage.constant;

/**
 * @Comments: 站内信类型
 */
public enum LetterTypeEnum {

	ANNOUNCEMENT              ((byte) 1, "公告"),
	PRODUCT_SERVICE           ((byte) 2, "产品服务"),
	SAFETY                    ((byte) 3, "安全消息"),
	ACCOUNT                   ((byte) 4, "帐户信息"),
	DYNAMIC                   ((byte) 5, "动态"),
	REPAIR_ORDER              ((byte) 6, "工单信息"),
	APPLY                     ((byte) 7, "申请结果"),

	OTHER                     ((byte) 20, "其它"),
	;




	private byte value;
	private String desc;

	LetterTypeEnum(byte value, String desc){
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
        for (LetterTypeEnum element : LetterTypeEnum.values()) {
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
		for (LetterTypeEnum element : LetterTypeEnum.values()) {
			if (element.value == value.byteValue()) {
				return element.desc;
			}
		}
		return "";
	}
}
