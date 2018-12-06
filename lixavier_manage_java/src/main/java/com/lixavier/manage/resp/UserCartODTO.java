package com.lixavier.manage.resp;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel("用户购物车")
public class UserCartODTO implements Serializable {

    /**   
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;


    /**
     * 
     */
    @ApiModelProperty("")
    private Long id;

    /**
     * 
     */
    @ApiModelProperty("")
    private Long userId;

    /**
     * 
     */
    @ApiModelProperty("")
    private Long goodsId;

    /**
     * 
     */
    @ApiModelProperty("")
    private Integer number;

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
     * 是否删除 0.否 1.是
     */
    @ApiModelProperty("是否删除 0.否 1.是")
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