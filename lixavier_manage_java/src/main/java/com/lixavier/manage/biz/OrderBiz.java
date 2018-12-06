package com.lixavier.manage.biz;

import com.lixavier.manage.async.UserCartAsync;
import com.lixavier.manage.constant.*;
import com.lixavier.manage.dao.GoodsMapper;
import com.lixavier.manage.dao.OrderMapper;
import com.lixavier.manage.exception.CommonBizException;
import com.lixavier.manage.model.*;
import com.lixavier.manage.req.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.util.CodeGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层订单管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
@Service("orderBiz")
public class OrderBiz {
    private final Logger logger = LoggerFactory.getLogger(OrderBiz.class);

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    private UserAddressBiz userAddressBiz;
    @Autowired
    private GoodsBiz goodsBiz;
    @Autowired
    private OrderGoodsBiz orderGoodsBiz;
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private GiftCardBiz giftCardBiz;
    @Autowired
    private UserAccountDetailBiz userAccountDetailBiz;

    @Autowired
    private UserCartAsync userCartAsync;

    /**
     * 逻辑删除订单
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteOrder(Long id) {
        // TODO: Describe business logic and implement it
        int result = orderMapper.logicalDeleteByPrimaryKey(id);
        return result;
    }

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public String createOrder(OrderDTO orderDTO, Long userId) {

        // 收货地址
        UserAddress userAddress = userAddressBiz.getUserAddress(orderDTO.getUserAddressId());

        // 商品
        List<Long> goodsIdList = orderDTO.getGoodsList().stream().map(OrderGoodsDTO::getGoodsId).collect(Collectors.toList());
        Map<Long, Goods> goodsMap = goodsBiz.getGoodsByIdList(goodsIdList);

        for (OrderGoodsDTO goodsDTO : orderDTO.getGoodsList()) {
            // 扣减库存
            int result = goodsMapper.deductingInventory(goodsDTO.getGoodsId(), goodsDTO.getGoodsNumber());
            if (result < 1) {
                throw new CommonBizException(ResultCode.LACK_OF_DATA, "商品" + goodsDTO.getGoodsId() + "库存");
            }

            goodsMap.get(goodsDTO.getGoodsId()).setNumber(goodsDTO.getGoodsNumber());
        }

        Order order = new Order();
        // 订单号
        order.setOrderNo(Constants.MALL_ORDER_PREFIX + CodeGenerator.generateOrderNo());
        order.setUserId(userId);
        // 代付款
        order.setOrderStatus(OrderStatusEnum.TO_BE_PAID.getValue());
        // 收货信息
        order.setConsignee(userAddress.getConsignee());
        order.setMobile(userAddress.getMobile());
        order.setAddress(userAddress.getProvinceName() + " " + userAddress.getCityName() + " " + userAddress.getAreaName() + " " + userAddress.getAddress());
        orderMapper.insertSelective(order);


        // 总金额(不包含运费)
        BigDecimal totalPrice = BigDecimal.ZERO;
        // 促销金额
        BigDecimal promotionPrice = BigDecimal.ZERO;

        for (Goods goods : goodsMap.values()) {

            // 商品总价(不包含运费)
            BigDecimal goodsPrice = goods.getPrice().multiply(new BigDecimal(goods.getNumber()));

            OrderGoods orderGoods = new OrderGoods();
            // 代付款
            orderGoods.setCommonStatus(OrderStatusEnum.TO_BE_PAID.getValue());
            orderGoods.setUserId(userId);
            orderGoods.setOrderId(order.getId());
            orderGoods.setOrderNo(order.getOrderNo());
            // 商品信息
            orderGoods.setGoodsId(goods.getId());
            orderGoods.setGoodsName(goods.getName());
            orderGoods.setGoodsNumber(goods.getNumber());
            orderGoods.setGoodsPrice(goods.getPrice());
            // 总金额
            orderGoods.setTotalPrice(goodsPrice);
            // TODO 促销
            orderGoods.setPromotionPrice(BigDecimal.ZERO);

            totalPrice = totalPrice.add(goodsPrice);

            orderGoodsBiz.createOrderGoods(orderGoods);

        }

        order.setTotalPrice(totalPrice);
        order.setPromotionPrice(promotionPrice);
        // 运费金额
        BigDecimal freightPriceTotal = BigDecimal.ZERO;
        if (totalPrice.compareTo(Constants.FREE_FREIGHT) < 0) {
            // 总金额不满免运费金额，支付运费金额
            freightPriceTotal = Constants.FREIGHT_AMOUNT;
        }
        order.setFreightPrice(freightPriceTotal);

        orderMapper.updateByPrimaryKeySelective(order);

        // 异步删除购物车的商品
        if (orderDTO.getFlag()) {
            userCartAsync.createGiftCardLetter(orderDTO.getGoodsList(), userId);
        }

        return order.getOrderNo();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Boolean payOrder(OrderPayDTO orderPayDTO, Long userId) {
        Order order = orderMapper.selectByOrderNo(orderPayDTO.getOrderNo());
        if (order == null || order.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "订单");
        }
        if (!order.getUserId().equals(userId)) {
            throw new CommonBizException(ResultCode.NO_PERMISSION);
        }
        if (!OrderStatusEnum.TO_BE_PAID.equal(order.getOrderStatus())) {
            throw new CommonBizException(ResultCode.COMMON_MESSAGE, "此订单不需要支付");
        }
        // 总价格
        BigDecimal totalPrice = order.getTotalPrice();
        // 礼品金额
        BigDecimal giftCardTotalPrice = BigDecimal.ZERO;

        List<GiftCard> giftCardList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(orderPayDTO.getGiftCardList())) {
            giftCardList = giftCardBiz.getGiftCard(orderPayDTO.getGiftCardList(), userId);

            for (GiftCard card : giftCardList) {
                giftCardTotalPrice = giftCardTotalPrice.add(card.getPrice());
            }
            if (totalPrice.compareTo(giftCardTotalPrice) < 0) {
                throw new CommonBizException(ResultCode.COMMON_MESSAGE, "礼品卡总金额超过了需要支付的金额");
            }
        }
        // 实付金额
        BigDecimal realPayPrice = totalPrice.subtract(giftCardTotalPrice).add(order.getFreightPrice());

        // 账户余额
        BigDecimal balance = userBiz.pay(userId, realPayPrice);

        // 支付成功
        Date nowDate = new Date();
        order.setPayTime(nowDate);
        order.setOrderStatus(OrderStatusEnum.PAID.getValue());
        order.setGiftCardPrice(giftCardTotalPrice);
        order.setRealPayPrice(realPayPrice);

        updateOrder(order);

        // 更新订单促销
        List<OrderGoods> orderGoodsList = orderGoodsBiz.getOrderGoodsByOrderId(order.getId());
        if (CollectionUtils.isNotEmpty(orderGoodsList)) {
            // 拆分礼品卡金额
            BigDecimal breakGiftCardPrice = giftCardTotalPrice;
            // 拆分实际支付金额(不包含运费)
            BigDecimal goodsToalRealPayPrice = realPayPrice.subtract(order.getFreightPrice());
            BigDecimal breakGoodsToalRealPayPrice = goodsToalRealPayPrice;
            for (int i = 0; i < orderGoodsList.size(); i++) {
                OrderGoods orderGoods = orderGoodsList.get(i);
                orderGoods.setCommonStatus(OrderStatusEnum.PAID.getValue());
                orderGoods.setPayTime(nowDate);
                // 拆分优惠(运费不参与)

                // 礼品卡金额
                if (giftCardTotalPrice.compareTo(BigDecimal.ZERO) > 0) {
                    if (i == orderGoodsList.size() - 1) {
                        // 最后一个用剩余金额
                        orderGoods.setGiftCardPrice(breakGiftCardPrice);
                    } else {
                        BigDecimal divide = orderGoods.getTotalPrice().divide(totalPrice, 2, BigDecimal.ROUND_HALF_UP);
                        BigDecimal goodsGiftCardPrice = divide.multiply(giftCardTotalPrice);
                        orderGoods.setGiftCardPrice(goodsGiftCardPrice);
                        breakGiftCardPrice = breakGiftCardPrice.subtract(goodsGiftCardPrice);
                    }
                }
                if (i == orderGoodsList.size() - 1) {
                    // 最后一个用剩余金额
                    orderGoods.setPayPrice(breakGoodsToalRealPayPrice);
                } else {
                    BigDecimal goodsRealPayPrice = orderGoods.getTotalPrice().divide(totalPrice, 2, BigDecimal.ROUND_HALF_UP).multiply(goodsToalRealPayPrice);
                    orderGoods.setPayPrice(goodsRealPayPrice);
                    breakGoodsToalRealPayPrice = breakGoodsToalRealPayPrice.subtract(goodsRealPayPrice);
                }
                orderGoodsBiz.updateOrderGoods(orderGoods);
            }
        }

        if (CollectionUtils.isNotEmpty(giftCardList)) {
            // 核销用户礼品卡
            giftCardBiz.useTheGiftCardAfterVerification(giftCardList, order.getOrderNo());
        }

        // 记录账户消费明细
        UserAccountDetail userAccountDetail = new UserAccountDetail();
        userAccountDetail.setUserId(userId);
        userAccountDetail.setTransactionType(TransactionTypeEnum.CONSUMPTION.getValue());
        userAccountDetail.setAmount(realPayPrice);
        userAccountDetail.setBalance(balance);
        userAccountDetail.setChannel(ChannelEnum.ACCOUNT_BALANCE.getValue());
        userAccountDetail.setType(AccountDetailTypeEnum.CASH.getValue());
        userAccountDetailBiz.createUserAccountDetail(userAccountDetail);

        return true;
    }

    /**
     * 更新订单
     *
     * @param order
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer updateOrder(Order order) {
        Integer result = null;
        Order orderLast = orderMapper.selectByPrimaryKeyForUpdate(order.getId());
        if (orderLast == null || orderLast.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "订单商品");
        }
        order.setIsDeleted(null);
        order.setCreateTime(null);
        order.setUpdateTime(null);
        result = orderMapper.updateByPrimaryKeySelective(order);
        return result;
    }

    /**
     * 根据ID获取订单信息
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public Order getOrder(Long id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        if (order == null || order.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "订单");
        }
        return order;
    }

    @Transactional(readOnly = true)
    public Order getOrder(String orderNo) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (order == null || order.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "订单");
        }
        return order;
    }

    /**
     * 分页查询订单信息
     *
     * @param orderQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<Order> queryOrder(OrderQueryDTO orderQuery) {
        QueryResultODTO<Order> queryResult = new QueryResultODTO<Order>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(orderQuery.getPageNo(), orderQuery.getPageSize());
        List<Order> orderList = orderMapper.queryOrder(orderQuery);
        PageInfo<Order> pageInfo = new PageInfo<Order>(orderList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(orderList);
        return queryResult;
    }

}