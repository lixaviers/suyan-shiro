package com.lixavier.manage.constant;

/**
 * @Comments: 站内信状态
 */
public enum LetterStatusEnum {

	DRAFT                   ((byte) 1, "草稿"),
	SENDING                 ((byte) 2, "发送中"),
	SENDED                  ((byte) 3, "已发送"),
	SEND_FAILURE            ((byte) 4, "发送失败"),
	;




	private byte value;
	private String desc;

	LetterStatusEnum(byte value, String desc){
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
        for (LetterStatusEnum element : LetterStatusEnum.values()) {
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
		for (LetterStatusEnum element : LetterStatusEnum.values()) {
			if (element.value == value.byteValue()) {
				return element.desc;
			}
		}
		return "";
	}
}
