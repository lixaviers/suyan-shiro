package com.lixavier.manage.model;

import java.util.Date;

public class UserLetter {

    public static final String ID = "ID";
    public static final String USER_ID = "用户id";
    public static final String LETTER_ID = "站内信id";
    public static final String IS_READ = "是否已读";
    public static final String CREATE_TIME = "创建时间";
    public static final String UPDATE_TIME = "更新时间";


    /**
     * ID
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 站内信id
     */
    private Long letterId;

    /**
     * 是否已读
     */
    private Boolean isRead;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
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