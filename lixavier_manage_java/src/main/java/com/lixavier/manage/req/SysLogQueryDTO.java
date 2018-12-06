package com.lixavier.manage.req;

import java.util.Date;
import java.util.List;

public class SysLogQueryDTO extends QueryDTO {
    
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
    private String userName;
            
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    private List<String> userNameList;

    public List<String> getUserNameList(){
        return userNameList;
    }
    public void setUserNameList(List<String> userNameList){
        this.userNameList = userNameList;
    }
    private String userNameLike;

    public String getUserNameLike(){
        return userNameLike;
    }
    public void setUserNameLike(String userNameLike){
        this.userNameLike = userNameLike;
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
    private String operation;
            
    public String getOperation(){
        return operation;
    }
    public void setOperation(String operation){
        this.operation = operation;
    }
    private List<String> operationList;

    public List<String> getOperationList(){
        return operationList;
    }
    public void setOperationList(List<String> operationList){
        this.operationList = operationList;
    }
    private String operationLike;

    public String getOperationLike(){
        return operationLike;
    }
    public void setOperationLike(String operationLike){
        this.operationLike = operationLike;
    }
    private String method;
            
    public String getMethod(){
        return method;
    }
    public void setMethod(String method){
        this.method = method;
    }
    private List<String> methodList;

    public List<String> getMethodList(){
        return methodList;
    }
    public void setMethodList(List<String> methodList){
        this.methodList = methodList;
    }
    private String methodLike;

    public String getMethodLike(){
        return methodLike;
    }
    public void setMethodLike(String methodLike){
        this.methodLike = methodLike;
    }
    private Long timeFrom;

    public Long getTimeFrom(){
        return timeFrom;
    }
    public void setTimeFrom(Long timeFrom){
        this.timeFrom = timeFrom;
    }
            
    private Long timeTo;

    public Long getTimeTo(){
        return timeTo;
    }
    public void setTimeTo(Long timeTo){
        this.timeTo = timeTo;
    }
    private String ip;
            
    public String getIp(){
        return ip;
    }
    public void setIp(String ip){
        this.ip = ip;
    }
    private List<String> ipList;

    public List<String> getIpList(){
        return ipList;
    }
    public void setIpList(List<String> ipList){
        this.ipList = ipList;
    }
    private String ipLike;

    public String getIpLike(){
        return ipLike;
    }
    public void setIpLike(String ipLike){
        this.ipLike = ipLike;
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