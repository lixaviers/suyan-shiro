package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.OrderBiz;
import com.lixavier.manage.convertor.OrderConvertor;
import com.lixavier.manage.model.Order;
import com.lixavier.manage.req.OrderDTO;
import com.lixavier.manage.req.OrderPayDTO;
import com.lixavier.manage.req.OrderQueryDTO;
import com.lixavier.manage.resp.OrderODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lixavier.manage.validate.OrderValidate.validateForCreate;
import static com.lixavier.manage.validate.OrderValidate.validateForPay;
import static com.lixavier.manage.validate.OrderValidate.validateForUpdate;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 订单管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderBiz orderBiz;

    /**
     * 删除订单
     *
     * @param id
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> deleteOrder(Long id) {
        Result<Integer> result = Result.newSuccess();
        result.setDataMap(orderBiz.deleteOrder(id));
        return result;
    }

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<String> createOrder(OrderDTO orderDTO, Long userId) {
        Result<String> result = Result.newSuccess();
        if (!validateForCreate(orderDTO, result)) {
            return result;
        }
        result.setDataMap(orderBiz.createOrder(orderDTO, userId));
        return result;
    }

    @Override
    public Result<Boolean> payOrder(OrderPayDTO orderPayDTO, Long userId) {
        Result<Boolean> result = Result.newSuccess();
        if (!validateForPay(orderPayDTO, result)) {
            return result;
        }
        result.setDataMap(orderBiz.payOrder(orderPayDTO, userId));
        return result;
    }

    /**
     * 更新订单
     *
     * @param orderDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> updateOrder(OrderDTO orderDTO) {
        Result<Integer> result = Result.newSuccess();
        Order order = null;
        order = OrderConvertor.toOrder(orderDTO);
        if (!validateForUpdate(order, result)) {
            return result;
        }
        result.setDataMap(orderBiz.updateOrder(order));
        return result;
    }

    /**
     * 根据ID获取订单信息
     *
     * @param id
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<OrderODTO> getOrder(Long id) {
        Result<OrderODTO> result = Result.newSuccess();
        Order order = orderBiz.getOrder(id);
        OrderODTO orderODTO = OrderConvertor.toOrderODTO(order);
        result.setDataMap(orderODTO);
        return result;
    }

    @Override
    public Result<OrderODTO> getOrder(String orderNo) {
        Result<OrderODTO> result = Result.newSuccess();
        result.setDataMap(OrderConvertor.toOrderODTO(orderBiz.getOrder(orderNo)));
        return result;
    }

    /**
     * 分页查询订单信息
     *
     * @param orderQueryDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<QueryResultODTO<OrderODTO>> queryOrder(OrderQueryDTO orderQueryDTO) {
        Result<QueryResultODTO<OrderODTO>> result = Result.newSuccess();

        QueryResultODTO<Order> resultInfo = orderBiz.queryOrder(orderQueryDTO);
        result.setDataMap(OrderConvertor.toQueryResult(resultInfo));

        return result;
    }
}