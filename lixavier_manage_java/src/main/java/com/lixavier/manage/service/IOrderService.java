package com.lixavier.manage.service;

import com.lixavier.manage.req.OrderDTO;
import com.lixavier.manage.req.OrderGoodsDTO;
import com.lixavier.manage.req.OrderPayDTO;
import com.lixavier.manage.req.OrderQueryDTO;
import com.lixavier.manage.resp.OrderODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 订单管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public interface IOrderService {

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<String> createOrder(OrderDTO orderDTO, Long userId);

    /**
     * 订单支付
     *
     * @param orderDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Boolean> payOrder(OrderPayDTO orderDTO, Long userId);


    /**
     * 更新订单
     *
     * @param orderDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Integer> updateOrder(OrderDTO orderDTO);

    /**
     * 根据ID获取订单信息
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<OrderODTO> getOrder(Long id);

    Result<OrderODTO> getOrder(String orderNo);

    /**
     * 分页查询订单信息
     *
     * @param orderQueryDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<QueryResultODTO<OrderODTO>> queryOrder(OrderQueryDTO orderQueryDTO);
}