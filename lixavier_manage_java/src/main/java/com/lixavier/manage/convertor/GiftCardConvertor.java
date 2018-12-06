package com.lixavier.manage.convertor;

import com.lixavier.manage.model.GiftCard;
import com.lixavier.manage.req.GiftCardDTO;
import com.lixavier.manage.resp.GiftCardODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层礼品卡转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-21>
 * @Modify Date: <2018-11-21>
 * @Version: <1.0>
 */
public abstract class GiftCardConvertor {

    private static final BeanCopier beanCopierForGiftCardODTO = BeanCopier.create(GiftCard.class, GiftCardODTO.class, false);
    private static final BeanCopier beanCopierForGiftCard = BeanCopier.create(GiftCardDTO.class, GiftCard.class, false);

    public static GiftCardODTO toGiftCardODTO(GiftCard giftCard) {
        if (giftCard == null) {
            return null;
        }
        GiftCardODTO giftCardODTO = new GiftCardODTO();
        beanCopierForGiftCardODTO.copy(giftCard, giftCardODTO, null);
        return giftCardODTO;
    }

    public static GiftCard toGiftCard(GiftCardDTO giftCardDTO) {
        GiftCard giftCard = new GiftCard();
        beanCopierForGiftCard.copy(giftCardDTO, giftCard, null);
        return giftCard;
    }

    public static List<GiftCardODTO> toGiftCardODTOList(List<GiftCard> giftCardList) {
        if (giftCardList == null || giftCardList.isEmpty()) {
            return null;
        }
        List<GiftCardODTO> giftCardInfoList = new ArrayList<GiftCardODTO>(giftCardList.size());
        for (GiftCard giftCard : giftCardList) {
            giftCardInfoList.add(toGiftCardODTO(giftCard));
        }
        return giftCardInfoList;
    }

    public static List<GiftCard> toGiftCardList(List<GiftCardDTO> giftCardDTOList) {
        if (giftCardDTOList == null || giftCardDTOList.isEmpty()) {
            return null;
        }
        List<GiftCard> giftCardList = new ArrayList<GiftCard>(giftCardDTOList.size());
        for (GiftCardDTO giftCardDTO : giftCardDTOList) {
            giftCardList.add(toGiftCard(giftCardDTO));
        }
        return giftCardList;
    }

    public static QueryResultODTO<GiftCardODTO> toQueryResult(QueryResultODTO<GiftCard> queryResult) {
        QueryResultODTO<GiftCardODTO> queryResultInfo = new QueryResultODTO<GiftCardODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toGiftCardODTOList(queryResult.getRecords()));
        return queryResultInfo;
        }
}