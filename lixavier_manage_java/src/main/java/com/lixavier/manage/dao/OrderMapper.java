package com.lixavier.manage.dao;

import com.lixavier.manage.model.Order;
import com.lixavier.manage.model.OrderExample;
import com.lixavier.manage.req.OrderQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderMapper {
    
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int logicalDeleteByPrimaryKey(
            @Param("id") Long id
    );

    Order selectByPrimaryKeyForUpdate(Long id);

    List<Order> queryOrder(OrderQueryDTO orderQuery);

    int insertBatch(@Param("modelList") List<Order> modelList);


    Order selectByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 查询超时未支付的订单(15分钟)
     * @return
     */
    List<Order> getOverduePaymentOrders(
            @Param("orderStatus") Byte orderStatus
    );


}