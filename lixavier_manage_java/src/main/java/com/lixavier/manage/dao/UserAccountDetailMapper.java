package com.lixavier.manage.dao;

import com.lixavier.manage.model.UserAccountDetail;
import com.lixavier.manage.model.UserAccountDetailExample;
import com.lixavier.manage.req.UserAccountDetailQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserAccountDetailMapper {
    
    long countByExample(UserAccountDetailExample example);

    int deleteByExample(UserAccountDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserAccountDetail record);

    int insertSelective(UserAccountDetail record);

    List<UserAccountDetail> selectByExample(UserAccountDetailExample example);

    UserAccountDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserAccountDetail record, @Param("example") UserAccountDetailExample example);

    int updateByExample(@Param("record") UserAccountDetail record, @Param("example") UserAccountDetailExample example);

    int updateByPrimaryKeySelective(UserAccountDetail record);

    int updateByPrimaryKey(UserAccountDetail record);


    UserAccountDetail selectByPrimaryKeyForUpdate(Long id);

    List<UserAccountDetail> queryUserAccountDetail(UserAccountDetailQueryDTO userAccountDetailQuery);

    int insertBatch(@Param("modelList") List<UserAccountDetail> modelList);



}