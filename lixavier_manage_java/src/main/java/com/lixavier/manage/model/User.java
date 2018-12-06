package com.lixavier.manage.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class User {

    public static final String ID = "ID";
    public static final String USER_NAME = "用户名";
    public static final String NICK_NAME = "昵称";
    public static final String PASSWORD = "密码";
    public static final String SALT = "盐";
    public static final String EMAIL = "邮箱";
    public static final String MOBILE = "手机号";
    public static final String BALANCE = "余额";
    public static final String COMMON_STATUS = "状态";
    public static final String IS_ADMIN = "是否管理员";
    public static final String CREATE_TIME = "创建时间";
    public static final String UPDATE_TIME = "更新时间";
    public static final String IS_DELETED = "是否删除 0.否 1.是";


    /**
     * ID
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
     * 盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 状态
     */
    private Byte commonStatus;

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

    public String getSalt(){
        return salt;
    }
    
    public void setSalt(String salt){
        this.salt = salt;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Byte getCommonStatus(){
        return commonStatus;
    }
    
    public void setCommonStatus(Byte commonStatus){
        this.commonStatus = commonStatus;
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




    private List<Long> roleIdList;

    /**
     * 新密码
     */
    private String newPassword;

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}