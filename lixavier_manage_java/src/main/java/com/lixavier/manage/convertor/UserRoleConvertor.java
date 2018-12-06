package com.lixavier.manage.convertor;

import com.lixavier.manage.model.UserRole;
import com.lixavier.manage.req.UserRoleDTO;
import com.lixavier.manage.resp.UserRoleODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层用户与角色对应关系转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public abstract class UserRoleConvertor {

    private static final BeanCopier beanCopierForUserRoleODTO = BeanCopier.create(UserRole.class, UserRoleODTO.class, false);
    private static final BeanCopier beanCopierForUserRole = BeanCopier.create(UserRoleDTO.class, UserRole.class, false);

    public static UserRoleODTO toUserRoleODTO(UserRole userRole) {
        if (userRole == null) {
            return null;
        }
        UserRoleODTO userRoleODTO = new UserRoleODTO();
        beanCopierForUserRoleODTO.copy(userRole, userRoleODTO, null);
        return userRoleODTO;
    }

    public static UserRole toUserRole(UserRoleDTO userRoleDTO) {
        UserRole userRole = new UserRole();
        beanCopierForUserRole.copy(userRoleDTO, userRole, null);
        return userRole;
    }

    public static List<UserRoleODTO> toUserRoleODTOList(List<UserRole> userRoleList) {
        if (userRoleList == null || userRoleList.isEmpty()) {
            return null;
        }
        List<UserRoleODTO> userRoleInfoList = new ArrayList<UserRoleODTO>(userRoleList.size());
        for (UserRole userRole : userRoleList) {
            userRoleInfoList.add(toUserRoleODTO(userRole));
        }
        return userRoleInfoList;
    }

    public static List<UserRole> toUserRoleList(List<UserRoleDTO> userRoleDTOList) {
        if (userRoleDTOList == null || userRoleDTOList.isEmpty()) {
            return null;
        }
        List<UserRole> userRoleList = new ArrayList<UserRole>(userRoleDTOList.size());
        for (UserRoleDTO userRoleDTO : userRoleDTOList) {
            userRoleList.add(toUserRole(userRoleDTO));
        }
        return userRoleList;
    }

    public static QueryResultODTO<UserRoleODTO> toQueryResult(QueryResultODTO<UserRole> queryResult) {
        QueryResultODTO<UserRoleODTO> queryResultInfo = new QueryResultODTO<UserRoleODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toUserRoleODTOList(queryResult.getRecords()));
        return queryResultInfo;
        }
}