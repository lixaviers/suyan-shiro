package com.lixavier.manage.dao;

import com.lixavier.manage.model.RoleMenu;
import com.lixavier.manage.model.RoleMenuExample;
import com.lixavier.manage.req.RoleMenuQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RoleMenuMapper {
    
    long countByExample(RoleMenuExample example);

    int deleteByExample(RoleMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    List<RoleMenu> selectByExample(RoleMenuExample example);

    RoleMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoleMenu record, @Param("example") RoleMenuExample example);

    int updateByExample(@Param("record") RoleMenu record, @Param("example") RoleMenuExample example);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);


    RoleMenu selectByPrimaryKeyForUpdate(Long id);

    List<RoleMenu> queryRoleMenu(RoleMenuQueryDTO roleMenuQuery);

    int insertBatch(@Param("modelList") List<RoleMenu> modelList);



}