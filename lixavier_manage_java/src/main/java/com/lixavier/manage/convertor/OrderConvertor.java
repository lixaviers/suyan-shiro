package com.lixavier.manage.convertor;

import com.lixavier.manage.model.Order;
import com.lixavier.manage.req.OrderDTO;
import com.lixavier.manage.resp.OrderODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层订单转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public abstract class OrderConvertor {

    private static final BeanCopier beanCopierForOrderODTO = BeanCopier.create(Order.class, OrderODTO.class, false);
    private static final BeanCopier beanCopierForOrder = BeanCopier.create(OrderDTO.class, Order.class, false);

    public static OrderODTO toOrderODTO(Order order) {
        if (order == null) {
            return null;
        }
        OrderODTO orderODTO = new OrderODTO();
        beanCopierForOrderODTO.copy(order, orderODTO, null);
        return orderODTO;
    }

    public static Order toOrder(OrderDTO orderDTO) {
        Order order = new Order();
        beanCopierForOrder.copy(orderDTO, order, null);
        return order;
    }

    public static List<OrderODTO> toOrderODTOList(List<Order> orderList) {
        if (orderList == null || orderList.isEmpty()) {
            return null;
        }
        List<OrderODTO> orderInfoList = new ArrayList<OrderODTO>(orderList.size());
        for (Order order : orderList) {
            orderInfoList.add(toOrderODTO(order));
        }
        return orderInfoList;
    }

    public static List<Order> toOrderList(List<OrderDTO> orderDTOList) {
        if (orderDTOList == null || orderDTOList.isEmpty()) {
            return null;
        }
        List<Order> orderList = new ArrayList<Order>(orderDTOList.size());
        for (OrderDTO orderDTO : orderDTOList) {
            orderList.add(toOrder(orderDTO));
        }
        return orderList;
    }

    public static QueryResultODTO<OrderODTO> toQueryResult(QueryResultODTO<Order> queryResult) {
        QueryResultODTO<OrderODTO> queryResultInfo = new QueryResultODTO<OrderODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toOrderODTOList(queryResult.getRecords()));
        return queryResultInfo;
        }
}