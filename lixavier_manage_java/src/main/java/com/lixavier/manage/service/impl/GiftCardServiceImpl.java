package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.GiftCardBiz;
import com.lixavier.manage.convertor.GiftCardConvertor;
import com.lixavier.manage.model.GiftCard;
import com.lixavier.manage.req.GiftCardDTO;
import com.lixavier.manage.req.GiftCardQueryDTO;
import com.lixavier.manage.req.UserGiftCardQueryDTO;
import com.lixavier.manage.resp.GiftCardODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.IGiftCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lixavier.manage.validate.GiftCardValidate.validateForCreate;
import static com.lixavier.manage.validate.GiftCardValidate.validateForUpdate;


/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 礼品卡管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-21>
 * @Modify Date: <2018-11-21>
 * @Version: <1.0>
 */
@Service("giftCardService")
public class GiftCardServiceImpl implements IGiftCardService {
    private final Logger logger = LoggerFactory.getLogger(GiftCardServiceImpl.class);

    @Autowired
    private GiftCardBiz giftCardBiz;

    /**
     * 删除礼品卡
     *
     * @param idList
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> deleteGiftCard(List<String> idList) {
        Result<Integer> result = Result.newSuccess();
        result.setDataMap(giftCardBiz.deleteGiftCard(idList));
        return result;
    }

    /**
     * 创建礼品卡
     *
     * @param giftCardDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> createGiftCard(GiftCardDTO giftCardDTO) {
        Result<Integer> result = Result.newSuccess();
        GiftCard giftCard = GiftCardConvertor.toGiftCard(giftCardDTO);
        if (!validateForCreate(giftCard, result)) {
            return result;
        }
        result.setDataMap(giftCardBiz.createGiftCard(giftCard));
        return result;
    }

    @Override
    public Result<Integer> createUserGiftCard(GiftCardDTO giftCardDTO) {
        Result<Integer> result = Result.newSuccess();
        result.setDataMap(giftCardBiz.createUserGiftCard(GiftCardConvertor.toGiftCard(giftCardDTO)));
        return result;
    }

    /**
     * 更新礼品卡
     *
     * @param giftCardDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> updateGiftCard(GiftCardDTO giftCardDTO) {
        Result<Integer> result = Result.newSuccess();
        GiftCard giftCard = GiftCardConvertor.toGiftCard(giftCardDTO);
        if (!validateForUpdate(giftCard, result)) {
            return result;
        }
        result.setDataMap(giftCardBiz.updateGiftCard(giftCard));
        return result;
    }

    /**
     * 根据ID获取礼品卡信息
     *
     * @param id
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<GiftCardODTO> getGiftCard(String id) {
        Result<GiftCardODTO> result = Result.newSuccess();
        GiftCard giftCard = giftCardBiz.getGiftCard(id);
        GiftCardODTO giftCardODTO = GiftCardConvertor.toGiftCardODTO(giftCard);
        result.setDataMap(giftCardODTO);
        return result;
    }

    /**
     * 分页查询礼品卡信息
     *
     * @param giftCardQueryDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<QueryResultODTO<GiftCardODTO>> queryGiftCard(GiftCardQueryDTO giftCardQueryDTO) {
        Result<QueryResultODTO<GiftCardODTO>> result = Result.newSuccess();

        QueryResultODTO<GiftCard> resultInfo = giftCardBiz.queryGiftCard(giftCardQueryDTO);
        result.setDataMap(GiftCardConvertor.toQueryResult(resultInfo));

        return result;
    }

    @Override
    public Result<QueryResultODTO<GiftCardODTO>> queryUserGiftCards(UserGiftCardQueryDTO userGiftCardQueryDTO) {
        Result<QueryResultODTO<GiftCardODTO>> result = Result.newSuccess();

        QueryResultODTO<GiftCard> resultInfo = giftCardBiz.queryUserGiftCards(userGiftCardQueryDTO);
        result.setDataMap(GiftCardConvertor.toQueryResult(resultInfo));

        return result;
    }

}