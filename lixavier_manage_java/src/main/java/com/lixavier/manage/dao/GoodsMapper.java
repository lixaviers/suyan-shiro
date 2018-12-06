package com.lixavier.manage.dao;

import com.lixavier.manage.model.Goods;
import com.lixavier.manage.model.GoodsExample;
import com.lixavier.manage.req.GoodsQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GoodsMapper {
    
    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    int logicalDeleteByPrimaryKey(
            @Param("id") Long id
    );

    Goods selectByPrimaryKeyForUpdate(Long id);

    List<Goods> queryGoods(GoodsQueryDTO goodsQuery);

    int insertBatch(@Param("modelList") List<Goods> modelList);


    Goods getGoodsById(Long id);

    List<Goods> queryGoodsList(
            @Param("commonStatus") Byte commonStatus,
            @Param("categoryId") Long categoryId
     );

    /**
     * 扣减库存
     * @param id
     * @param number
     * @return
     */
    int deductingInventory(
            @Param("id") Long id,
            @Param("number") Integer number
    );

    /**
     * 释放库存
     * @param id
     * @param number
     * @return
     */
    int releaseInventory(
            @Param("id") Long id,
            @Param("number") Integer number
    );

}