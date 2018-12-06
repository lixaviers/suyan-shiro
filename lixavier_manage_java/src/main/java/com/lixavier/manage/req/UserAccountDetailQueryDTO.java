package com.lixavier.manage.req;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class UserAccountDetailQueryDTO extends QueryDTO {
    
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
    private String product;
            
    public String getProduct(){
        return product;
    }
    public void setProduct(String product){
        this.product = product;
    }
    private List<String> productList;

    public List<String> getProductList(){
        return productList;
    }
    public void setProductList(List<String> productList){
        this.productList = productList;
    }
    private String productLike;

    public String getProductLike(){
        return productLike;
    }
    public void setProductLike(String productLike){
        this.productLike = productLike;
    }
    private Byte transactionType;
            
    public Byte getTransactionType(){
        return transactionType;
    }
    public void setTransactionType(Byte transactionType){
        this.transactionType = transactionType;
    }
    private List<Byte> transactionTypeList;

    public List<Byte> getTransactionTypeList(){
        return transactionTypeList;
    }
    public void setTransactionTypeList(List<Byte> transactionTypeList){
        this.transactionTypeList = transactionTypeList;
    }
    private BigDecimal amountFrom;

    public BigDecimal getAmountFrom(){
        return amountFrom;
    }
    public void setAmountFrom(BigDecimal amountFrom){
        this.amountFrom = amountFrom;
    }
            
    private BigDecimal amountTo;

    public BigDecimal getAmountTo(){
        return amountTo;
    }
    public void setAmountTo(BigDecimal amountTo){
        this.amountTo = amountTo;
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
    private Byte channel;
            
    public Byte getChannel(){
        return channel;
    }
    public void setChannel(Byte channel){
        this.channel = channel;
    }
    private List<Byte> channelList;

    public List<Byte> getChannelList(){
        return channelList;
    }
    public void setChannelList(List<Byte> channelList){
        this.channelList = channelList;
    }
    private Byte type;
            
    public Byte getType(){
        return type;
    }
    public void setType(Byte type){
        this.type = type;
    }
    private List<Byte> typeList;

    public List<Byte> getTypeList(){
        return typeList;
    }
    public void setTypeList(List<Byte> typeList){
        this.typeList = typeList;
    }
    private String memo;
            
    public String getMemo(){
        return memo;
    }
    public void setMemo(String memo){
        this.memo = memo;
    }
    private List<String> memoList;

    public List<String> getMemoList(){
        return memoList;
    }
    public void setMemoList(List<String> memoList){
        this.memoList = memoList;
    }
    private String memoLike;

    public String getMemoLike(){
        return memoLike;
    }
    public void setMemoLike(String memoLike){
        this.memoLike = memoLike;
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