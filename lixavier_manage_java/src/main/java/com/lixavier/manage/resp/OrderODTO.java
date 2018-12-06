package com.lixavier.manage.resp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel("订单")
public class OrderODTO implements Serializable {

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
     * 订单号
     */
    @ApiModelProperty("订单号")
    private String orderNo;

    /**
     * 订单状态
     */
    @ApiModelProperty("订单状态")
    private Byte orderStatus;

    /**
     * 收货人名称
     */
    @ApiModelProperty("收货人名称")
    private String consignee;

    /**
     * 收货人手机号
     */
    @ApiModelProperty("收货人手机号")
    private String mobile;

    /**
     * 收货具体地址
     */
    @ApiModelProperty("收货具体地址")
    private String address;

    /**
     * 总金额
     */
    @ApiModelProperty("总金额")
    private BigDecimal totalPrice;

    /**
     * 实付金额
     */
    @ApiModelProperty("实付金额")
    private BigDecimal realPayPrice;

    /**
     * 促销金额
     */
    @ApiModelProperty("促销金额")
    private BigDecimal promotionPrice;

    /**
     * 运费金额
     */
    @ApiModelProperty("运费金额")
    private BigDecimal freightPrice;

    /**
     * 礼品卡金额
     */
    @ApiModelProperty("礼品卡金额")
    private BigDecimal giftCardPrice;

    /**
     * 支付时间
     */
    @ApiModelProperty("支付时间")
    private Date payTime;

    /**
     * 订单留言
     */
    @ApiModelProperty("订单留言")
    private String message;

    /**
     * 关闭时间
     */
    @ApiModelProperty("关闭时间")
    private Date closeTime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 是否删除：0.未删除，1.已删除
     */
    @ApiModelProperty("是否删除：0.未删除，1.已删除")
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

    public String getOrderNo(){
        return orderNo;
    }
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    public Byte getOrderStatus(){
        return orderStatus;
    }
    public void setOrderStatus(Byte orderStatus){
        this.orderStatus = orderStatus;
    }

    public String getConsignee(){
        return consignee;
    }
    public void setConsignee(String consignee){
        this.consignee = consignee;
    }

    public String getMobile(){
        return mobile;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public BigDecimal getTotalPrice(){
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice){
        this.totalPrice = totalPrice;
    }

    public BigDecimal getRealPayPrice(){
        return realPayPrice;
    }
    public void setRealPayPrice(BigDecimal realPayPrice){
        this.realPayPrice = realPayPrice;
    }

    public BigDecimal getPromotionPrice(){
        return promotionPrice;
    }
    public void setPromotionPrice(BigDecimal promotionPrice){
        this.promotionPrice = promotionPrice;
    }

    public BigDecimal getFreightPrice(){
        return freightPrice;
    }
    public void setFreightPrice(BigDecimal freightPrice){
        this.freightPrice = freightPrice;
    }

    public BigDecimal getGiftCardPrice(){
        return giftCardPrice;
    }
    public void setGiftCardPrice(BigDecimal giftCardPrice){
        this.giftCardPrice = giftCardPrice;
    }

    public Date getPayTime(){
        return payTime;
    }
    public void setPayTime(Date payTime){
        this.payTime = payTime;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }

    public Date getCloseTime(){
        return closeTime;
    }
    public void setCloseTime(Date closeTime){
        this.closeTime = closeTime;
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