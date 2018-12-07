package com.lixavier.manage.service;

import com.lixavier.manage.req.GoodsCommentDTO;
import com.lixavier.manage.req.GoodsCommentQueryDTO;
import com.lixavier.manage.resp.GoodsCommentODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 商品评论管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public interface IGoodsCommentService {


    /**
     * 
     * 创建商品评论
     * 
     * @author lixavier
     * @version 1.0.0
     * @param goodsCommentDTO
     * @return
     */
    Result<Long> createGoodsComment(GoodsCommentDTO goodsCommentDTO);

    /**
     *
     * 回复商品评论
     *
     * @author lixavier
     * @version 1.0.0
     * @param goodsCommentDTO
     * @return
     */
    Result<Integer> replyGoodsComment(GoodsCommentDTO goodsCommentDTO);


    /**
     * 
     * 分页查询商品评论信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param goodsCommentQueryDTO
     * @return
     */
    Result<QueryResultODTO<GoodsCommentODTO>> queryGoodsComment(GoodsCommentQueryDTO goodsCommentQueryDTO);

}