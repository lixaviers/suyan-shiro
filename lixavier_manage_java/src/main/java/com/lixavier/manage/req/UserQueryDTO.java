package com.lixavier.manage.req;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class UserQueryDTO extends QueryDTO {
    
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
    private String password;
            
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    private List<String> passwordList;

    public List<String> getPasswordList(){
        return passwordList;
    }
    public void setPasswordList(List<String> passwordList){
        this.passwordList = passwordList;
    }
    private String passwordLike;

    public String getPasswordLike(){
        return passwordLike;
    }
    public void setPasswordLike(String passwordLike){
        this.passwordLike = passwordLike;
    }
    private String salt;
            
    public String getSalt(){
        return salt;
    }
    public void setSalt(String salt){
        this.salt = salt;
    }
    private List<String> saltList;

    public List<String> getSaltList(){
        return saltList;
    }
    public void setSaltList(List<String> saltList){
        this.saltList = saltList;
    }
    private String saltLike;

    public String getSaltLike(){
        return saltLike;
    }
    public void setSaltLike(String saltLike){
        this.saltLike = saltLike;
    }
    private String email;
            
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    private List<String> emailList;

    public List<String> getEmailList(){
        return emailList;
    }
    public void setEmailList(List<String> emailList){
        this.emailList = emailList;
    }
    private String emailLike;

    public String getEmailLike(){
        return emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }
    private String mobile;
            
    public String getMobile(){
        return mobile;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    private List<String> mobileList;

    public List<String> getMobileList(){
        return mobileList;
    }
    public void setMobileList(List<String> mobileList){
        this.mobileList = mobileList;
    }
    private String mobileLike;

    public String getMobileLike(){
        return mobileLike;
    }
    public void setMobileLike(String mobileLike){
        this.mobileLike = mobileLike;
    }
    private BigDecimal balanceFrom;

    public BigDecimal getBalanceFrom(){
        return balanceFrom;
    }
    public void setBalanceFrom(BigDecimal balanceFrom){
        this.balanceFrom = balanceFrom;
    }
            
    private BigDecimal balanceTo;

    public BigDecimal getBalanceTo(){
        return balanceTo;
    }
    public void setBalanceTo(BigDecimal balanceTo){
        this.balanceTo = balanceTo;
    }
    private Byte commonStatus;
            
    public Byte getCommonStatus(){
        return commonStatus;
    }
    public void setCommonStatus(Byte commonStatus){
        this.commonStatus = commonStatus;
    }
    private List<Byte> commonStatusList;

    public List<Byte> getCommonStatusList(){
        return commonStatusList;
    }
    public void setCommonStatusList(List<Byte> commonStatusList){
        this.commonStatusList = commonStatusList;
    }
    private Boolean isAdmin;
            
    public Boolean getIsAdmin(){
        return isAdmin;
    }
    public void setIsAdmin(Boolean isAdmin){
        this.isAdmin = isAdmin;
    }
    private List<Boolean> isAdminList;

    public List<Boolean> getIsAdminList(){
        return isAdminList;
    }
    public void setIsAdminList(List<Boolean> isAdminList){
        this.isAdminList = isAdminList;
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
    private Boolean isDeleted;
            
    public Boolean getIsDeleted(){
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    private List<Boolean> isDeletedList;

    public List<Boolean> getIsDeletedList(){
        return isDeletedList;
    }
    public void setIsDeletedList(List<Boolean> isDeletedList){
        this.isDeletedList = isDeletedList;
    }

    /**
    * The following custom property definition
    */
}