package com.lixavier.manage.model;

import java.util.Date;
import java.util.List;

public class GoodsComment {

    public static final String ID = "ID";
    public static final String USER_ID = "用户id";
    public static final String GOODS_ID = "商品id";
    public static final String IS_ANONYMOUS = "是否匿名";
    public static final String NICK_NAME = "昵称";
    public static final String CONTENT = "评论内容";
    public static final String PIC_URLS = "图片地址列表";
    public static final String IS_REPLY = "是否回复";
    public static final String REPLY_CONTENT = "回复内容";
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
     * 商品id
     */
    private Long goodsId;

    /**
     * 是否匿名
     */
    private Boolean isAnonymous;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 图片地址列表
     */
    private String picUrls;

    /**
     * 是否回复
     */
    private Boolean isReply;

    /**
     * 回复内容
     */
    private String replyContent;

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

    public Long getGoodsId(){
        return goodsId;
    }
    
    public void setGoodsId(Long goodsId){
        this.goodsId = goodsId;
    }

    public Boolean getIsAnonymous(){
        return isAnonymous;
    }
    
    public void setIsAnonymous(Boolean isAnonymous){
        this.isAnonymous = isAnonymous;
    }

    public String getNickName(){
        return nickName;
    }
    
    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
    }

    public String getPicUrls(){
        return picUrls;
    }
    
    public void setPicUrls(String picUrls){
        this.picUrls = picUrls;
    }

    public Boolean getIsReply(){
        return isReply;
    }
    
    public void setIsReply(Boolean isReply){
        this.isReply = isReply;
    }

    public String getReplyContent(){
        return replyContent;
    }
    
    public void setReplyContent(String replyContent){
        this.replyContent = replyContent;
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

    /**
     * 图片地址列表
     */
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

}