package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.GoodsCommentBiz;
import com.lixavier.manage.convertor.GoodsCommentConvertor;
import com.lixavier.manage.model.GoodsComment;
import com.lixavier.manage.req.GoodsCommentDTO;
import com.lixavier.manage.req.GoodsCommentQueryDTO;
import com.lixavier.manage.resp.GoodsCommentODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.IGoodsCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lixavier.manage.validate.GoodsCommentValidate.validateForCreate;
import static com.lixavier.manage.validate.GoodsCommentValidate.validateForReply;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 商品评论管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
@Service("goodsCommentService")
public class GoodsCommentServiceImpl implements IGoodsCommentService {
    private final Logger logger = LoggerFactory.getLogger(GoodsCommentServiceImpl.class);

    @Autowired
    private GoodsCommentBiz goodsCommentBiz;

    @Override
    public Result<Long> createGoodsComment(GoodsCommentDTO goodsCommentDTO) {
        Result<Long> result = Result.newSuccess();
        GoodsComment goodsComment = GoodsCommentConvertor.toGoodsComment(goodsCommentDTO);
        if (!validateForCreate(goodsComment, result)) {
            return result;
        }
        result.setDataMap(goodsCommentBiz.createGoodsComment(goodsComment));
        return result;
    }

    @Override
    public Result<Integer> replyGoodsComment(GoodsCommentDTO goodsCommentDTO) {
        Result<Integer> result = Result.newSuccess();
        GoodsComment goodsComment = GoodsCommentConvertor.toGoodsComment(goodsCommentDTO);
        if (!validateForReply(goodsComment, result)) {
            return result;
        }
        result.setDataMap(goodsCommentBiz.replyGoodsComment(goodsComment));
        return result;
    }

    @Override
    public Result<QueryResultODTO<GoodsCommentODTO>> queryGoodsComment(GoodsCommentQueryDTO goodsCommentQueryDTO) {
        Result<QueryResultODTO<GoodsCommentODTO>> result = Result.newSuccess();

        QueryResultODTO<GoodsComment> resultInfo = goodsCommentBiz.queryGoodsComment(goodsCommentQueryDTO);
        result.setDataMap(GoodsCommentConvertor.toQueryResult(resultInfo));

        return result;
    }
}