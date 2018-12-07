package com.lixavier.manage.req;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GoodsCommentDTO implements Serializable {
    /**
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;

            
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
     * 订单商品id
     */
    private Long orderGoodsId;
            
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
    
    public Long getOrderGoodsId(){
        return orderGoodsId;
    }
    
    public void setOrderGoodsId(Long orderGoodsId){
        this.orderGoodsId = orderGoodsId;
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