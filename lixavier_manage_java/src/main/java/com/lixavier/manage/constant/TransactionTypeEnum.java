package com.lixavier.manage.constant;

/**
 * @Comments: 交易类型
 */
public enum TransactionTypeEnum {

	CONSUMPTION              ((byte) 1, "消费"),
	TOP_UP                   ((byte) 2, "充值"),
	REFUND                   ((byte) 3, "退款"),
	;




	private byte value;
	private String desc;

	TransactionTypeEnum(byte value, String desc){
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
        for (TransactionTypeEnum element : TransactionTypeEnum.values()) {
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
		for (TransactionTypeEnum element : TransactionTypeEnum.values()) {
			if (element.value == value.byteValue()) {
				return element.desc;
			}
		}
		return "";
	}
}
