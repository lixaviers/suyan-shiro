package com.lixavier.manage.req;

import java.io.Serializable;
import java.util.Date;

public class CategoryDTO implements Serializable {
    /**
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;

            
    /**
     * ID
     */
    private Long id;
            
    /**
     * 父类id
     */
    private Long parentId;
            
    /**
     * 名称
     */
    private String name;
            
    /**
     * 排序
     */
    private Integer orderNum;
            
    /**
     * 创建时间
     */
    private Date createTime;
            
    /**
     * 更新时间
     */
    private Date updateTime;
            
    /**
     * 是否删除：0.未删除，1.已删除
     */
    private Boolean isDeleted;
        
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public Long getParentId(){
        return parentId;
    }
    
    public void setParentId(Long parentId){
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
    
    public Date getCreateTime(){
        return createTime;
    }
    
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    
    public Date getUpdateTime(){
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    
    public Boolean getIsDeleted(){
        return isDeleted;
    }
    
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    
    /**
    * The following custom property definition
    */
}