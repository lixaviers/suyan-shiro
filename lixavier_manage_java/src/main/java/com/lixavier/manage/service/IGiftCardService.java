package com.lixavier.manage.service;

import com.lixavier.manage.req.GiftCardDTO;
import com.lixavier.manage.req.GiftCardQueryDTO;
import com.lixavier.manage.req.UserGiftCardQueryDTO;
import com.lixavier.manage.resp.GiftCardODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 礼品卡管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-21>
 * @Modify Date: <2018-11-21>
 * @Version: <1.0>
 */
public interface IGiftCardService {

    /**
     * 删除礼品卡
     *
     * @param idList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Integer> deleteGiftCard(List<String> idList);

    /**
     * 创建礼品卡
     *
     * @param giftCardDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Integer> createGiftCard(GiftCardDTO giftCardDTO);

    Result<Integer> createUserGiftCard(GiftCardDTO giftCardDTO);

    /**
     * 更新礼品卡
     *
     * @param giftCardDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Integer> updateGiftCard(GiftCardDTO giftCardDTO);

    /**
     * 根据ID获取礼品卡信息
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<GiftCardODTO> getGiftCard(String id);

    /**
     * 分页查询礼品卡信息
     *
     * @param giftCardQueryDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<QueryResultODTO<GiftCardODTO>> queryGiftCard(GiftCardQueryDTO giftCardQueryDTO);

    Result<QueryResultODTO<GiftCardODTO>> queryUserGiftCards(UserGiftCardQueryDTO userGiftCardQueryDTO);

}