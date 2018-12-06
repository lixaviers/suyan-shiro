package com.lixavier.manage.task;

import com.lixavier.manage.biz.OrderGoodsBiz;
import com.lixavier.manage.constant.OrderStatusEnum;
import com.lixavier.manage.dao.GoodsMapper;
import com.lixavier.manage.dao.OrderMapper;
import com.lixavier.manage.model.Order;
import com.lixavier.manage.model.OrderGoods;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 订单定时任务
 */
@Component
public class OrderTask {
    private final Logger logger = LoggerFactory.getLogger(OrderTask.class);

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrderGoodsBiz orderGoodsBiz;

    /**
     * 关闭超过15分钟未支付的订单
     * 定时任务执行时间较短，并且比较单一，所以使用Scheduled
     * 由于没有数据，这边设成10分钟执行一次
     */
    @Scheduled(fixedRate = 60000)
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void getOverduePaymentOrders() {
        List<Order> orderList = orderMapper.getOverduePaymentOrders(OrderStatusEnum.TO_BE_PAID.getValue());
        if (CollectionUtils.isNotEmpty(orderList)) {
            for (Order order : orderList) {
                orderGoodsBiz.closeOrderGoods(order.getId());

                List<OrderGoods> orderGoodsList = orderGoodsBiz.getOrderGoodsByOrderId(order.getId());
                if (CollectionUtils.isNotEmpty(orderGoodsList)) {
                    // 释放商品库存
                    for (OrderGoods orderGoods : orderGoodsList) {
                        goodsMapper.releaseInventory(orderGoods.getGoodsId(), orderGoods.getGoodsNumber());
                    }
                }

                Order bean = new Order();
                bean.setId(order.getId());
                bean.setOrderStatus(OrderStatusEnum.CLOSED.getValue());
                bean.setCloseTime(new Date());
                orderMapper.updateByPrimaryKeySelective(bean);

                logger.info("订单号{}已关闭", order.getOrderNo());
            }
        }
    }

    // TODO 3天未同意退款，自动退款
    // TODO 付款15天，自动收货 交易成功

}
