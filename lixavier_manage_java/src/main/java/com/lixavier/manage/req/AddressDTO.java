package com.lixavier.manage.req;

import java.io.Serializable;

public class AddressDTO implements Serializable {
    /**
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;

            
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
}