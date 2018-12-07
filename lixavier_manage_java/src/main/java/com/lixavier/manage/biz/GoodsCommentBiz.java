package com.lixavier.manage.biz;

import com.alibaba.fastjson.JSON;
import com.lixavier.manage.constant.OrderStatusEnum;
import com.lixavier.manage.dao.GoodsCommentMapper;
import com.lixavier.manage.dao.OrderGoodsMapper;
import com.lixavier.manage.exception.CommonBizException;
import com.lixavier.manage.model.GoodsComment;
import com.lixavier.manage.model.OrderGoods;
import com.lixavier.manage.model.OrderGoodsExample;
import com.lixavier.manage.req.GoodsCommentQueryDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.ResultCode;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层商品评论管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
@Service("goodsCommentBiz")
public class GoodsCommentBiz {
    private final Logger logger = LoggerFactory.getLogger(GoodsCommentBiz.class);

    @Autowired
    GoodsCommentMapper goodsCommentMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    /**
     * 创建商品评论
     *
     * @param goodsComment
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createGoodsComment(GoodsComment goodsComment) {
        // 查询是否有交易成功的商品(删除的也算)
        OrderGoodsExample example = new OrderGoodsExample();
        example.createCriteria().andGoodsIdEqualTo(goodsComment.getGoodsId())
                .andUserIdEqualTo(goodsComment.getUserId())
                .andCommonStatusEqualTo(OrderStatusEnum.TRADE_SUCCESSFULLY.getValue());
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(orderGoods)) {
            throw new CommonBizException(ResultCode.NO_PERMISSION);
        }
        if (CollectionUtils.isNotEmpty(goodsComment.getUrls())) {
            goodsComment.setPicUrls(JSON.toJSONString(goodsComment.getUrls()));
        } else {
            goodsComment.setPicUrls(null);
        }
        goodsComment.setIsReply(null);
        goodsComment.setReplyContent(null);
        goodsComment.setCreateTime(null);
        goodsComment.setUpdateTime(null);
        goodsCommentMapper.insertSelective(goodsComment);

        // 订单商品改成已完成状态
        for (OrderGoods orderGood : orderGoods) {
            OrderGoods temp = new OrderGoods();
            temp.setId(orderGood.getId());
            temp.setCommonStatus(OrderStatusEnum.COMPLETED.getValue());
            orderGoodsMapper.updateByPrimaryKeySelective(temp);
        }

        return goodsComment.getId();
    }

    /**
     * 回复商品评论
     *
     * @param goodsComment
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer replyGoodsComment(GoodsComment goodsComment) {
        GoodsComment goodsCommentLast = goodsCommentMapper.selectByPrimaryKeyForUpdate(goodsComment.getId());
        if (goodsCommentLast == null) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "商品评论");
        }
        if (goodsCommentLast.getIsReply()) {
            throw new CommonBizException(ResultCode.COMMON_MESSAGE, "商品评论已回复");
        }
        GoodsComment bean = new GoodsComment();
        bean.setId(goodsComment.getId());
        bean.setIsReply(true);
        bean.setReplyContent(goodsComment.getReplyContent());
        return goodsCommentMapper.updateByPrimaryKeySelective(bean);
    }

    /**
     * 分页查询商品评论信息
     *
     * @param goodsCommentQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<GoodsComment> queryGoodsComment(GoodsCommentQueryDTO goodsCommentQuery) {
        QueryResultODTO<GoodsComment> queryResult = new QueryResultODTO<GoodsComment>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(goodsCommentQuery.getPageNo(), goodsCommentQuery.getPageSize());
        List<GoodsComment> goodsCommentList = goodsCommentMapper.queryGoodsComment(goodsCommentQuery);
        PageInfo<GoodsComment> pageInfo = new PageInfo<GoodsComment>(goodsCommentList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(goodsCommentList);
        return queryResult;
    }

}