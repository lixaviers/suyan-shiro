package com.lixavier.manage.convertor;

import com.lixavier.manage.constant.AccountDetailTypeEnum;
import com.lixavier.manage.constant.ChannelEnum;
import com.lixavier.manage.constant.TransactionTypeEnum;
import com.lixavier.manage.model.UserAccountDetail;
import com.lixavier.manage.req.UserAccountDetailDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserAccountDetailODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层用户账户明细转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public abstract class UserAccountDetailConvertor {

    private static final BeanCopier beanCopierForUserAccountDetailODTO = BeanCopier.create(UserAccountDetail.class, UserAccountDetailODTO.class, false);
    private static final BeanCopier beanCopierForUserAccountDetail = BeanCopier.create(UserAccountDetailDTO.class, UserAccountDetail.class, false);

    public static UserAccountDetailODTO toUserAccountDetailODTO(UserAccountDetail userAccountDetail) {
        if (userAccountDetail == null) {
            return null;
        }
        UserAccountDetailODTO userAccountDetailODTO = new UserAccountDetailODTO();
        beanCopierForUserAccountDetailODTO.copy(userAccountDetail, userAccountDetailODTO, null);
        userAccountDetailODTO.setTransactionTypeDesc(TransactionTypeEnum.getDescByValue(userAccountDetail.getTransactionType()));
        userAccountDetailODTO.setChannelDesc(ChannelEnum.getDescByValue(userAccountDetail.getChannel()));
        return userAccountDetailODTO;
    }

    public static UserAccountDetail toUserAccountDetail(UserAccountDetailDTO userAccountDetailDTO) {
        UserAccountDetail userAccountDetail = new UserAccountDetail();
        beanCopierForUserAccountDetail.copy(userAccountDetailDTO, userAccountDetail, null);
        return userAccountDetail;
    }

    public static List<UserAccountDetailODTO> toUserAccountDetailODTOList(List<UserAccountDetail> userAccountDetailList) {
        if (userAccountDetailList == null || userAccountDetailList.isEmpty()) {
            return null;
        }
        List<UserAccountDetailODTO> userAccountDetailInfoList = new ArrayList<UserAccountDetailODTO>(userAccountDetailList.size());
        for (UserAccountDetail userAccountDetail : userAccountDetailList) {
            userAccountDetailInfoList.add(toUserAccountDetailODTO(userAccountDetail));
        }
        return userAccountDetailInfoList;
    }

    public static List<UserAccountDetail> toUserAccountDetailList(List<UserAccountDetailDTO> userAccountDetailDTOList) {
        if (userAccountDetailDTOList == null || userAccountDetailDTOList.isEmpty()) {
            return null;
        }
        List<UserAccountDetail> userAccountDetailList = new ArrayList<UserAccountDetail>(userAccountDetailDTOList.size());
        for (UserAccountDetailDTO userAccountDetailDTO : userAccountDetailDTOList) {
            userAccountDetailList.add(toUserAccountDetail(userAccountDetailDTO));
        }
        return userAccountDetailList;
    }

    public static QueryResultODTO<UserAccountDetailODTO> toQueryResult(QueryResultODTO<UserAccountDetail> queryResult) {
        QueryResultODTO<UserAccountDetailODTO> queryResultInfo = new QueryResultODTO<UserAccountDetailODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toUserAccountDetailODTOList(queryResult.getRecords()));
        return queryResultInfo;
        }
}