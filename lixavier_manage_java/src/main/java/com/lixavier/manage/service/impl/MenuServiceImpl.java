package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.MenuBiz;
import com.lixavier.manage.convertor.MenuConvertor;
import com.lixavier.manage.model.Menu;
import com.lixavier.manage.req.MenuDTO;
import com.lixavier.manage.req.MenuQueryDTO;
import com.lixavier.manage.resp.MenuODTO;
import com.lixavier.manage.resp.NavODTO;
import com.lixavier.manage.service.IMenuService;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lixavier.manage.validate.MenuValidate.validateForCreate;
import static com.lixavier.manage.validate.MenuValidate.validateForUpdate;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 菜单管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("menuService")
public class MenuServiceImpl implements IMenuService {
    private final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuBiz menuBiz;

    /**
     * 删除菜单
     *
     * @param id
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> deleteMenu(Long id) {
        Result<Integer> result = Result.newSuccess();
        result.setDataMap(menuBiz.deleteMenu(id));
        return result;
    }

    /**
     * 创建菜单
     *
     * @param menuDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Long> createMenu(MenuDTO menuDTO) {
        Result<Long> result = Result.newSuccess();
        Menu menu = MenuConvertor.toMenu(menuDTO);
        if (!validateForCreate(menu, result)) {
            return result;
        }
        result.setDataMap(menuBiz.createMenu(menu));
        return result;
    }

    @Override
    public Result<Integer> batchCreate(List<MenuDTO> menuDTOs) {
        Result<Integer> result = Result.newSuccess();
        List<Menu> items = MenuConvertor.toMenuList(menuDTOs);
        result.setDataMap(menuBiz.batchCreateMenu(items));
        return result;
    }

    /**
     * 更新菜单
     *
     * @param menuDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> updateMenu(MenuDTO menuDTO) {
        Result<Integer> result = Result.newSuccess();
        Menu menu = MenuConvertor.toMenu(menuDTO);
        if (!validateForUpdate(menu, result)) {
            return result;
        }
        result.setDataMap(menuBiz.updateMenu(menu));
        return result;
    }

    /**
     * 根据ID获取菜单信息
     *
     * @param id
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<MenuODTO> getMenu(Long id) {
        Result<MenuODTO> result = Result.newSuccess();
        Menu menu = menuBiz.getMenu(id);
        MenuODTO menuODTO = MenuConvertor.toMenuODTO(menu);
        result.setDataMap(menuODTO);
        return result;
    }

    /**
     * 分页查询菜单信息
     *
     * @param menuQueryDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<QueryResultODTO<MenuODTO>> queryMenu(MenuQueryDTO menuQueryDTO) {
        Result<QueryResultODTO<MenuODTO>> result = Result.newSuccess();

        QueryResultODTO<Menu> resultInfo = menuBiz.queryMenu(menuQueryDTO);
        result.setDataMap(MenuConvertor.toQueryResult(resultInfo));

        return result;
    }

    @Override
    public Result<List<MenuODTO>> getResourcesByRoleId(Long roleId) {
        Result<List<MenuODTO>> result = Result.newSuccess();
        result.setDataMap(MenuConvertor.toMenuODTOList(menuBiz.getResourcesByRoleId(roleId)));
        return result;
    }

    @Override
    public Result<NavODTO> getNav(boolean isAdmin, Long userId) {
        Result<NavODTO> result = Result.newSuccess();
        result.setDataMap(menuBiz.getNav(isAdmin, userId));
        return result;
    }

}