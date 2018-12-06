package com.lixavier.manage.model;

import java.util.Date;

public class UserCart {

    public static final String ID = "ID";
    public static final String USER_ID = "用户id";
    public static final String GOODS_ID = "商品id";
    public static final String NUMBER = "数量";
    public static final String CREATE_TIME = "创建时间";
    public static final String UPDATE_TIME = "更新时间";
    public static final String IS_DELETED = "是否删除 0.否 1.是";


    /**
     * ID
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 数量
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