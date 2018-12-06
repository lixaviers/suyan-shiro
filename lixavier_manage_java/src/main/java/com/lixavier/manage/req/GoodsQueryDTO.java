package com.lixavier.manage.req;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GoodsQueryDTO extends QueryDTO {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private Long id;
            
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    private List<Long> idList;

    public List<Long> getIdList(){
        return idList;
    }
    public void setIdList(List<Long> idList){
        this.idList = idList;
    }
    private Long categoryId;
            
    public Long getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }
    private List<Long> categoryIdList;

    public List<Long> getCategoryIdList(){
        return categoryIdList;
    }
    public void setCategoryIdList(List<Long> categoryIdList){
        this.categoryIdList = categoryIdList;
    }
    private Long brandId;
            
    public Long getBrandId(){
        return brandId;
    }
    public void setBrandId(Long brandId){
        this.brandId = brandId;
    }
    private List<Long> brandIdList;

    public List<Long> getBrandIdList(){
        return brandIdList;
    }
    public void setBrandIdList(List<Long> brandIdList){
        this.brandIdList = brandIdList;
    }
    private String name;
            
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    private List<String> nameList;

    public List<String> getNameList(){
        return nameList;
    }
    public void setNameList(List<String> nameList){
        this.nameList = nameList;
    }
    private String nameLike;

    public String getNameLike(){
        return nameLike;
    }
    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }
    private BigDecimal priceFrom;

    public BigDecimal getPriceFrom(){
        return priceFrom;
    }
    public void setPriceFrom(BigDecimal priceFrom){
        this.priceFrom = priceFrom;
    }
            
    private BigDecimal priceTo;

    public BigDecimal getPriceTo(){
        return priceTo;
    }
    public void setPriceTo(BigDecimal priceTo){
        this.priceTo = priceTo;
    }
    private Integer inventory;
            
    public Integer getInventory(){
        return inventory;
    }
    public void setInventory(Integer inventory){
        this.inventory = inventory;
    }
    private List<Integer> inventoryList;

    public List<Integer> getInventoryList(){
        return inventoryList;
    }
    public void setInventoryList(List<Integer> inventoryList){
        this.inventoryList = inventoryList;
    }
    private Integer remainingInventory;
            
    public Integer getRemainingInventory(){
        return remainingInventory;
    }
    public void setRemainingInventory(Integer remainingInventory){
        this.remainingInventory = remainingInventory;
    }
    private List<Integer> remainingInventoryList;

    public List<Integer> getRemainingInventoryList(){
        return remainingInventoryList;
    }
    public void setRemainingInventoryList(List<Integer> remainingInventoryList){
        this.remainingInventoryList = remainingInventoryList;
    }
    private String pictureUrl;
            
    public String getPictureUrl(){
        return pictureUrl;
    }
    public void setPictureUrl(String pictureUrl){
        this.pictureUrl = pictureUrl;
    }
    private List<String> pictureUrlList;

    public List<String> getPictureUrlList(){
        return pictureUrlList;
    }
    public void setPictureUrlList(List<String> pictureUrlList){
        this.pictureUrlList = pictureUrlList;
    }
    private String pictureUrlLike;

    public String getPictureUrlLike(){
        return pictureUrlLike;
    }
    public void setPictureUrlLike(String pictureUrlLike){
        this.pictureUrlLike = pictureUrlLike;
    }
    private Byte commonStatus;
            
    public Byte getCommonStatus(){
        return commonStatus;
    }
    public void setCommonStatus(Byte commonStatus){
        this.commonStatus = commonStatus;
    }
    private List<Byte> commonStatusList;

    public List<Byte> getCommonStatusList(){
        return commonStatusList;
    }
    public void setCommonStatusList(List<Byte> commonStatusList){
        this.commonStatusList = commonStatusList;
    }
    private Date createTimeFrom;

    public Date getCreateTimeFrom(){
        return createTimeFrom;
    }
    public void setCreateTimeFrom(Date createTimeFrom){
        this.createTimeFrom = createTimeFrom;
    }
            
    private Date createTimeTo;

    public Date getCreateTimeTo(){
        return createTimeTo;
    }
    public void setCreateTimeTo(Date createTimeTo){
        this.createTimeTo = createTimeTo;
    }
    private Date updateTimeFrom;

    public Date getUpdateTimeFrom(){
        return updateTimeFrom;
    }
    public void setUpdateTimeFrom(Date updateTimeFrom){
        this.updateTimeFrom = updateTimeFrom;
    }
            
    private Date updateTimeTo;

    public Date getUpdateTimeTo(){
        return updateTimeTo;
    }
    public void setUpdateTimeTo(Date updateTimeTo){
        this.updateTimeTo = updateTimeTo;
    }
    private Boolean isDeleted;
            
    public Boolean getIsDeleted(){
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    private List<Boolean> isDeletedList;

    public List<Boolean> getIsDeletedList(){
        return isDeletedList;
    }
    public void setIsDeletedList(List<Boolean> isDeletedList){
        this.isDeletedList = isDeletedList;
    }

    /**
    * The following custom property definition
    */
}