package com.lixavier.manage.convertor;

import com.lixavier.manage.model.Menu;
import com.lixavier.manage.req.MenuDTO;
import com.lixavier.manage.resp.MenuODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层菜单转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public abstract class MenuConvertor {

    private static final BeanCopier beanCopierForMenuODTO = BeanCopier.create(Menu.class, MenuODTO.class, false);
    private static final BeanCopier beanCopierForMenu = BeanCopier.create(MenuDTO.class, Menu.class, false);

    public static MenuODTO toMenuODTO(Menu menu) {
        if (menu == null) {
            return null;
        }
        MenuODTO menuODTO = new MenuODTO();
        beanCopierForMenuODTO.copy(menu, menuODTO, null);
        menuODTO.setChildren(MenuConvertor.toMenuODTOList(menu.getChildren()));
        return menuODTO;
    }

    public static Menu toMenu(MenuDTO menuDTO) {
        Menu menu = new Menu();
        beanCopierForMenu.copy(menuDTO, menu, null);
        return menu;
    }

    public static List<MenuODTO> toMenuODTOList(List<Menu> menuList) {
        if (menuList == null || menuList.isEmpty()) {
            return null;
        }
        List<MenuODTO> menuInfoList = new ArrayList<MenuODTO>(menuList.size());
        for (Menu menu : menuList) {
            menuInfoList.add(toMenuODTO(menu));
        }
        return menuInfoList;
    }

    public static List<Menu> toMenuList(List<MenuDTO> menuDTOList) {
        if (menuDTOList == null || menuDTOList.isEmpty()) {
            return null;
        }
        List<Menu> menuList = new ArrayList<Menu>(menuDTOList.size());
        for (MenuDTO menuDTO : menuDTOList) {
            menuList.add(toMenu(menuDTO));
        }
        return menuList;
    }

    public static QueryResultODTO<MenuODTO> toQueryResult(QueryResultODTO<Menu> queryResult) {
        QueryResultODTO<MenuODTO> queryResultInfo = new QueryResultODTO<MenuODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toMenuODTOList(queryResult.getRecords()));
        return queryResultInfo;
    }
}