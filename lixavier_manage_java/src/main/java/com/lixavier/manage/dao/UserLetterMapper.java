package com.lixavier.manage.dao;

import com.lixavier.manage.model.UserLetter;
import com.lixavier.manage.model.UserLetterExample;
import com.lixavier.manage.req.UserLetterQueryDTO;
import com.lixavier.manage.resp.EnumODTO;
import com.lixavier.manage.resp.page.UserLetterPageODTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserLetterMapper {
    
    long countByExample(UserLetterExample example);

    int deleteByExample(UserLetterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLetter record);

    int insertSelective(UserLetter record);

    List<UserLetter> selectByExample(UserLetterExample example);

    UserLetter selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLetter record, @Param("example") UserLetterExample example);

    int updateByExample(@Param("record") UserLetter record, @Param("example") UserLetterExample example);

    int updateByPrimaryKeySelective(UserLetter record);

    int updateByPrimaryKey(UserLetter record);


    UserLetter selectByPrimaryKeyForUpdate(Long id);

    List<UserLetterPageODTO> queryUserLetter(UserLetterQueryDTO userLetterQuery);

    int insertBatch(@Param("modelList") List<UserLetter> modelList);







    List<EnumODTO> getUserLetterCount(Long userId);





}