package com.lixavier.manage.req;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderQueryDTO extends QueryDTO {
    
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
    private String orderNo;
            
    public String getOrderNo(){
        return orderNo;
    }
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }
    private List<String> orderNoList;

    public List<String> getOrderNoList(){
        return orderNoList;
    }
    public void setOrderNoList(List<String> orderNoList){
        this.orderNoList = orderNoList;
    }
    private String orderNoLike;

    public String getOrderNoLike(){
        return orderNoLike;
    }
    public void setOrderNoLike(String orderNoLike){
        this.orderNoLike = orderNoLike;
    }
    private Byte orderStatus;
            
    public Byte getOrderStatus(){
        return orderStatus;
    }
    public void setOrderStatus(Byte orderStatus){
        this.orderStatus = orderStatus;
    }
    private List<Byte> orderStatusList;

    public List<Byte> getOrderStatusList(){
        return orderStatusList;
    }
    public void setOrderStatusList(List<Byte> orderStatusList){
        this.orderStatusList = orderStatusList;
    }
    private String consignee;
            
    public String getConsignee(){
        return consignee;
    }
    public void setConsignee(String consignee){
        this.consignee = consignee;
    }
    private List<String> consigneeList;

    public List<String> getConsigneeList(){
        return consigneeList;
    }
    public void setConsigneeList(List<String> consigneeList){
        this.consigneeList = consigneeList;
    }
    private String consigneeLike;

    public String getConsigneeLike(){
        return consigneeLike;
    }
    public void setConsigneeLike(String consigneeLike){
        this.consigneeLike = consigneeLike;
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
    private String address;
            
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    private List<String> addressList;

    public List<String> getAddressList(){
        return addressList;
    }
    public void setAddressList(List<String> addressList){
        this.addressList = addressList;
    }
    private String addressLike;

    public String getAddressLike(){
        return addressLike;
    }
    public void setAddressLike(String addressLike){
        this.addressLike = addressLike;
    }
    private BigDecimal totalPriceFrom;

    public BigDecimal getTotalPriceFrom(){
        return totalPriceFrom;
    }
    public void setTotalPriceFrom(BigDecimal totalPriceFrom){
        this.totalPriceFrom = totalPriceFrom;
    }
            
    private BigDecimal totalPriceTo;

    public BigDecimal getTotalPriceTo(){
        return totalPriceTo;
    }
    public void setTotalPriceTo(BigDecimal totalPriceTo){
        this.totalPriceTo = totalPriceTo;
    }
    private BigDecimal realPayPriceFrom;

    public BigDecimal getRealPayPriceFrom(){
        return realPayPriceFrom;
    }
    public void setRealPayPriceFrom(BigDecimal realPayPriceFrom){
        this.realPayPriceFrom = realPayPriceFrom;
    }
            
    private BigDecimal realPayPriceTo;

    public BigDecimal getRealPayPriceTo(){
        return realPayPriceTo;
    }
    public void setRealPayPriceTo(BigDecimal realPayPriceTo){
        this.realPayPriceTo = realPayPriceTo;
    }
    private BigDecimal promotionPriceFrom;

    public BigDecimal getPromotionPriceFrom(){
        return promotionPriceFrom;
    }
    public void setPromotionPriceFrom(BigDecimal promotionPriceFrom){
        this.promotionPriceFrom = promotionPriceFrom;
    }
            
    private BigDecimal promotionPriceTo;

    public BigDecimal getPromotionPriceTo(){
        return promotionPriceTo;
    }
    public void setPromotionPriceTo(BigDecimal promotionPriceTo){
        this.promotionPriceTo = promotionPriceTo;
    }
    private BigDecimal freightPriceFrom;

    public BigDecimal getFreightPriceFrom(){
        return freightPriceFrom;
    }
    public void setFreightPriceFrom(BigDecimal freightPriceFrom){
        this.freightPriceFrom = freightPriceFrom;
    }
            
    private BigDecimal freightPriceTo;

    public BigDecimal getFreightPriceTo(){
        return freightPriceTo;
    }
    public void setFreightPriceTo(BigDecimal freightPriceTo){
        this.freightPriceTo = freightPriceTo;
    }
    private BigDecimal giftCardPriceFrom;

    public BigDecimal getGiftCardPriceFrom(){
        return giftCardPriceFrom;
    }
    public void setGiftCardPriceFrom(BigDecimal giftCardPriceFrom){
        this.giftCardPriceFrom = giftCardPriceFrom;
    }
            
    private BigDecimal giftCardPriceTo;

    public BigDecimal getGiftCardPriceTo(){
        return giftCardPriceTo;
    }
    public void setGiftCardPriceTo(BigDecimal giftCardPriceTo){
        this.giftCardPriceTo = giftCardPriceTo;
    }
    private Date payTimeFrom;

    public Date getPayTimeFrom(){
        return payTimeFrom;
    }
    public void setPayTimeFrom(Date payTimeFrom){
        this.payTimeFrom = payTimeFrom;
    }
            
    private Date payTimeTo;

    public Date getPayTimeTo(){
        return payTimeTo;
    }
    public void setPayTimeTo(Date payTimeTo){
        this.payTimeTo = payTimeTo;
    }
    private Date closeTimeFrom;

    public Date getCloseTimeFrom(){
        return closeTimeFrom;
    }
    public void setCloseTimeFrom(Date closeTimeFrom){
        this.closeTimeFrom = closeTimeFrom;
    }
            
    private Date closeTimeTo;

    public Date getCloseTimeTo(){
        return closeTimeTo;
    }
    public void setCloseTimeTo(Date closeTimeTo){
        this.closeTimeTo = closeTimeTo;
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