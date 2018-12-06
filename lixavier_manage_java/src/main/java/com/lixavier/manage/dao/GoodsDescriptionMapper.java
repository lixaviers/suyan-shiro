package com.lixavier.manage.dao;

import com.lixavier.manage.model.GoodsDescription;
import com.lixavier.manage.model.GoodsDescriptionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsDescriptionMapper {
    
    long countByExample(GoodsDescriptionExample example);

    int deleteByExample(GoodsDescriptionExample example);

    int deleteByPrimaryKey(Long goodsId);

    int insert(GoodsDescription record);

    int insertSelective(GoodsDescription record);

    List<GoodsDescription> selectByExampleWithBLOBs(GoodsDescriptionExample example);

    List<GoodsDescription> selectByExample(GoodsDescriptionExample example);

    GoodsDescription selectByPrimaryKey(Long goodsId);

    int updateByExampleSelective(@Param("record") GoodsDescription record, @Param("example") GoodsDescriptionExample example);

    int updateByExampleWithBLOBs(@Param("record") GoodsDescription record, @Param("example") GoodsDescriptionExample example);

    int updateByExample(@Param("record") GoodsDescription record, @Param("example") GoodsDescriptionExample example);

    int updateByPrimaryKey(GoodsDescription record);

    int updateByPrimaryKeySelective(GoodsDescription record);

    int updateByPrimaryKeyWithBLOBs(GoodsDescription record);


    GoodsDescription selectByPrimaryKeyForUpdate(Long goodsId);

    int insertBatch(@Param("modelList") List<GoodsDescription> modelList);



}