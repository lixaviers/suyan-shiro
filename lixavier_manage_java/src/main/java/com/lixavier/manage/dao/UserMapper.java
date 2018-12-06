package com.lixavier.manage.dao;

import com.lixavier.manage.model.User;
import com.lixavier.manage.model.UserExample;
import com.lixavier.manage.req.UserQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface UserMapper {

    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int logicalDeleteByPrimaryKey(
            @Param("id") Long id
    );

    User selectByPrimaryKeyForUpdate(Long id);

    List<User> queryUser(UserQueryDTO userQuery);

    int insertBatch(@Param("modelList") List<User> modelList);


    List<Long> getRoleIdListByRoleId(@Param("userId") Long userId);


    int addBalance(User record);

    int pay(
            @Param("id") Long id,
            @Param("amount") BigDecimal amount
    );

}