package com.lixavier.manage.model;

import java.util.Date;

public class Letter {

    public static final String ID = "ID";
    public static final String TITLE = "标题";
    public static final String TYPE = "类型";
    public static final String CONTENT = "内容";
    public static final String COMMON_STATUS = "状态";
    public static final String SEND_RESULT = "发送结果";
    public static final String CREATE_TIME = "创建时间";
    public static final String UPDATE_TIME = "更新时间";
    public static final String IS_DELETED = "是否删除 0.否 1.是";


    /**
     * ID
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型
     */
    private Byte type;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态
     */
    private Byte commonStatus;

    /**
     * 发送结果
     */
    private String sendResult;

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

    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }

    public Byte getType(){
        return type;
    }
    
    public void setType(Byte type){
        this.type = type;
    }

    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
    }

    public Byte getCommonStatus(){
        return commonStatus;
    }
    
    public void setCommonStatus(Byte commonStatus){
        this.commonStatus = commonStatus;
    }

    public String getSendResult(){
        return sendResult;
    }
    
    public void setSendResult(String sendResult){
        this.sendResult = sendResult;
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