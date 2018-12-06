package com.lixavier.manage.convertor;

import com.lixavier.manage.constant.GoodsStatusEnum;
import com.lixavier.manage.model.Goods;
import com.lixavier.manage.req.GoodsDTO;
import com.lixavier.manage.resp.GoodsODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层商品转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public abstract class GoodsConvertor {

    private static final BeanCopier beanCopierForGoodsODTO = BeanCopier.create(Goods.class, GoodsODTO.class, false);
    private static final BeanCopier beanCopierForGoods = BeanCopier.create(GoodsDTO.class, Goods.class, false);

    public static GoodsODTO toGoodsODTO(Goods goods) {
        if (goods == null) {
            return null;
        }
        GoodsODTO goodsODTO = new GoodsODTO();
        beanCopierForGoodsODTO.copy(goods, goodsODTO, null);
        goodsODTO.setStatusDesc(GoodsStatusEnum.getDescByValue(goods.getCommonStatus()));
        return goodsODTO;
    }

    public static Goods toGoods(GoodsDTO goodsDTO) {
        Goods goods = new Goods();
        beanCopierForGoods.copy(goodsDTO, goods, null);
        return goods;
    }

    public static List<GoodsODTO> toGoodsODTOList(List<Goods> goodsList) {
        if (goodsList == null || goodsList.isEmpty()) {
            return null;
        }
        List<GoodsODTO> goodsInfoList = new ArrayList<GoodsODTO>(goodsList.size());
        for (Goods goods : goodsList) {
            goodsInfoList.add(toGoodsODTO(goods));
        }
        return goodsInfoList;
    }

    public static List<Goods> toGoodsList(List<GoodsDTO> goodsDTOList) {
        if (goodsDTOList == null || goodsDTOList.isEmpty()) {
            return null;
        }
        List<Goods> goodsList = new ArrayList<Goods>(goodsDTOList.size());
        for (GoodsDTO goodsDTO : goodsDTOList) {
            goodsList.add(toGoods(goodsDTO));
        }
        return goodsList;
    }

    public static QueryResultODTO<GoodsODTO> toQueryResult(QueryResultODTO<Goods> queryResult) {
        QueryResultODTO<GoodsODTO> queryResultInfo = new QueryResultODTO<GoodsODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toGoodsODTOList(queryResult.getRecords()));
        return queryResultInfo;
        }
}