package com.lixavier.manage.model;

import java.util.Date;

public class SysLog {

    public static final String ID = "ID";
    public static final String USER_ID = "用户id";
    public static final String USER_NAME = "用户名";
    public static final String NICK_NAME = "昵称";
    public static final String OPERATION = "用户操作";
    public static final String METHOD = "请求方法";
    public static final String PARAMS = "请求参数";
    public static final String TIME = "执行时长(毫秒)";
    public static final String IP = "IP地址";
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
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 执行时长(毫秒)
     */
    private Long time;

    /**
     * IP地址
     */
    private String ip;

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

    public String getUserName(){
        return userName;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getNickName(){
        return nickName;
    }
    
    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public String getOperation(){
        return operation;
    }
    
    public void setOperation(String operation){
        this.operation = operation;
    }

    public String getMethod(){
        return method;
    }
    
    public void setMethod(String method){
        this.method = method;
    }

    public String getParams(){
        return params;
    }
    
    public void setParams(String params){
        this.params = params;
    }

    public Long getTime(){
        return time;
    }
    
    public void setTime(Long time){
        this.time = time;
    }

    public String getIp(){
        return ip;
    }
    
    public void setIp(String ip){
        this.ip = ip;
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