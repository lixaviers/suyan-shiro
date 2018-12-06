package com.lixavier.manage.convertor;

import com.lixavier.manage.model.OrderGoods;
import com.lixavier.manage.req.OrderGoodsDTO;
import com.lixavier.manage.resp.OrderGoodsODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层订单商品转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public abstract class OrderGoodsConvertor {

    private static final BeanCopier beanCopierForOrderGoodsODTO = BeanCopier.create(OrderGoods.class, OrderGoodsODTO.class, false);
    private static final BeanCopier beanCopierForOrderGoods = BeanCopier.create(OrderGoodsDTO.class, OrderGoods.class, false);

    public static OrderGoodsODTO toOrderGoodsODTO(OrderGoods orderGoods) {
        if (orderGoods == null) {
            return null;
        }
        OrderGoodsODTO orderGoodsODTO = new OrderGoodsODTO();
        beanCopierForOrderGoodsODTO.copy(orderGoods, orderGoodsODTO, null);
        orderGoodsODTO.setOrder(OrderConvertor.toOrderODTO(orderGoods.getOrder()));
        return orderGoodsODTO;
    }

    public static OrderGoods toOrderGoods(OrderGoodsDTO orderGoodsDTO) {
        OrderGoods orderGoods = new OrderGoods();
        beanCopierForOrderGoods.copy(orderGoodsDTO, orderGoods, null);
        return orderGoods;
    }

    public static List<OrderGoodsODTO> toOrderGoodsODTOList(List<OrderGoods> orderGoodsList) {
        if (orderGoodsList == null || orderGoodsList.isEmpty()) {
            return null;
        }
        List<OrderGoodsODTO> orderGoodsInfoList = new ArrayList<OrderGoodsODTO>(orderGoodsList.size());
        for (OrderGoods orderGoods : orderGoodsList) {
            orderGoodsInfoList.add(toOrderGoodsODTO(orderGoods));
        }
        return orderGoodsInfoList;
    }

    public static List<OrderGoods> toOrderGoodsList(List<OrderGoodsDTO> orderGoodsDTOList) {
        if (orderGoodsDTOList == null || orderGoodsDTOList.isEmpty()) {
            return null;
        }
        List<OrderGoods> orderGoodsList = new ArrayList<OrderGoods>(orderGoodsDTOList.size());
        for (OrderGoodsDTO orderGoodsDTO : orderGoodsDTOList) {
            orderGoodsList.add(toOrderGoods(orderGoodsDTO));
        }
        return orderGoodsList;
    }

    public static QueryResultODTO<OrderGoodsODTO> toQueryResult(QueryResultODTO<OrderGoods> queryResult) {
        QueryResultODTO<OrderGoodsODTO> queryResultInfo = new QueryResultODTO<OrderGoodsODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toOrderGoodsODTOList(queryResult.getRecords()));
        return queryResultInfo;
    }
}