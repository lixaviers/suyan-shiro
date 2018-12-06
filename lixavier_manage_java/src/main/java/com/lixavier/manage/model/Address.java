package com.lixavier.manage.model;


import java.util.List;

public class Address {

    public static final String ID = "主键id";
    public static final String PARENT_ID = "父类id";
    public static final String NAME = "名称";
    public static final String ORDER_NUM = "排序";
    public static final String ADDRESS_LEVEL = "层级";


    /**
     * 主键id
     */
    private Integer id;

    /**
     * 父类id
     */
    private Integer parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 层级
     */
    private Byte addressLevel;
    

    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }

    public Integer getParentId(){
        return parentId;
    }
    
    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }

    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public Integer getOrderNum(){
        return orderNum;
    }
    
    public void setOrderNum(Integer orderNum){
        this.orderNum = orderNum;
    }

    public Byte getAddressLevel(){
        return addressLevel;
    }
    
    public void setAddressLevel(Byte addressLevel){
        this.addressLevel = addressLevel;
    }

    /**
    * The following custom property definition
    */

    private List<Address> children;

    public List<Address> getChildren() {
        return children;
    }

    public void setChildren(List<Address> children) {
        this.children = children;
    }
}