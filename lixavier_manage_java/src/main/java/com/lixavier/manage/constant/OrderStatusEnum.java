package com.lixavier.manage.constant;

/**
 * 订单状态
 */
public enum OrderStatusEnum {

    TO_BE_PAID                       ((byte) 0, "待支付"),
    PAID                             ((byte) 1, "已支付"),
    CLOSED                           ((byte) 2, "已关闭"),
    TRADE_SUCCESSFULLY               ((byte) 3, "交易成功"),
    COMPLETED                        ((byte) 4, "已完成"),
    REFUND                           ((byte) 5, "退款中"),
    REFUNDED                         ((byte) 6, "已退款"),
    ;

	private byte value;
	private String desc;

	OrderStatusEnum(byte value, String desc){
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
        for (OrderStatusEnum element : OrderStatusEnum.values()) {
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
