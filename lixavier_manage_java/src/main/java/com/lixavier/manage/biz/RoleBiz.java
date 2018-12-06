package com.lixavier.manage.biz;

import com.lixavier.manage.dao.RoleMapper;
import com.lixavier.manage.exception.CommonBizException;
import com.lixavier.manage.model.Role;
import com.lixavier.manage.model.RoleMenu;
import com.lixavier.manage.req.RoleQueryDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.result.ResultCode;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层角色管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("roleBiz")
public class RoleBiz {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleMenuBiz roleMenuBiz;

    /**
     * 逻辑删除角色
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteRole(Long id) {
        return roleMapper.logicalDeleteByPrimaryKey(id);
    }

    /**
     * 创建角色
     *
     * @param role
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createRole(Role role) {
        role.setIsDeleted(false);
        role.setCreateTime(null);
        role.setUpdateTime(null);
        roleMapper.insertSelective(role);

        createRoleMenu(role);
        return role.getId();
    }

    private void createRoleMenu(Role role) {
        if (CollectionUtils.isNotEmpty(role.getMenuIdList())) {
            List<RoleMenu> roleMenuList = new ArrayList<>();
            for (Long menuId : role.getMenuIdList()) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(role.getId());
                roleMenuList.add(roleMenu);
            }
            roleMenuBiz.batchCreateRoleMenu(roleMenuList);
        }
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer updateRole(Role role) {
        Role roleLast = roleMapper.selectByPrimaryKeyForUpdate(role.getId());
        if (roleLast == null) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "角色");
        }
        role.setIsDeleted(false);
        role.setCreateTime(null);
        role.setUpdateTime(null);
        roleMapper.updateByPrimaryKeySelective(role);

        // 删除角色菜单
        roleMenuBiz.deleteRoleMenu(role.getId());

        // 创建角色菜单
        createRoleMenu(role);
        return 1;
    }

    /**
     * 根据ID获取角色信息
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public Role getRole(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        if (role == null || role.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "角色");
        }
        role.setMenuIdList(roleMapper.getMenuIdListByRoleId(id));
        return role;
    }

    /**
     * 分页查询角色信息
     *
     * @param roleQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<Role> queryRole(RoleQueryDTO roleQuery) {
        QueryResultODTO<Role> queryResult = new QueryResultODTO<Role>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(roleQuery.getPageNo(), roleQuery.getPageSize());
        List<Role> roleList = roleMapper.queryRole(roleQuery);
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(roleList);
        return queryResult;
    }

}