package com.lixavier.manage.convertor;

import com.lixavier.manage.model.UserCart;
import com.lixavier.manage.req.UserCartDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserCartODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层用户购物车转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public abstract class UserCartConvertor {

    private static final BeanCopier beanCopierForUserCartODTO = BeanCopier.create(UserCart.class, UserCartODTO.class, false);
    private static final BeanCopier beanCopierForUserCart = BeanCopier.create(UserCartDTO.class, UserCart.class, false);

    public static UserCartODTO toUserCartODTO(UserCart userCart) {
        if (userCart == null) {
            return null;
        }
        UserCartODTO userCartODTO = new UserCartODTO();
        beanCopierForUserCartODTO.copy(userCart, userCartODTO, null);
        return userCartODTO;
    }

    public static UserCart toUserCart(UserCartDTO userCartDTO) {
        UserCart userCart = new UserCart();
        beanCopierForUserCart.copy(userCartDTO, userCart, null);
        return userCart;
    }

    public static List<UserCartODTO> toUserCartODTOList(List<UserCart> userCartList) {
        if (userCartList == null || userCartList.isEmpty()) {
            return null;
        }
        List<UserCartODTO> userCartInfoList = new ArrayList<UserCartODTO>(userCartList.size());
        for (UserCart userCart : userCartList) {
            userCartInfoList.add(toUserCartODTO(userCart));
        }
        return userCartInfoList;
    }

    public static List<UserCart> toUserCartList(List<UserCartDTO> userCartDTOList) {
        if (userCartDTOList == null || userCartDTOList.isEmpty()) {
            return null;
        }
        List<UserCart> userCartList = new ArrayList<UserCart>(userCartDTOList.size());
        for (UserCartDTO userCartDTO : userCartDTOList) {
            userCartList.add(toUserCart(userCartDTO));
        }
        return userCartList;
    }

    public static QueryResultODTO<UserCartODTO> toQueryResult(QueryResultODTO<UserCart> queryResult) {
        QueryResultODTO<UserCartODTO> queryResultInfo = new QueryResultODTO<UserCartODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toUserCartODTOList(queryResult.getRecords()));
        return queryResultInfo;
        }
}