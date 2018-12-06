package com.lixavier.manage.req;

import java.io.Serializable;
import java.util.Date;

public class UserCartDTO implements Serializable {
    /**
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;

            
    /**
     * 
     */
    private Long id;
            
    /**
     * 
     */
    private Long userId;
            
    /**
     * 
     */
    private Long goodsId;
            
    /**
     * 
     */
    private Integer number;
            
    /**
     * 创建时间
     */
    private Date createTime;
            
    /**
     * 更新时间
     */
    private Date updateTime;
            
    /**
     * 是否删除 0.否 1.是
     */
    private Boolean isDeleted;
        
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public Long getUserId(){
        return userId;
    }
    
    public void setUserId(Long userId){
        this.userId = userId;
    }
    
    public Long getGoodsId(){
        return goodsId;
    }
    
    public void setGoodsId(Long goodsId){
        this.goodsId = goodsId;
    }
    
    public Integer getNumber(){
        return number;
    }
    
    public void setNumber(Integer number){
        this.number = number;
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