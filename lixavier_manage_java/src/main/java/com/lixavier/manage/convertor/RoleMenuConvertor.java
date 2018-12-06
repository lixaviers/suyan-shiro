package com.lixavier.manage.convertor;

import com.lixavier.manage.model.RoleMenu;
import com.lixavier.manage.req.RoleMenuDTO;
import com.lixavier.manage.resp.RoleMenuODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层角色与菜单对应关系转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public abstract class RoleMenuConvertor {

    private static final BeanCopier beanCopierForRoleMenuODTO = BeanCopier.create(RoleMenu.class, RoleMenuODTO.class, false);
    private static final BeanCopier beanCopierForRoleMenu = BeanCopier.create(RoleMenuDTO.class, RoleMenu.class, false);

    public static RoleMenuODTO toRoleMenuODTO(RoleMenu roleMenu) {
        if (roleMenu == null) {
            return null;
        }
        RoleMenuODTO roleMenuODTO = new RoleMenuODTO();
        beanCopierForRoleMenuODTO.copy(roleMenu, roleMenuODTO, null);
        return roleMenuODTO;
    }

    public static RoleMenu toRoleMenu(RoleMenuDTO roleMenuDTO) {
        RoleMenu roleMenu = new RoleMenu();
        beanCopierForRoleMenu.copy(roleMenuDTO, roleMenu, null);
        return roleMenu;
    }

    public static List<RoleMenuODTO> toRoleMenuODTOList(List<RoleMenu> roleMenuList) {
        if (roleMenuList == null || roleMenuList.isEmpty()) {
            return null;
        }
        List<RoleMenuODTO> roleMenuInfoList = new ArrayList<RoleMenuODTO>(roleMenuList.size());
        for (RoleMenu roleMenu : roleMenuList) {
            roleMenuInfoList.add(toRoleMenuODTO(roleMenu));
        }
        return roleMenuInfoList;
    }

    public static List<RoleMenu> toRoleMenuList(List<RoleMenuDTO> roleMenuDTOList) {
        if (roleMenuDTOList == null || roleMenuDTOList.isEmpty()) {
            return null;
        }
        List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>(roleMenuDTOList.size());
        for (RoleMenuDTO roleMenuDTO : roleMenuDTOList) {
            roleMenuList.add(toRoleMenu(roleMenuDTO));
        }
        return roleMenuList;
    }

    public static QueryResultODTO<RoleMenuODTO> toQueryResult(QueryResultODTO<RoleMenu> queryResult) {
        QueryResultODTO<RoleMenuODTO> queryResultInfo = new QueryResultODTO<RoleMenuODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toRoleMenuODTOList(queryResult.getRecords()));
        return queryResultInfo;
        }
}