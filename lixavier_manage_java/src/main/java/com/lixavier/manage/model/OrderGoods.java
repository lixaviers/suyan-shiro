package com.lixavier.manage.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderGoods {

    public static final String ID = "ID";
    public static final String USER_ID = "用户id";
    public static final String ORDER_ID = "订单id";
    public static final String ORDER_NO = "订单号";
    public static final String GOODS_ID = "商品id";
    public static final String GOODS_NAME = "商品名称";
    public static final String GOODS_NUMBER = "商品数量";
    public static final String GOODS_PRICE = "商品价格";
    public static final String TOTAL_PRICE = "总金额";
    public static final String PROMOTION_PRICE = "促销金额";
    public static final String GIFT_CARD_PRICE = "礼品卡金额";
    public static final String PAY_PRICE = "实付金额";
    public static final String COMMON_STATUS = "状态";
    public static final String PAY_TIME = "支付时间";
    public static final String DELIVERY_TIME = "发货时间";
    public static final String DEAL_TIME = "交易时间";
    public static final String REFUND_TIME = "退款时间";
    public static final String CREATE_TIME = "创建时间";
    public static final String UPDATE_TIME = "更新时间";
    public static final String IS_DELETED = "是否删除：0.未删除，1.已删除";


    /**
     * ID
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品数量
     */
    private Integer goodsNumber;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 总金额
     */
    private BigDecimal totalPrice;

    /**
     * 促销金额
     */
    private BigDecimal promotionPrice;

    /**
     * 礼品卡金额
     */
    private BigDecimal giftCardPrice;

    /**
     * 实付金额
     */
    private BigDecimal payPrice;

    /**
     * 状态
     */
    private Byte commonStatus;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 交易时间
     */
    private Date dealTime;

    /**
     * 退款时间
     */
    private Date refundTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除：0.未删除，1.已删除
     */
    private Boolean isDeleted;
    

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

    public Long getOrderId(){
        return orderId;
    }
    
    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }

    public String getOrderNo(){
        return orderNo;
    }
    
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    public Long getGoodsId(){
        return goodsId;
    }
    
    public void setGoodsId(Long goodsId){
        this.goodsId = goodsId;
    }

    public String getGoodsName(){
        return goodsName;
    }
    
    public void setGoodsName(String goodsName){
        this.goodsName = goodsName;
    }

    public Integer getGoodsNumber(){
        return goodsNumber;
    }
    
    public void setGoodsNumber(Integer goodsNumber){
        this.goodsNumber = goodsNumber;
    }

    public BigDecimal getGoodsPrice(){
        return goodsPrice;
    }
    
    public void setGoodsPrice(BigDecimal goodsPrice){
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getTotalPrice(){
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice){
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPromotionPrice(){
        return promotionPrice;
    }
    
    public void setPromotionPrice(BigDecimal promotionPrice){
        this.promotionPrice = promotionPrice;
    }

    public BigDecimal getGiftCardPrice(){
        return giftCardPrice;
    }
    
    public void setGiftCardPrice(BigDecimal giftCardPrice){
        this.giftCardPrice = giftCardPrice;
    }

    public BigDecimal getPayPrice(){
        return payPrice;
    }
    
    public void setPayPrice(BigDecimal payPrice){
        this.payPrice = payPrice;
    }

    public Byte getCommonStatus(){
        return commonStatus;
    }
    
    public void setCommonStatus(Byte commonStatus){
        this.commonStatus = commonStatus;
    }

    public Date getPayTime(){
        return payTime;
    }
    
    public void setPayTime(Date payTime){
        this.payTime = payTime;
    }

    public Date getDeliveryTime(){
        return deliveryTime;
    }
    
    public void setDeliveryTime(Date deliveryTime){
        this.deliveryTime = deliveryTime;
    }

    public Date getDealTime(){
        return dealTime;
    }
    
    public void setDealTime(Date dealTime){
        this.dealTime = dealTime;
    }

    public Date getRefundTime(){
        return refundTime;
    }
    
    public void setRefundTime(Date refundTime){
        this.refundTime = refundTime;
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

    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}