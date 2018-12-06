package com.lixavier.manage.convertor;

import com.lixavier.manage.model.UserAddress;
import com.lixavier.manage.req.UserAddressDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserAddressODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层用户收货地址转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-02>
 * @Modify Date: <2018-12-02>
 * @Version: <1.0>
 */
public abstract class UserAddressConvertor {

    private static final BeanCopier beanCopierForUserAddressODTO = BeanCopier.create(UserAddress.class, UserAddressODTO.class, false);
    private static final BeanCopier beanCopierForUserAddress = BeanCopier.create(UserAddressDTO.class, UserAddress.class, false);

    public static UserAddressODTO toUserAddressODTO(UserAddress userAddress) {
        if (userAddress == null) {
            return null;
        }
        UserAddressODTO userAddressODTO = new UserAddressODTO();
        beanCopierForUserAddressODTO.copy(userAddress, userAddressODTO, null);
        return userAddressODTO;
    }

    public static UserAddress toUserAddress(UserAddressDTO userAddressDTO) {
        UserAddress userAddress = new UserAddress();
        beanCopierForUserAddress.copy(userAddressDTO, userAddress, null);
        return userAddress;
    }

    public static List<UserAddressODTO> toUserAddressODTOList(List<UserAddress> userAddressList) {
        if (userAddressList == null || userAddressList.isEmpty()) {
            return null;
        }
        List<UserAddressODTO> userAddressInfoList = new ArrayList<UserAddressODTO>(userAddressList.size());
        for (UserAddress userAddress : userAddressList) {
            userAddressInfoList.add(toUserAddressODTO(userAddress));
        }
        return userAddressInfoList;
    }

    public static List<UserAddress> toUserAddressList(List<UserAddressDTO> userAddressDTOList) {
        if (userAddressDTOList == null || userAddressDTOList.isEmpty()) {
            return null;
        }
        List<UserAddress> userAddressList = new ArrayList<UserAddress>(userAddressDTOList.size());
        for (UserAddressDTO userAddressDTO : userAddressDTOList) {
            userAddressList.add(toUserAddress(userAddressDTO));
        }
        return userAddressList;
    }

    public static QueryResultODTO<UserAddressODTO> toQueryResult(QueryResultODTO<UserAddress> queryResult) {
        QueryResultODTO<UserAddressODTO> queryResultInfo = new QueryResultODTO<UserAddressODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toUserAddressODTOList(queryResult.getRecords()));
        return queryResultInfo;
        }
}