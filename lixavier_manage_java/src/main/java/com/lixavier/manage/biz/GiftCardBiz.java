package com.lixavier.manage.biz;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.async.LetterAsync;
import com.lixavier.manage.constant.GiftCardStatusEnum;
import com.lixavier.manage.dao.GiftCardMapper;
import com.lixavier.manage.exception.CommonBizException;
import com.lixavier.manage.model.GiftCard;
import com.lixavier.manage.model.GiftCardExample;
import com.lixavier.manage.req.GiftCardQueryDTO;
import com.lixavier.manage.req.UserGiftCardQueryDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.util.CodeGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层礼品卡管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-21>
 * @Modify Date: <2018-11-21>
 * @Version: <1.0>
 */
@Service("giftCardBiz")
public class GiftCardBiz {
    private final Logger logger = LoggerFactory.getLogger(GiftCardBiz.class);

    @Autowired
    private GiftCardMapper giftCardMapper;
    @Autowired
    private LetterAsync letterAsync;

    /**
     * 逻辑删除礼品卡
     *
     * @param idList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteGiftCard(List<String> idList) {
        for (String id : idList) {
            giftCardMapper.logicalDeleteByPrimaryKey(id);
        }
        return 1;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public int createUserGiftCard(GiftCard giftCard) {
        GiftCard giftCardLast = giftCardMapper.selectByPrimaryKeyForUpdate(giftCard.getId());
        if (giftCardLast == null || giftCardLast.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "礼品卡");
        }
        if (!GiftCardStatusEnum.VALID.equal(giftCardLast.getCommonStatus())) {
            throw new CommonBizException(ResultCode.COMMON_MESSAGE, "礼品卡已被使用");
        }
        giftCardLast.setUserId(giftCard.getUserId());
        giftCardLast.setCommonStatus(GiftCardStatusEnum.IS_BINDING.getValue());
        giftCardLast.setCreateTime(null);
        giftCardLast.setUpdateTime(null);
        giftCardLast.setIsDeleted(null);
        giftCardMapper.updateByPrimaryKeySelective(giftCardLast);
        // 异步发送消息
        letterAsync.createGiftCardLetter(giftCardLast);

        return 1;
    }

    /**
     * 创建礼品卡
     *
     * @param giftCard
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public int createGiftCard(GiftCard giftCard) {
        Map<String, String> codeMap = new HashMap<>();
        List<GiftCard> giftCardList = new ArrayList<>();
        for (Integer i = 0; i < giftCard.getCount(); i++) {
            // 生成券码
            String cardCode = null;
            GiftCard temp = null;
            do {
                cardCode = CodeGenerator.generateCardCode();
                temp = giftCardMapper.selectByPrimaryKey(cardCode);

                if (temp == null) {
                    String mapCode = codeMap.get(cardCode);
                    if (StringUtils.isNotEmpty(mapCode)) {
                        temp = new GiftCard();
                        temp.setId(null);
                    } else {
                        codeMap.put(cardCode, cardCode);
                    }
                }

            } while (temp != null);

            temp = new GiftCard();

            temp.setId(cardCode);
            temp.setPrice(giftCard.getPrice());
            temp.setEndTime(giftCard.getEndTime());
            temp.setCommonStatus(GiftCardStatusEnum.VALID.getValue());
            temp.setIsDeleted(false);

            giftCardList.add(temp);
        }
        return giftCardMapper.insertBatch(giftCardList);
    }


    /**
     * 更新礼品卡
     *
     * @param giftCard
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer updateGiftCard(GiftCard giftCard) {
        Integer result = null;
        GiftCard giftCardLast = giftCardMapper.selectByPrimaryKeyForUpdate(giftCard.getId());
        if (giftCardLast == null) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "礼品卡");
        }
        giftCard.setIsDeleted(null);
        giftCard.setCreateTime(null);
        result = giftCardMapper.updateByPrimaryKeySelective(giftCard);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer updateGiftCardStatus(GiftCard giftCard) {
        GiftCard giftCardLast = giftCardMapper.selectByPrimaryKeyForUpdate(giftCard.getId());
        if (giftCardLast == null) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "礼品卡");
        }
        GiftCard temp = new GiftCard();
        temp.setId(giftCard.getId());
        temp.setCommonStatus(giftCard.getCommonStatus());
        // TODO 系统日志
        return giftCardMapper.updateByPrimaryKeySelective(temp);
    }

    /**
     * 根据ID获取礼品卡信息
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public GiftCard getGiftCard(String id) {
        GiftCard giftCard = giftCardMapper.selectByPrimaryKey(id);
        if (giftCard == null || giftCard.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "礼品卡");
        }
        return giftCard;
    }

    @Transactional(readOnly = true)
    public List<GiftCard> getGiftCard(List<String> giftCardList, Long userId) {
        GiftCardExample example = new GiftCardExample();
        example.createCriteria().andIsDeletedEqualTo(false).andIdIn(giftCardList);
        List<GiftCard> giftCards = giftCardMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(giftCards)) {
            throw new CommonBizException(ResultCode.COMMON_PARAM_INVALID, "礼品卡");
        }
        if (giftCardList.size() != giftCards.size()) {
            throw new CommonBizException(ResultCode.COMMON_PARAM_INVALID, "礼品卡");
        }
        for (GiftCard giftCard : giftCards) {
            if (!userId.equals(giftCard.getUserId())) {
                throw new CommonBizException(ResultCode.COMMON_PARAM_INVALID, "礼品卡");
            }
            if (!GiftCardStatusEnum.IS_BINDING.equal(giftCard.getCommonStatus())) {
                throw new CommonBizException(ResultCode.COMMON_MESSAGE, "礼品卡" + giftCard.getId() + "不能使用");
            }
        }
        return giftCards;
    }

    /**
      * 核销礼品卡
      * @param giftCardList
      * @return
      */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public int useTheGiftCardAfterVerification(List<GiftCard> giftCardList, String orderNo) {
        List<String> beanList = giftCardList.stream().map(GiftCard::getId).collect(Collectors.toList());
        GiftCard bean = new GiftCard();
        bean.setUseOrderNo(orderNo);
        bean.setCommonStatus(GiftCardStatusEnum.USED.getValue());
        GiftCardExample example = new GiftCardExample();
        example.createCriteria().andIdIn(beanList);
        return giftCardMapper.updateByExampleSelective(bean, example);
    }

    /**
     * 分页查询礼品卡信息
     *
     * @param giftCardQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<GiftCard> queryGiftCard(GiftCardQueryDTO giftCardQuery) {

        QueryResultODTO<GiftCard> queryResult = new QueryResultODTO<GiftCard>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(giftCardQuery.getPageNo(), giftCardQuery.getPageSize());
        List<GiftCard> giftCardList = giftCardMapper.queryGiftCard(giftCardQuery);
        PageInfo<GiftCard> pageInfo = new PageInfo<GiftCard>(giftCardList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(giftCardList);
        return queryResult;
    }

    @Transactional(readOnly = true)
    public QueryResultODTO<GiftCard> queryUserGiftCards(UserGiftCardQueryDTO userGiftCardQueryDTO) {
        QueryResultODTO<GiftCard> queryResult = new QueryResultODTO<GiftCard>();
        GiftCardQueryDTO giftCardQuery = new GiftCardQueryDTO();
        giftCardQuery.setUserId(userGiftCardQueryDTO.getUserId());
        if (userGiftCardQueryDTO.getStatus() == 0) {
            giftCardQuery.setCommonStatus(GiftCardStatusEnum.IS_BINDING.getValue());
        } else {
            List<Byte> commonStatusList = new ArrayList<>();
            commonStatusList.add(GiftCardStatusEnum.USED.getValue());
            commonStatusList.add(GiftCardStatusEnum.INVALID.getValue());
            giftCardQuery.setCommonStatusList(commonStatusList);
        }
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(userGiftCardQueryDTO.getPageNo(), userGiftCardQueryDTO.getPageSize());
        List<GiftCard> giftCardList = giftCardMapper.queryGiftCard(giftCardQuery);
        PageInfo<GiftCard> pageInfo = new PageInfo<GiftCard>(giftCardList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(giftCardList);
        return queryResult;
    }

}