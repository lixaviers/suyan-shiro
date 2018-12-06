//package com.lixavier.manage.biz;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.lixavier.manage.async.LetterAsync;
//import com.lixavier.manage.constant.GiftCardStatusEnum;
//import com.lixavier.manage.constant.UserGiftCardStatusEnum;
//import com.lixavier.manage.exception.CommonBizException;
//import com.lixavier.manage.model.GiftCard;
//import com.lixavier.manage.req.page.UserGiftCardQueryPageDTO;
//import com.lixavier.manage.resp.QueryResultODTO;
//import com.lixavier.manage.result.ResultCode;
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
// * @Project: <manage>
// * @Comments: <业务层用户礼品卡管理类>
// * @jdk 1.8
// * @Author: <lixavier>
// * @email : <lixavier@163.com>
// * @Create Date: <2018-11-22>
// * @Modify Date: <2018-11-22>
// * @Version: <1.0>
// */
//@Service("userGiftCardBiz")
//public class UserGiftCardBiz {
//
//    @Autowired
//    UserGiftCardMapper userGiftCardMapper;
//
//    @Autowired
//    GiftCardBiz giftCardBiz;
//
//    @Autowired
//    private LetterAsync letterAsync;
//
//    /**
//     * 查询用户礼品卡信息
//     *
//     * @return
//     * @author lixavier
//     * @version 1.0.0
//     */
//    @Transactional(readOnly = true)
//    public QueryResultODTO<GiftCard> getUserGiftCards(UserGiftCardQueryPageDTO dto) {
//        QueryResultODTO<GiftCard> queryResult = new QueryResultODTO<GiftCard>();
//        // 使用分页插件PageHelper实现分页功能
//        PageHelper.startPage(dto.getPageNo(), dto.getPageSize());
//        List<GiftCard> userGiftCards = userGiftCardMapper.getUserGiftCards(dto.getUserId(), dto.getStatus());
//        PageInfo<GiftCard> pageInfo = new PageInfo<GiftCard>(userGiftCards);
//        queryResult.setPageNo(pageInfo.getPageNum());
//        queryResult.setPageSize(pageInfo.getPageSize());
//        queryResult.setTotalPages(pageInfo.getPages());
//        queryResult.setTotalRecords(pageInfo.getTotal());
//        queryResult.setRecords(userGiftCards);
//        return queryResult;
//    }
//
//    @Transactional(readOnly = true)
//    public List<GiftCard> getGiftCard(List<Long> idList, Long userId) {
//        List<GiftCard> giftCardList = userGiftCardMapper.getGiftCardsByIdList(idList);
//        // 这里的[id、状态]为UserGiftCard中的，为了方便后面使用
//        if (CollectionUtils.isEmpty(giftCardList)) {
//            throw new CommonBizException(ResultCode.COMMON_PARAM_INVALID, "礼品卡");
//        }
//        if (giftCardList.size() != idList.size()) {
//            throw new CommonBizException(ResultCode.COMMON_PARAM_INVALID, "礼品卡");
//        }
//        for (GiftCard giftCard : giftCardList) {
//            if (!userId.equals(giftCard.getUserId())) {
//                throw new CommonBizException(ResultCode.COMMON_PARAM_INVALID, "礼品卡");
//            }
//            if (!UserGiftCardStatusEnum.VALID.equal(giftCard.getCommonStatus())) {
//                throw new CommonBizException(ResultCode.COMMON_MESSAGE, "礼品卡" + giftCard.getCardCode() + "不能使用");
//            }
//        }
//        return giftCardList;
//    }
//
//
//    /**
//     * 核销礼品卡
//     * @param giftCardList
//     * @return
//     */
//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
//    public int useTheGiftCardAfterVerification(List<GiftCard> giftCardList) {
//        List<Long> beanList = giftCardList.stream().map(GiftCard::getId).collect(Collectors.toList());
//        UserGiftCard bean = new UserGiftCard();
//        bean.setCommonStatus(UserGiftCardStatusEnum.USED.getValue());
//        UserGiftCardExample example = new UserGiftCardExample();
//        example.createCriteria().andIdIn(beanList);
//        return userGiftCardMapper.updateByExampleSelective(bean, example);
//    }
//
//
//}