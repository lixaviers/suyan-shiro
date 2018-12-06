package com.lixavier.manage.dao;

import com.lixavier.manage.model.UserAddress;
import com.lixavier.manage.model.UserAddressExample;
import com.lixavier.manage.req.UserAddressQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserAddressMapper {
    
    long countByExample(UserAddressExample example);

    int deleteByExample(UserAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    List<UserAddress> selectByExample(UserAddressExample example);

    UserAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    int updateByExample(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    int logicalDeleteByPrimaryKey(
            @Param("id") Long id
    );

    UserAddress selectByPrimaryKeyForUpdate(Long id);

    List<UserAddress> queryUserAddress(UserAddressQueryDTO userAddressQuery);

    int insertBatch(@Param("modelList") List<UserAddress> modelList);



}