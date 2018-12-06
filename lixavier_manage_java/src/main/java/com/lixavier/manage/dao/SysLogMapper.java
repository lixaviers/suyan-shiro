package com.lixavier.manage.dao;

import com.lixavier.manage.model.SysLog;
import com.lixavier.manage.model.SysLogExample;
import com.lixavier.manage.req.SysLogQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SysLogMapper {
    
    long countByExample(SysLogExample example);

    int deleteByExample(SysLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    List<SysLog> selectByExample(SysLogExample example);

    SysLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysLog record, @Param("example") SysLogExample example);

    int updateByExample(@Param("record") SysLog record, @Param("example") SysLogExample example);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    int logicalDeleteByPrimaryKey(
            @Param("id") Long id,
            @Param("updateUser") String updateUser,
            @Param("updateUserName") String updateUserName
    );

    SysLog selectByPrimaryKeyForUpdate(Long id);

    List<SysLog> querySysLog(SysLogQueryDTO sysLogQuery);

    int insertBatch(@Param("modelList") List<SysLog> modelList);



}