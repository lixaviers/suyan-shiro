package com.lixavier.manage.resp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel("商品")
public class GoodsODTO implements Serializable {

    /**   
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    private Long categoryId;

    /**
     * 品牌id
     */
    @ApiModelProperty("品牌id")
    private Long brandId;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 列表价
     */
    @ApiModelProperty("列表价")
    private BigDecimal price;

    /**
     * 库存
     */
    @ApiModelProperty("库存")
    private Integer inventory;

    /**
     * 剩余库存
     */
    @ApiModelProperty("剩余库存")
    private Integer remainingInventory;

    /**
     * 图片地址
     */
    @ApiModelProperty("图片地址")
    private String pictureUrl;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Byte commonStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 是否删除：0.未删除，1.已删除
     */
    @ApiModelProperty("是否删除：0.未删除，1.已删除")
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
    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("状态描述")
    private String statusDesc;
    /**
     * 购物车数量
     */
    private Integer number;

    private List<Long> categoryIds;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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