package com.lixavier.manage.dao;

import com.lixavier.manage.model.Goods;
import com.lixavier.manage.model.UserCart;
import com.lixavier.manage.model.UserCartExample;
import com.lixavier.manage.req.UserCartQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCartMapper {

    long countByExample(UserCartExample example);

    int deleteByExample(UserCartExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserCart record);

    int insertSelective(UserCart record);

    List<UserCart> selectByExample(UserCartExample example);

    UserCart selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserCart record, @Param("example") UserCartExample example);

    int updateByExample(@Param("record") UserCart record, @Param("example") UserCartExample example);

    int updateByPrimaryKeySelective(UserCart record);

    int updateByPrimaryKey(UserCart record);

    int logicalDeleteByPrimaryKey(
            @Param("id") Long id
    );

    UserCart selectByPrimaryKeyForUpdate(Long id);

    List<Goods> queryUserCart(UserCartQueryDTO userCartQuery);

    int insertBatch(@Param("modelList") List<UserCart> modelList);


    int updateNumber(
            @Param("id") Long id,
            @Param("number") Integer number
    );


    int deleteUserCarts(@Param("bean") UserCart bean);

}