package com.lixavier.manage.biz;

import com.lixavier.manage.constant.MenuTypeEnum;
import com.lixavier.manage.convertor.MenuConvertor;
import com.lixavier.manage.dao.MenuMapper;
import com.lixavier.manage.exception.CommonBizException;
import com.lixavier.manage.model.Menu;
import com.lixavier.manage.model.MenuExample;
import com.lixavier.manage.req.MenuQueryDTO;
import com.lixavier.manage.resp.NavODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.result.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层菜单管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("menuBiz")
public class MenuBiz {
    private final Logger logger = LoggerFactory.getLogger(MenuBiz.class);

    @Autowired
    MenuMapper menuMapper;

    /**
     * 逻辑删除菜单
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteMenu(Long id) {
        int result = menuMapper.logicalDeleteByPrimaryKey(id);
        return result;
    }

    /**
     * 创建菜单
     *
     * @param menu
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createMenu(Menu menu) {
        menu.setIsDeleted(false);
        menu.setCreateTime(null);
        menu.setUpdateTime(null);
        menuMapper.insertSelective(menu);
        return menu.getId();
    }

    /**
     * 批量创建
     *
     * @param menuList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public int batchCreateMenu(List<Menu> menuList) {
        return menuMapper.insertBatch(menuList);
    }

    /**
     * 更新菜单
     *
     * @param menu
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer updateMenu(Menu menu) {
        Integer result = null;
        Menu menuLast = menuMapper.selectByPrimaryKeyForUpdate(menu.getId());
        if (menuLast == null) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "菜单");
        }
        menu.setIsDeleted(false);
        menu.setCreateTime(null);
        menu.setUpdateTime(null);
        result = menuMapper.updateByPrimaryKeySelective(menu);
        return result;
    }

    /**
     * 根据ID获取菜单信息
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public Menu getMenu(Long id) {
        Menu menu = menuMapper.selectByPrimaryKey(id);
        if (menu == null || menu.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "菜单");
        }
        return menu;
    }

    /**
     * 分页查询菜单信息
     *
     * @param menuQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<Menu> queryMenu(MenuQueryDTO menuQuery) {
        QueryResultODTO<Menu> queryResult = new QueryResultODTO<Menu>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(menuQuery.getPageNo(), menuQuery.getPageSize());
        List<Menu> menuList = menuMapper.queryMenu(menuQuery);
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(menuList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(menuList);
        return queryResult;
    }

    @Transactional(readOnly = true)
    public List<Menu> getResourcesByRoleId(Long roleId) {
        return menuMapper.getResourcesByRoleId(roleId);
    }

    @Transactional(readOnly = true)
    public NavODTO getNav(boolean isAdmin, Long userId) {
        NavODTO navODTO = new NavODTO();
        navODTO.setIsAdmin(isAdmin);
        if (isAdmin) {
            MenuExample example = new MenuExample();
            example.createCriteria().andTypeEqualTo(MenuTypeEnum.MENU.getValue()).andIsDeletedEqualTo(false);
            example.setOrderByClause("order_num, id");
            navODTO.setMenuList(MenuConvertor.toMenuODTOList(menuMapper.selectByExample(example)));
        } else {
            navODTO.setMenuList(MenuConvertor.toMenuODTOList(menuMapper.getUserMenuList(userId, MenuTypeEnum.MENU.getValue())));
            navODTO.setPermissions(menuMapper.getUserPermissions(userId));
        }
        return navODTO;
    }


    @Transactional(readOnly = true)
    public List<Menu> getUserMenuList(Long userId, Byte type) {
        return menuMapper.getUserMenuList(userId, type);
    }


}