package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.OrderGoodsBiz;
import com.lixavier.manage.convertor.OrderGoodsConvertor;
import com.lixavier.manage.model.OrderGoods;
import com.lixavier.manage.req.OrderGoodsDTO;
import com.lixavier.manage.req.OrderGoodsQueryDTO;
import com.lixavier.manage.resp.OrderGoodsODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.IOrderGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lixavier.manage.validate.OrderGoodsValidate.validateForCreate;
import static com.lixavier.manage.validate.OrderGoodsValidate.validateForUpdate;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 订单商品管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
@Service("orderGoodsService")
public class OrderGoodsServiceImpl implements IOrderGoodsService {
    private final Logger logger = LoggerFactory.getLogger(OrderGoodsServiceImpl.class);

    @Autowired
    private OrderGoodsBiz orderGoodsBiz;

    /**
     * 删除订单商品
     *
     * @param id
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> deleteOrderGoods(Long id, Long userId) {
        Result<Integer> result = Result.newSuccess();
        result.setDataMap(orderGoodsBiz.deleteOrderGoods(id, userId));
        return result;
    }

    @Override
    public Result<Boolean> confirmReceipt(Long id, Long userId) {
        Result<Boolean> result = Result.newSuccess();
        result.setDataMap(orderGoodsBiz.confirmReceipt(id, userId));
        return result;
    }

    @Override
    public Result<Boolean> applyRefund(Long id, Long userId) {
        Result<Boolean> result = Result.newSuccess();
        result.setDataMap(orderGoodsBiz.applyRefund(id, userId));
        return result;
    }

    @Override
    public Result<Boolean> refund(Long id, int opt, String message) {
        Result<Boolean> result = Result.newSuccess();
        result.setDataMap(orderGoodsBiz.refund(id, opt, message));
        return result;
    }

    @Override
    public Result<OrderGoodsODTO> getOrderInfo(Long id, Long userId) {
        Result<OrderGoodsODTO> result = Result.newSuccess();
        result.setDataMap(OrderGoodsConvertor.toOrderGoodsODTO(orderGoodsBiz.getOrderInfo(id, userId)));
        return result;
    }

    /**
     * 分页查询订单商品信息
     *
     * @param orderGoodsQueryDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<QueryResultODTO<OrderGoodsODTO>> queryOrderGoods(OrderGoodsQueryDTO orderGoodsQueryDTO) {
        Result<QueryResultODTO<OrderGoodsODTO>> result = Result.newSuccess();

        QueryResultODTO<OrderGoods> resultInfo = orderGoodsBiz.queryOrderGoods(orderGoodsQueryDTO);
        result.setDataMap(OrderGoodsConvertor.toQueryResult(resultInfo));

        return result;
    }
}