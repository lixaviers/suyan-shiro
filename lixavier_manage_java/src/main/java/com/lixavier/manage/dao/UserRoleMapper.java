package com.lixavier.manage.dao;

import com.lixavier.manage.model.UserRole;
import com.lixavier.manage.model.UserRoleExample;
import com.lixavier.manage.req.UserRoleQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserRoleMapper {
    
    long countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);


    UserRole selectByPrimaryKeyForUpdate(Long id);

    List<UserRole> queryUserRole(UserRoleQueryDTO userRoleQuery);

    int insertBatch(@Param("modelList") List<UserRole> modelList);



}