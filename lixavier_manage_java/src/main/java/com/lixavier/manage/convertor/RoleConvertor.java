package com.lixavier.manage.convertor;

import com.lixavier.manage.model.Role;
import com.lixavier.manage.req.RoleDTO;
import com.lixavier.manage.resp.RoleODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层角色转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public abstract class RoleConvertor {

    private static final BeanCopier beanCopierForRoleODTO = BeanCopier.create(Role.class, RoleODTO.class, false);
    private static final BeanCopier beanCopierForRole = BeanCopier.create(RoleDTO.class, Role.class, false);

    public static RoleODTO toRoleODTO(Role role) {
        if (role == null) {
            return null;
        }
        RoleODTO roleODTO = new RoleODTO();
        beanCopierForRoleODTO.copy(role, roleODTO, null);
        return roleODTO;
    }

    public static Role toRole(RoleDTO roleDTO) {
        Role role = new Role();
        beanCopierForRole.copy(roleDTO, role, null);
        return role;
    }

    public static List<RoleODTO> toRoleODTOList(List<Role> roleList) {
        if (roleList == null || roleList.isEmpty()) {
            return null;
        }
        List<RoleODTO> roleInfoList = new ArrayList<RoleODTO>(roleList.size());
        for (Role role : roleList) {
            roleInfoList.add(toRoleODTO(role));
        }
        return roleInfoList;
    }

    public static List<Role> toRoleList(List<RoleDTO> roleDTOList) {
        if (roleDTOList == null || roleDTOList.isEmpty()) {
            return null;
        }
        List<Role> roleList = new ArrayList<Role>(roleDTOList.size());
        for (RoleDTO roleDTO : roleDTOList) {
            roleList.add(toRole(roleDTO));
        }
        return roleList;
    }

    public static QueryResultODTO<RoleODTO> toQueryResult(QueryResultODTO<Role> queryResult) {
        QueryResultODTO<RoleODTO> queryResultInfo = new QueryResultODTO<RoleODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toRoleODTOList(queryResult.getRecords()));
        return queryResultInfo;
    }
}