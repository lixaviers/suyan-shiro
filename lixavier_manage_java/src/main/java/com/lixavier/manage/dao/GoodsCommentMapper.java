package com.lixavier.manage.dao;

import com.lixavier.manage.model.GoodsComment;
import com.lixavier.manage.model.GoodsCommentExample;
import com.lixavier.manage.req.GoodsCommentQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GoodsCommentMapper {
    
    long countByExample(GoodsCommentExample example);

    int deleteByExample(GoodsCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsComment record);

    int insertSelective(GoodsComment record);

    List<GoodsComment> selectByExample(GoodsCommentExample example);

    GoodsComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsComment record, @Param("example") GoodsCommentExample example);

    int updateByExample(@Param("record") GoodsComment record, @Param("example") GoodsCommentExample example);

    int updateByPrimaryKeySelective(GoodsComment record);

    int updateByPrimaryKey(GoodsComment record);


    GoodsComment selectByPrimaryKeyForUpdate(Long id);

    List<GoodsComment> queryGoodsComment(GoodsCommentQueryDTO goodsCommentQuery);

    int insertBatch(@Param("modelList") List<GoodsComment> modelList);



}