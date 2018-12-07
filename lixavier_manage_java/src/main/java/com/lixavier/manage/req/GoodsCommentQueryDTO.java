package com.lixavier.manage.req;

import java.util.Date;
import java.util.List;

public class GoodsCommentQueryDTO extends QueryDTO {
    
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
    private Long userId;
            
    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }
    private List<Long> userIdList;

    public List<Long> getUserIdList(){
        return userIdList;
    }
    public void setUserIdList(List<Long> userIdList){
        this.userIdList = userIdList;
    }
    private Long goodsId;
            
    public Long getGoodsId(){
        return goodsId;
    }
    public void setGoodsId(Long goodsId){
        this.goodsId = goodsId;
    }
    private List<Long> goodsIdList;

    public List<Long> getGoodsIdList(){
        return goodsIdList;
    }
    public void setGoodsIdList(List<Long> goodsIdList){
        this.goodsIdList = goodsIdList;
    }
    private Boolean isAnonymous;
            
    public Boolean getIsAnonymous(){
        return isAnonymous;
    }
    public void setIsAnonymous(Boolean isAnonymous){
        this.isAnonymous = isAnonymous;
    }
    private List<Boolean> isAnonymousList;

    public List<Boolean> getIsAnonymousList(){
        return isAnonymousList;
    }
    public void setIsAnonymousList(List<Boolean> isAnonymousList){
        this.isAnonymousList = isAnonymousList;
    }
    private String nickName;
            
    public String getNickName(){
        return nickName;
    }
    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    private List<String> nickNameList;

    public List<String> getNickNameList(){
        return nickNameList;
    }
    public void setNickNameList(List<String> nickNameList){
        this.nickNameList = nickNameList;
    }
    private String nickNameLike;

    public String getNickNameLike(){
        return nickNameLike;
    }
    public void setNickNameLike(String nickNameLike){
        this.nickNameLike = nickNameLike;
    }
    private Boolean isReply;
            
    public Boolean getIsReply(){
        return isReply;
    }
    public void setIsReply(Boolean isReply){
        this.isReply = isReply;
    }
    private List<Boolean> isReplyList;

    public List<Boolean> getIsReplyList(){
        return isReplyList;
    }
    public void setIsReplyList(List<Boolean> isReplyList){
        this.isReplyList = isReplyList;
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

    /**
    * The following custom property definition
    */
}