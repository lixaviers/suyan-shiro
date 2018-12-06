package com.lixavier.manage.vo;

import java.io.Serializable;
import java.util.Date;

public class UserVO implements Serializable {
    /**
     * serialVersionUID
     */ 
    private static final long serialVersionUID = 1L;

            
    /**
     * 
     */
    private Long id;
            
    /**
     * 用户名
     */
    private String userName;
            
    /**
     * 昵称
     */
    private String nickName;
            
    /**
     * 密码
     */
    private String password;
            
    /**
     * 邮箱
     */
    private String email;
            
    /**
     * 手机号
     */
    private String mobile;
            
    /**
     * 状态
     */
    private Byte status;
            
    /**
     * 是否管理员
     */
    private Boolean isAdmin;
            
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
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getMobile(){
        return mobile;
    }
    
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    
    public Byte getStatus(){
        return status;
    }
    
    public void setStatus(Byte status){
        this.status = status;
    }
    
    public Boolean getIsAdmin(){
        return isAdmin;
    }
    
    public void setIsAdmin(Boolean isAdmin){
        this.isAdmin = isAdmin;
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