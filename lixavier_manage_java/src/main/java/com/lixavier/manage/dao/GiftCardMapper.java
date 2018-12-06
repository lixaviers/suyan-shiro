package com.lixavier.manage.dao;

import com.lixavier.manage.model.GiftCard;
import com.lixavier.manage.model.GiftCardExample;
import com.lixavier.manage.req.GiftCardQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GiftCardMapper {

    long countByExample(GiftCardExample example);

    int deleteByExample(GiftCardExample example);

    int deleteByPrimaryKey(String id);

    int insert(GiftCard record);

    int insertSelective(GiftCard record);

    List<GiftCard> selectByExample(GiftCardExample example);

    GiftCard selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GiftCard record, @Param("example") GiftCardExample example);

    int updateByExample(@Param("record") GiftCard record, @Param("example") GiftCardExample example);

    int updateByPrimaryKeySelective(GiftCard record);

    int updateByPrimaryKey(GiftCard record);

    int logicalDeleteByPrimaryKey(
            @Param("id")String id
    );

    GiftCard selectByPrimaryKeyForUpdate(String id);

    List<GiftCard> queryGiftCard(GiftCardQueryDTO giftCardQuery);

    int insertBatch(@Param("modelList")List<GiftCard> modelList);



}