package com.lixavier.manage.biz;

import com.lixavier.manage.dao.RoleMenuMapper;
import com.lixavier.manage.model.RoleMenu;
import com.lixavier.manage.model.RoleMenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层角色与菜单对应关系管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("roleMenuBiz")
public class RoleMenuBiz {

    @Autowired
    RoleMenuMapper roleMenuMapper;

    /**
     * 删除角色与菜单对应关系
     *
     * @param roleId
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteRoleMenu(Long roleId) {
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return roleMenuMapper.deleteByExample(example);
    }

    /**
     * 批量创建
     *
     * @param roleMenuList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public int batchCreateRoleMenu(List<RoleMenu> roleMenuList) {
        return roleMenuMapper.insertBatch(roleMenuList);
    }


}