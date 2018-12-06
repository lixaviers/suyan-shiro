package com.lixavier.manage.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Goods {

    public static final String ID = "ID";
    public static final String CATEGORY_ID = "分类id";
    public static final String BRAND_ID = "品牌id";
    public static final String NAME = "名称";
    public static final String PRICE = "列表价";
    public static final String INVENTORY = "库存";
    public static final String REMAINING_INVENTORY = "剩余库存";
    public static final String PICTURE_URL = "图片地址";
    public static final String COMMON_STATUS = "状态";
    public static final String CREATE_TIME = "创建时间";
    public static final String UPDATE_TIME = "更新时间";
    public static final String IS_DELETED = "是否删除：0.未删除，1.已删除";


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
    /**
     * 购物车/订单商品数量
     */
    private Integer number;

    private List<Long> categoryIds;

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}