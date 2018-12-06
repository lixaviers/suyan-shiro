package com.lixavier.manage.service;

import com.lixavier.manage.req.OrderGoodsDTO;
import com.lixavier.manage.req.OrderGoodsQueryDTO;
import com.lixavier.manage.resp.OrderGoodsODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 订单商品管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public interface IOrderGoodsService {

    /**
     * 删除订单商品
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Integer> deleteOrderGoods(Long id, Long userId);


    Result<Boolean> confirmReceipt(Long id, Long userId);

    Result<Boolean> applyRefund(Long id, Long userId);

    /**
     * 退款
     * @param id
     * @param opt 0.驳回 1.退款
     * @param message 拒绝理由
     * @return
     */
    Result<Boolean> refund(Long id, int opt, String message);

    Result<OrderGoodsODTO> getOrderInfo(Long id, Long userId);

    /**
     * 分页查询订单商品信息
     *
     * @param orderGoodsQueryDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<QueryResultODTO<OrderGoodsODTO>> queryOrderGoods(OrderGoodsQueryDTO orderGoodsQueryDTO);


}