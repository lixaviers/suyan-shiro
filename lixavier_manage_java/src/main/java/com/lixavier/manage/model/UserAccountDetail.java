package com.lixavier.manage.model;

import java.math.BigDecimal;
import java.util.Date;

public class UserAccountDetail {

    public static final String ID = "ID";
    public static final String USER_ID = "用户id";
    public static final String PRODUCT = "产品";
    public static final String TRANSACTION_TYPE = "交易类型";
    public static final String AMOUNT = "金额";
    public static final String BALANCE = "余额";
    public static final String CHANNEL = "渠道";
    public static final String TYPE = "类型";
    public static final String MEMO = "备注";
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
     * 产品
     */
    private String product;

    /**
     * 交易类型
     */
    private Byte transactionType;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 渠道
     */
    private Byte channel;

    /**
     * 类型
     */
    private Byte type;

    /**
     * 备注
     */
    private String memo;

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

    public String getProduct(){
        return product;
    }
    
    public void setProduct(String product){
        this.product = product;
    }

    public Byte getTransactionType(){
        return transactionType;
    }
    
    public void setTransactionType(Byte transactionType){
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount(){
        return amount;
    }
    
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public BigDecimal getBalance(){
        return balance;
    }
    
    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }

    public Byte getChannel(){
        return channel;
    }
    
    public void setChannel(Byte channel){
        this.channel = channel;
    }

    public Byte getType(){
        return type;
    }
    
    public void setType(Byte type){
        this.type = type;
    }

    public String getMemo(){
        return memo;
    }
    
    public void setMemo(String memo){
        this.memo = memo;
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