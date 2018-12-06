package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.GoodsBiz;
import com.lixavier.manage.convertor.GoodsConvertor;
import com.lixavier.manage.model.Goods;
import com.lixavier.manage.req.GoodsDTO;
import com.lixavier.manage.req.GoodsQueryDTO;
import com.lixavier.manage.resp.GoodsODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.IGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lixavier.manage.validate.GoodsValidate.validateForCreate;
import static com.lixavier.manage.validate.GoodsValidate.validateForUpdate;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 商品管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService {
    private final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsBiz goodsBiz;

    @Override
    public Result<Integer> deleteGoods(Long id) {
        Result<Integer> result = Result.newSuccess();
        result.setDataMap(goodsBiz.deleteGoods(id));
        return result;
    }

    /**
     * 创建商品
     *
     * @param goodsDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Long> createGoods(GoodsDTO goodsDTO) {
        Result<Long> result = Result.newSuccess();
        Goods goods = GoodsConvertor.toGoods(goodsDTO);
        if (!validateForCreate(goods, result)) {
            return result;
        }
        result.setDataMap(goodsBiz.createGoods(goods));
        return result;
    }

    /**
     * 更新商品
     *
     * @param goodsDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> updateGoods(GoodsDTO goodsDTO) {
        Result<Integer> result = Result.newSuccess();
        Goods goods = null;
        goods = GoodsConvertor.toGoods(goodsDTO);
        if (!validateForUpdate(goods, result)) {
            return result;
        }
        result.setDataMap(goodsBiz.updateGoods(goods));
        return result;
    }

    /**
     * 根据ID获取商品信息
     *
     * @param id
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<GoodsODTO> getGoods(Long id) {
        Result<GoodsODTO> result = Result.newSuccess();
        Goods goods = goodsBiz.getGoods(id);
        GoodsODTO goodsODTO = GoodsConvertor.toGoodsODTO(goods);
        result.setDataMap(goodsODTO);
        return result;
    }

    /**
     * 分页查询商品信息
     *
     * @param goodsQueryDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<QueryResultODTO<GoodsODTO>> queryGoods(GoodsQueryDTO goodsQueryDTO) {
        Result<QueryResultODTO<GoodsODTO>> result = Result.newSuccess();

        QueryResultODTO<Goods> resultInfo = goodsBiz.queryGoods(goodsQueryDTO);
        result.setDataMap(GoodsConvertor.toQueryResult(resultInfo));

        return result;
    }

    @Override
    public Result<QueryResultODTO<GoodsODTO>> queryGoodsList(GoodsQueryDTO goodsQueryDTO) {
        Result<QueryResultODTO<GoodsODTO>> result = Result.newSuccess();

        QueryResultODTO<Goods> resultInfo = goodsBiz.queryGoodsList(goodsQueryDTO);
        result.setDataMap(GoodsConvertor.toQueryResult(resultInfo));

        return result;
    }
}