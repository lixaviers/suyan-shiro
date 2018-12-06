package com.lixavier.manage.service;

import com.lixavier.manage.req.GoodsDTO;
import com.lixavier.manage.req.GoodsQueryDTO;
import com.lixavier.manage.resp.GoodsODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 商品管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public interface IGoodsService {

    /**
     * 
     * 删除商品
     * 
     * @author lixavier
     * @version 1.0.0
     * @param id
     * @return
     */
    Result<Integer> deleteGoods(Long id);
    
    /**
     * 
     * 创建商品
     * 
     * @author lixavier
     * @version 1.0.0
     * @param goodsDTO
     * @return
     */
    Result<Long> createGoods(GoodsDTO goodsDTO);

    /**
     * 
     * 更新商品
     * 
     * @author lixavier
     * @version 1.0.0
     * @param goodsDTO
     * @return
     */
    Result<Integer> updateGoods(GoodsDTO goodsDTO);
    
    /**
     * 
     * 根据ID获取商品信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param id
     * @return
     */
    Result<GoodsODTO> getGoods(Long id);

    /**
     * 
     * 分页查询商品信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param goodsQueryDTO
     * @return
     */
    Result<QueryResultODTO<GoodsODTO>> queryGoods(GoodsQueryDTO goodsQueryDTO);

    Result<QueryResultODTO<GoodsODTO>> queryGoodsList(GoodsQueryDTO goodsQueryDTO);

}