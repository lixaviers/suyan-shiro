package com.lixavier.manage.convertor;

import com.lixavier.manage.model.GoodsComment;
import com.lixavier.manage.req.GoodsCommentDTO;
import com.lixavier.manage.resp.GoodsCommentODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层商品评论转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public abstract class GoodsCommentConvertor {

    private static final BeanCopier beanCopierForGoodsCommentODTO = BeanCopier.create(GoodsComment.class, GoodsCommentODTO.class, false);
    private static final BeanCopier beanCopierForGoodsComment = BeanCopier.create(GoodsCommentDTO.class, GoodsComment.class, false);

    public static GoodsCommentODTO toGoodsCommentODTO(GoodsComment goodsComment) {
        if (goodsComment == null) {
            return null;
        }
        GoodsCommentODTO goodsCommentODTO = new GoodsCommentODTO();
        beanCopierForGoodsCommentODTO.copy(goodsComment, goodsCommentODTO, null);
        return goodsCommentODTO;
    }

    public static GoodsComment toGoodsComment(GoodsCommentDTO goodsCommentDTO) {
        GoodsComment goodsComment = new GoodsComment();
        beanCopierForGoodsComment.copy(goodsCommentDTO, goodsComment, null);
        return goodsComment;
    }

    public static List<GoodsCommentODTO> toGoodsCommentODTOList(List<GoodsComment> goodsCommentList) {
        if (goodsCommentList == null || goodsCommentList.isEmpty()) {
            return null;
        }
        List<GoodsCommentODTO> goodsCommentInfoList = new ArrayList<GoodsCommentODTO>(goodsCommentList.size());
        for (GoodsComment goodsComment : goodsCommentList) {
            goodsCommentInfoList.add(toGoodsCommentODTO(goodsComment));
        }
        return goodsCommentInfoList;
    }

    public static List<GoodsComment> toGoodsCommentList(List<GoodsCommentDTO> goodsCommentDTOList) {
        if (goodsCommentDTOList == null || goodsCommentDTOList.isEmpty()) {
            return null;
        }
        List<GoodsComment> goodsCommentList = new ArrayList<GoodsComment>(goodsCommentDTOList.size());
        for (GoodsCommentDTO goodsCommentDTO : goodsCommentDTOList) {
            goodsCommentList.add(toGoodsComment(goodsCommentDTO));
        }
        return goodsCommentList;
    }

    public static QueryResultODTO<GoodsCommentODTO> toQueryResult(QueryResultODTO<GoodsComment> queryResult) {
        QueryResultODTO<GoodsCommentODTO> queryResultInfo = new QueryResultODTO<GoodsCommentODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toGoodsCommentODTOList(queryResult.getRecords()));
        return queryResultInfo;
    }
}