package com.lixavier.manage.biz;

import com.lixavier.manage.async.LetterAsync;
import com.lixavier.manage.constant.AccountDetailTypeEnum;
import com.lixavier.manage.constant.ChannelEnum;
import com.lixavier.manage.constant.OrderStatusEnum;
import com.lixavier.manage.constant.TransactionTypeEnum;
import com.lixavier.manage.dao.OrderGoodsMapper;
import com.lixavier.manage.exception.CommonBizException;
import com.lixavier.manage.model.Order;
import com.lixavier.manage.model.OrderGoods;
import com.lixavier.manage.model.OrderGoodsExample;
import com.lixavier.manage.model.User;
import com.lixavier.manage.req.OrderGoodsQueryDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层订单商品管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
@Service("orderGoodsBiz")
public class OrderGoodsBiz {
    private final Logger logger = LoggerFactory.getLogger(OrderGoodsBiz.class);

    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private OrderBiz orderBiz;
    @Autowired
    private UserBiz userBiz;

    @Autowired
    private LetterAsync letterAsync;

    /**
     * 逻辑删除订单商品
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteOrderGoods(Long id, Long userId) {
        OrderGoods orderGoods = getOrderGoodsForUpdate(id);
        if (!userId.equals(orderGoods.getUserId())) {
            throw new CommonBizException(ResultCode.NO_PERMISSION);
        }
        if (!OrderStatusEnum.CLOSED.equal(orderGoods.getCommonStatus())
                && !OrderStatusEnum.COMPLETED.equal(orderGoods.getCommonStatus())
                && !OrderStatusEnum.REFUNDED.equal(orderGoods.getCommonStatus())) {
            // [已关闭/已完成/已退款]才可以删除
            throw new CommonBizException(ResultCode.INVALID_CHANGE_STATUS, "订单商品");
        }
        int result = orderGoodsMapper.logicalDeleteByPrimaryKey(id);
        return result;
    }

    /**
     * 创建订单商品
     *
     * @param orderGoods
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createOrderGoods(OrderGoods orderGoods) {
        orderGoods.setIsDeleted(false);
        orderGoods.setCreateTime(null);
        orderGoods.setUpdateTime(null);
        orderGoodsMapper.insertSelective(orderGoods);
        return orderGoods.getId();
    }

    /**
     * 批量创建
     *
     * @param orderGoodsList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public int batchCreateOrderGoods(List<OrderGoods> orderGoodsList) {
        // TODO: Describe business logic and implement it
        return orderGoodsMapper.insertBatch(orderGoodsList);
    }

    /**
     * 更新订单商品
     *
     * @param orderGoods
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer updateOrderGoods(OrderGoods orderGoods) {
        OrderGoods orderGoodsLast = getOrderGoodsForUpdate(orderGoods.getId());
        orderGoodsLast.setIsDeleted(null);
        orderGoodsLast.setCreateTime(null);
        orderGoodsLast.setUpdateTime(null);
        return orderGoodsMapper.updateByPrimaryKeySelective(orderGoods);
    }

    /**
     * 根据ID获取订单商品信息
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public OrderGoods getOrderGoods(Long id) {
        OrderGoods orderGoods = orderGoodsMapper.selectByPrimaryKey(id);
        if (orderGoods == null || orderGoods.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "订单商品");
        }
        return orderGoods;
    }

    @Transactional(readOnly = true)
    public OrderGoods getOrderGoodsForUpdate(Long id) {
        OrderGoods orderGoodsLast = orderGoodsMapper.selectByPrimaryKeyForUpdate(id);
        if (orderGoodsLast == null || orderGoodsLast.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "订单商品");
        }
        return orderGoodsLast;
    }

    /**
     * 分页查询订单商品信息
     *
     * @param orderGoodsQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<OrderGoods> queryOrderGoods(OrderGoodsQueryDTO orderGoodsQuery) {
        QueryResultODTO<OrderGoods> queryResult = new QueryResultODTO<OrderGoods>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(orderGoodsQuery.getPageNo(), orderGoodsQuery.getPageSize());
        List<OrderGoods> orderGoodsList = orderGoodsMapper.queryOrderGoods(orderGoodsQuery);
        PageInfo<OrderGoods> pageInfo = new PageInfo<OrderGoods>(orderGoodsList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(orderGoodsList);
        return queryResult;
    }


    /**
     * 根据订单id查询订单商品列表
     *
     * @param orderId
     * @return
     */
    @Transactional(readOnly = true)
    public List<OrderGoods> getOrderGoodsByOrderId(Long orderId) {
        OrderGoodsExample example = new OrderGoodsExample();
        example.createCriteria().andIsDeletedEqualTo(false).andOrderIdEqualTo(orderId);
        return orderGoodsMapper.selectByExample(example);
    }

    /**
     * 关闭订单商品
     *
     * @param orderId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer closeOrderGoods(Long orderId) {
        OrderGoodsExample example = new OrderGoodsExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andIsDeletedEqualTo(false);
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setCommonStatus(OrderStatusEnum.CLOSED.getValue());
        return orderGoodsMapper.updateByExampleSelective(orderGoods, example);
    }

    /**
     * 确认收货
     *
     * @param id
     * @param userId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public boolean confirmReceipt(Long id, Long userId) {
        OrderGoods orderGoods = getOrderGoodsForUpdate(id);
        if (!userId.equals(orderGoods.getUserId())) {
            throw new CommonBizException(ResultCode.NO_PERMISSION);
        }
        if (!OrderStatusEnum.PAID.equal(orderGoods.getCommonStatus())) {
            throw new CommonBizException(ResultCode.INVALID_CHANGE_STATUS, "订单商品");
        }
        OrderGoods bean = new OrderGoods();
        bean.setId(id);
        // 交易成功
        bean.setCommonStatus(OrderStatusEnum.TRADE_SUCCESSFULLY.getValue());
        bean.setDealTime(new Date());
        orderGoodsMapper.updateByPrimaryKeySelective(bean);
        return true;
    }

    /**
     * 申请退款
     *
     * @param id
     * @param userId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public boolean applyRefund(Long id, Long userId) {
        OrderGoods orderGoods = getOrderGoodsForUpdate(id);
        if (!userId.equals(orderGoods.getUserId())) {
            throw new CommonBizException(ResultCode.NO_PERMISSION);
        }
        if (!OrderStatusEnum.PAID.equal(orderGoods.getCommonStatus())) {
            throw new CommonBizException(ResultCode.INVALID_CHANGE_STATUS, "订单商品");
        }
        OrderGoods bean = new OrderGoods();
        bean.setId(id);
        // 退款中
        bean.setCommonStatus(OrderStatusEnum.REFUND.getValue());
        orderGoodsMapper.updateByPrimaryKeySelective(bean);
        return true;
    }

    /**
     * 退款
     *
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public boolean refund(Long id, int opt, String message) {
        OrderGoods orderGoods = getOrderGoodsForUpdate(id);
        if (!OrderStatusEnum.REFUND.equal(orderGoods.getCommonStatus())) {
            throw new CommonBizException(ResultCode.INVALID_CHANGE_STATUS, "订单商品");
        }
        if (opt == 1) {
            // 退款
            User user = new User();
            user.setId(orderGoods.getUserId());
            // 实付金额
            user.setBalance(orderGoods.getPayPrice());
            // 退款
            userBiz.addBalance(user, TransactionTypeEnum.REFUND.getValue(), ChannelEnum.ACCOUNT_BALANCE.getValue(),
                    AccountDetailTypeEnum.CASH.getValue());

            OrderGoods bean = new OrderGoods();
            bean.setId(id);
            // 已退款
            bean.setCommonStatus(OrderStatusEnum.REFUNDED.getValue());
            bean.setRefundTime(new Date());
            orderGoodsMapper.updateByPrimaryKeySelective(bean);

            // 异步发送退款消息
            letterAsync.createRefundLetter(orderGoods.getUserId(), orderGoods.getPayPrice());
        } else {
            // 驳回退款
            OrderGoods bean = new OrderGoods();
            bean.setId(id);
            // 已支付
            bean.setCommonStatus(OrderStatusEnum.PAID.getValue());
            bean.setRefundTime(new Date());
            orderGoodsMapper.updateByPrimaryKeySelective(bean);

            // 异步发送拒绝退款消息
            letterAsync.createRefusedRefundLetter(orderGoods.getUserId(), message);
        }

        return true;
    }


    @Transactional(readOnly = true)
    public OrderGoods getOrderInfo(Long id, Long userId) {
        OrderGoods orderGoods = getOrderGoods(id);
        if (!userId.equals(orderGoods.getUserId())) {
            throw new CommonBizException(ResultCode.NO_PERMISSION);
        }
        Order order = orderBiz.getOrder(orderGoods.getOrderId());
        orderGoods.setOrder(order);
        return orderGoods;
    }


}