package com.lixavier.manage.dao;

import com.lixavier.manage.model.Category;
import com.lixavier.manage.model.CategoryExample;
import com.lixavier.manage.req.CategoryQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CategoryMapper {
    
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    int logicalDeleteByPrimaryKey(
            @Param("id") Long id,
            @Param("updateUser") String updateUser,
            @Param("updateUserName") String updateUserName
    );

    Category selectByPrimaryKeyForUpdate(Long id);

    List<Category> queryCategory(CategoryQueryDTO categoryQuery);

    int insertBatch(@Param("modelList") List<Category> modelList);


    List<Category> getCategoryList();



}