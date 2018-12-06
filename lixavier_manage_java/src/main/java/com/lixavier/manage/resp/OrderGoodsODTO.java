package com.lixavier.manage.resp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel("订单商品")
public class OrderGoodsODTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private Long orderId;

    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    private String orderNo;

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Long goodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /**
     * 商品数量
     */
    @ApiModelProperty("商品数量")
    private Integer goodsNumber;

    /**
     * 商品价格
     */
    @ApiModelProperty("商品价格")
    private BigDecimal goodsPrice;

    /**
     * 总金额
     */
    @ApiModelProperty("总金额")
    private BigDecimal totalPrice;

    /**
     * 促销金额
     */
    @ApiModelProperty("促销金额")
    private BigDecimal promotionPrice;

    /**
     * 礼品卡金额
     */
    @ApiModelProperty("礼品卡金额")
    private BigDecimal giftCardPrice;

    /**
     * 实付金额
     */
    @ApiModelProperty("实付金额")
    private BigDecimal payPrice;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Byte commonStatus;

    /**
     * 支付时间
     */
    @ApiModelProperty("支付时间")
    private Date payTime;

    /**
     * 发货时间
     */
    @ApiModelProperty("发货时间")
    private Date deliveryTime;

    /**
     * 交易时间
     */
    @ApiModelProperty("交易时间")
    private Date dealTime;

    /**
     * 退款时间
     */
    @ApiModelProperty("退款时间")
    private Date refundTime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public BigDecimal getGiftCardPrice() {
        return giftCardPrice;
    }

    public void setGiftCardPrice(BigDecimal giftCardPrice) {
        this.giftCardPrice = giftCardPrice;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public Byte getCommonStatus() {
        return commonStatus;
    }

    public void setCommonStatus(Byte commonStatus) {
        this.commonStatus = commonStatus;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * The following custom property definition
     */


    private OrderODTO order;

    public OrderODTO getOrder() {
        return order;
    }

    public void setOrder(OrderODTO order) {
        this.order = order;
    }

}