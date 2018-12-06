package com.lixavier.manage.resp;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel("用户站内信")
public class UserLetterODTO implements Serializable {

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
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 站内信id
     */
    @ApiModelProperty("站内信id")
    private Long letterId;

    /**
     * 是否已读
     */
    @ApiModelProperty("是否已读")
    private Boolean isRead;

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

    public Long getLetterId(){
        return letterId;
    }
    public void setLetterId(Long letterId){
        this.letterId = letterId;
    }

    public Boolean getIsRead(){
        return isRead;
    }
    public void setIsRead(Boolean isRead){
        this.isRead = isRead;
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

    /**
    * The following custom property definition
    */
}