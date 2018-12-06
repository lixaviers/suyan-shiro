package com.lixavier.manage.dao;

import com.lixavier.manage.model.Role;
import com.lixavier.manage.model.RoleExample;
import com.lixavier.manage.req.RoleQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RoleMapper {
    
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int logicalDeleteByPrimaryKey(
            @Param("id") Long id
    );

    Role selectByPrimaryKeyForUpdate(Long id);

    List<Role> queryRole(RoleQueryDTO roleQuery);

    int insertBatch(@Param("modelList") List<Role> modelList);




    List<Long> getMenuIdListByRoleId(@Param("roleId")Long roleId);



}