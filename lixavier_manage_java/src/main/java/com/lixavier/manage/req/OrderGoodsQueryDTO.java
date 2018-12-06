package com.lixavier.manage.req;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderGoodsQueryDTO extends QueryDTO {
    
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
    private Long orderId;
            
    public Long getOrderId(){
        return orderId;
    }
    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }
    private List<Long> orderIdList;

    public List<Long> getOrderIdList(){
        return orderIdList;
    }
    public void setOrderIdList(List<Long> orderIdList){
        this.orderIdList = orderIdList;
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
    private String goodsName;
            
    public String getGoodsName(){
        return goodsName;
    }
    public void setGoodsName(String goodsName){
        this.goodsName = goodsName;
    }
    private List<String> goodsNameList;

    public List<String> getGoodsNameList(){
        return goodsNameList;
    }
    public void setGoodsNameList(List<String> goodsNameList){
        this.goodsNameList = goodsNameList;
    }
    private String goodsNameLike;

    public String getGoodsNameLike(){
        return goodsNameLike;
    }
    public void setGoodsNameLike(String goodsNameLike){
        this.goodsNameLike = goodsNameLike;
    }
    private Integer goodsNumber;
            
    public Integer getGoodsNumber(){
        return goodsNumber;
    }
    public void setGoodsNumber(Integer goodsNumber){
        this.goodsNumber = goodsNumber;
    }
    private List<Integer> goodsNumberList;

    public List<Integer> getGoodsNumberList(){
        return goodsNumberList;
    }
    public void setGoodsNumberList(List<Integer> goodsNumberList){
        this.goodsNumberList = goodsNumberList;
    }
    private BigDecimal goodsPriceFrom;

    public BigDecimal getGoodsPriceFrom(){
        return goodsPriceFrom;
    }
    public void setGoodsPriceFrom(BigDecimal goodsPriceFrom){
        this.goodsPriceFrom = goodsPriceFrom;
    }
            
    private BigDecimal goodsPriceTo;

    public BigDecimal getGoodsPriceTo(){
        return goodsPriceTo;
    }
    public void setGoodsPriceTo(BigDecimal goodsPriceTo){
        this.goodsPriceTo = goodsPriceTo;
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
    private BigDecimal payPriceFrom;

    public BigDecimal getPayPriceFrom(){
        return payPriceFrom;
    }
    public void setPayPriceFrom(BigDecimal payPriceFrom){
        this.payPriceFrom = payPriceFrom;
    }
            
    private BigDecimal payPriceTo;

    public BigDecimal getPayPriceTo(){
        return payPriceTo;
    }
    public void setPayPriceTo(BigDecimal payPriceTo){
        this.payPriceTo = payPriceTo;
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
    private Date deliveryTimeFrom;

    public Date getDeliveryTimeFrom(){
        return deliveryTimeFrom;
    }
    public void setDeliveryTimeFrom(Date deliveryTimeFrom){
        this.deliveryTimeFrom = deliveryTimeFrom;
    }
            
    private Date deliveryTimeTo;

    public Date getDeliveryTimeTo(){
        return deliveryTimeTo;
    }
    public void setDeliveryTimeTo(Date deliveryTimeTo){
        this.deliveryTimeTo = deliveryTimeTo;
    }
    private Date dealTimeFrom;

    public Date getDealTimeFrom(){
        return dealTimeFrom;
    }
    public void setDealTimeFrom(Date dealTimeFrom){
        this.dealTimeFrom = dealTimeFrom;
    }
            
    private Date dealTimeTo;

    public Date getDealTimeTo(){
        return dealTimeTo;
    }
    public void setDealTimeTo(Date dealTimeTo){
        this.dealTimeTo = dealTimeTo;
    }
    private Date refundTimeFrom;

    public Date getRefundTimeFrom(){
        return refundTimeFrom;
    }
    public void setRefundTimeFrom(Date refundTimeFrom){
        this.refundTimeFrom = refundTimeFrom;
    }
            
    private Date refundTimeTo;

    public Date getRefundTimeTo(){
        return refundTimeTo;
    }
    public void setRefundTimeTo(Date refundTimeTo){
        this.refundTimeTo = refundTimeTo;
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