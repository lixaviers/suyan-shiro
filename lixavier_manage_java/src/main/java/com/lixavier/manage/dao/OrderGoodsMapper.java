package com.lixavier.manage.dao;

import com.lixavier.manage.model.OrderGoods;
import com.lixavier.manage.model.OrderGoodsExample;
import com.lixavier.manage.req.OrderGoodsQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderGoodsMapper {
    
    long countByExample(OrderGoodsExample example);

    int deleteByExample(OrderGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    List<OrderGoods> selectByExample(OrderGoodsExample example);

    OrderGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderGoods record, @Param("example") OrderGoodsExample example);

    int updateByExample(@Param("record") OrderGoods record, @Param("example") OrderGoodsExample example);

    int updateByPrimaryKeySelective(OrderGoods record);

    int updateByPrimaryKey(OrderGoods record);

    int logicalDeleteByPrimaryKey(
            @Param("id") Long id
    );

    OrderGoods selectByPrimaryKeyForUpdate(Long id);

    List<OrderGoods> queryOrderGoods(OrderGoodsQueryDTO orderGoodsQuery);

    int insertBatch(@Param("modelList") List<OrderGoods> modelList);



}