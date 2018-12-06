package com.lixavier.manage.convertor;

import com.lixavier.manage.model.UserLetter;
import com.lixavier.manage.req.UserLetterDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserLetterODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层用户站内信转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public abstract class UserLetterConvertor {

    private static final BeanCopier beanCopierForUserLetterODTO = BeanCopier.create(UserLetter.class, UserLetterODTO.class, false);
    private static final BeanCopier beanCopierForUserLetter = BeanCopier.create(UserLetterDTO.class, UserLetter.class, false);

    public static UserLetterODTO toUserLetterODTO(UserLetter userLetter) {
        if (userLetter == null) {
            return null;
        }
        UserLetterODTO userLetterODTO = new UserLetterODTO();
        beanCopierForUserLetterODTO.copy(userLetter, userLetterODTO, null);
        return userLetterODTO;
    }

    public static UserLetter toUserLetter(UserLetterDTO userLetterDTO) {
        UserLetter userLetter = new UserLetter();
        beanCopierForUserLetter.copy(userLetterDTO, userLetter, null);
        return userLetter;
    }

    public static List<UserLetterODTO> toUserLetterODTOList(List<UserLetter> userLetterList) {
        if (userLetterList == null || userLetterList.isEmpty()) {
            return null;
        }
        List<UserLetterODTO> userLetterInfoList = new ArrayList<UserLetterODTO>(userLetterList.size());
        for (UserLetter userLetter : userLetterList) {
            userLetterInfoList.add(toUserLetterODTO(userLetter));
        }
        return userLetterInfoList;
    }

    public static List<UserLetter> toUserLetterList(List<UserLetterDTO> userLetterDTOList) {
        if (userLetterDTOList == null || userLetterDTOList.isEmpty()) {
            return null;
        }
        List<UserLetter> userLetterList = new ArrayList<UserLetter>(userLetterDTOList.size());
        for (UserLetterDTO userLetterDTO : userLetterDTOList) {
            userLetterList.add(toUserLetter(userLetterDTO));
        }
        return userLetterList;
    }

    public static QueryResultODTO<UserLetterODTO> toQueryResult(QueryResultODTO<UserLetter> queryResult) {
        QueryResultODTO<UserLetterODTO> queryResultInfo = new QueryResultODTO<UserLetterODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toUserLetterODTOList(queryResult.getRecords()));
        return queryResultInfo;
        }
}