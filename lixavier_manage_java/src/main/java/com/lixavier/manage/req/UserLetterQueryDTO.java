package com.lixavier.manage.req;


import java.util.Date;
import java.util.List;

public class UserLetterQueryDTO extends QueryDTO {
    
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
    private Long letterId;
            
    public Long getLetterId(){
        return letterId;
    }
    public void setLetterId(Long letterId){
        this.letterId = letterId;
    }
    private List<Long> letterIdList;

    public List<Long> getLetterIdList(){
        return letterIdList;
    }
    public void setLetterIdList(List<Long> letterIdList){
        this.letterIdList = letterIdList;
    }
    private Boolean isRead;
            
    public Boolean getIsRead(){
        return isRead;
    }
    public void setIsRead(Boolean isRead){
        this.isRead = isRead;
    }
    private List<Boolean> isReadList;

    public List<Boolean> getIsReadList(){
        return isReadList;
    }
    public void setIsReadList(List<Boolean> isReadList){
        this.isReadList = isReadList;
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


    private Byte type;

    public Byte getType(){
        return type;
    }
    public void setType(Byte type){
        this.type = type;
    }


}