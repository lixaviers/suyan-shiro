package com.lixavier.manage.biz;

import com.lixavier.manage.constant.GoodsStatusEnum;
import com.lixavier.manage.dao.GoodsMapper;
import com.lixavier.manage.exception.CommonBizException;
import com.lixavier.manage.model.Category;
import com.lixavier.manage.model.Goods;
import com.lixavier.manage.model.GoodsDescription;
import com.lixavier.manage.model.GoodsExample;
import com.lixavier.manage.req.GoodsQueryDTO;
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

import java.util.*;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层商品管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("goodsBiz")
public class GoodsBiz {
    private final Logger logger = LoggerFactory.getLogger(GoodsBiz.class);

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    private GoodsDescriptionBiz goodsDescriptionBiz;
    @Autowired
    private CategoryBiz categoryBiz;

    /**
     * 逻辑删除商品
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteGoods(Long id) {
        int result = goodsMapper.logicalDeleteByPrimaryKey(id);
        return result;
    }

    /**
     * 创建商品
     *
     * @param goods
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createGoods(Goods goods) {
        goods.setIsDeleted(false);
        goods.setCreateTime(null);
        goods.setUpdateTime(null);
        goods.setRemainingInventory(goods.getInventory());
        goods.setCommonStatus(GoodsStatusEnum.NORMAL.getValue());
        goodsMapper.insertSelective(goods);
        GoodsDescription goodsDescription = new GoodsDescription();
        goodsDescription.setGoodsId(goods.getId());
        goodsDescription.setDescription(goods.getDescription());
        goodsDescriptionBiz.createGoodsDescription(goodsDescription);
        return goods.getId();
    }

    /**
     * 更新商品
     *
     * @param goods
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer updateGoods(Goods goods) {
        Goods goodsLast = goodsMapper.selectByPrimaryKeyForUpdate(goods.getId());
        if (goodsLast == null) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "商品");
        }
        goods.setIsDeleted(null);
        goods.setCreateTime(null);
        goods.setUpdateTime(null);
        GoodsDescription goodsDescription = new GoodsDescription();
        goodsDescription.setGoodsId(goods.getId());
        goodsDescription.setDescription(goods.getDescription());
        goodsDescriptionBiz.updateGoodsDescription(goodsDescription);
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 根据ID获取商品信息
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public Goods getGoods(Long id) {
        Goods goods = goodsMapper.getGoodsById(id);
        if (goods == null || goods.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "商品");
        }
        if (goods.getCategoryId() != null) {
            List<Long> list = new ArrayList<>();
            Category category = categoryBiz.getCategory(goods.getCategoryId());
            list.add(category.getParentId());
            list.add(category.getId());
            goods.setCategoryIds(list);
        }
        return goods;
    }

    /**
     * 分页查询商品信息
     *
     * @param goodsQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<Goods> queryGoods(GoodsQueryDTO goodsQuery) {
        QueryResultODTO<Goods> queryResult = new QueryResultODTO<Goods>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(goodsQuery.getPageNo(), goodsQuery.getPageSize());
        List<Goods> goodsList = goodsMapper.queryGoods(goodsQuery);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(goodsList);
        return queryResult;
    }

    @Transactional(readOnly = true)
    public QueryResultODTO<Goods> queryGoodsList(GoodsQueryDTO goodsQuery) {
        QueryResultODTO<Goods> queryResult = new QueryResultODTO<Goods>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(goodsQuery.getPageNo(), goodsQuery.getPageSize());
        List<Goods> goodsList = goodsMapper.queryGoodsList(GoodsStatusEnum.NORMAL.getValue(), goodsQuery.getCategoryId());
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(goodsList);
        return queryResult;
    }


    @Transactional(readOnly = true)
    public Map<Long, Goods> getGoodsByIdList(List<Long> idList) {
        GoodsExample example = new GoodsExample();
        example.createCriteria().andIdIn(idList);
        List<Goods> goodsList = goodsMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(goodsList)) {
            throw new CommonBizException(ResultCode.COMMON_PARAM_INVALID, "商品");
        }
        if (idList.size() != goodsList.size()) {
            throw new CommonBizException(ResultCode.COMMON_PARAM_INVALID, "商品");
        }
        Map<Long, Goods> map = new HashMap<>();
        for (Goods goods : goodsList) {
            if (goods.getIsDeleted()) {
                throw new CommonBizException(ResultCode.COMMON_MESSAGE, "商品" + goods.getName() + "已被删除,请重新选择");
            }
            if (!GoodsStatusEnum.NORMAL.equal(goods.getCommonStatus())) {
                throw new CommonBizException(ResultCode.COMMON_MESSAGE, "商品" + goods.getName() + "已被下架,请重新选择");
            }
            map.put(goods.getId(), goods);
        }
        return map;
    }


}