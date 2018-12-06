package com.lixavier.manage.resp;

public class EnumODTO {
    private Byte value;
    private String desc;
    private Integer count;

    public EnumODTO() {
    }

    public EnumODTO(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
        this.count = 0;
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
