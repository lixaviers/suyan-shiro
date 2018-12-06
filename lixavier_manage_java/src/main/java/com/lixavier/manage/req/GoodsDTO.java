package com.lixavier.manage.req;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GoodsDTO implements Serializable {
    /**
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;

            
    /**
     * ID
     */
    private Long id;
            
    /**
     * 分类id
     */
    private Long categoryId;
            
    /**
     * 品牌id
     */
    private Long brandId;
            
    /**
     * 名称
     */
    private String name;
            
    /**
     * 列表价
     */
    private BigDecimal price;
            
    /**
     * 库存
     */
    private Integer inventory;
            
    /**
     * 剩余库存
     */
    private Integer remainingInventory;
            
    /**
     * 图片地址
     */
    private String pictureUrl;
            
    /**
     * 状态
     */
    private Byte commonStatus;
            
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
    
    public Long getCategoryId(){
        return categoryId;
    }
    
    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }
    
    public Long getBrandId(){
        return brandId;
    }
    
    public void setBrandId(Long brandId){
        this.brandId = brandId;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public BigDecimal getPrice(){
        return price;
    }
    
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    
    public Integer getInventory(){
        return inventory;
    }
    
    public void setInventory(Integer inventory){
        this.inventory = inventory;
    }
    
    public Integer getRemainingInventory(){
        return remainingInventory;
    }
    
    public void setRemainingInventory(Integer remainingInventory){
        this.remainingInventory = remainingInventory;
    }
    
    public String getPictureUrl(){
        return pictureUrl;
    }
    
    public void setPictureUrl(String pictureUrl){
        this.pictureUrl = pictureUrl;
    }
    
    public Byte getCommonStatus(){
        return commonStatus;
    }
    
    public void setCommonStatus(Byte commonStatus){
        this.commonStatus = commonStatus;
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

    /**
     * 描述
     */
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}