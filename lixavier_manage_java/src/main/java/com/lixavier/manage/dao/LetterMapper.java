package com.lixavier.manage.dao;

import com.lixavier.manage.model.Letter;
import com.lixavier.manage.model.LetterExample;
import com.lixavier.manage.req.LetterQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LetterMapper {
    
    long countByExample(LetterExample example);

    int deleteByExample(LetterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Letter record);

    int insertSelective(Letter record);

    List<Letter> selectByExample(LetterExample example);

    Letter selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Letter record, @Param("example") LetterExample example);

    int updateByExample(@Param("record") Letter record, @Param("example") LetterExample example);

    int updateByPrimaryKeySelective(Letter record);

    int updateByPrimaryKey(Letter record);

    int logicalDeleteByPrimaryKey(
            @Param("id") Long id
    );

    Letter selectByPrimaryKeyForUpdate(Long id);

    List<Letter> queryLetter(LetterQueryDTO letterQuery);

    int insertBatch(@Param("modelList") List<Letter> modelList);



}