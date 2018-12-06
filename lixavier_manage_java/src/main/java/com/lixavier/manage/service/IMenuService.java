package com.lixavier.manage.service;

import com.lixavier.manage.req.MenuDTO;
import com.lixavier.manage.req.MenuQueryDTO;
import com.lixavier.manage.resp.MenuODTO;
import com.lixavier.manage.resp.NavODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 菜单管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public interface IMenuService {

    /**
     * 
     * 删除菜单
     * 
     * @author lixavier
     * @version 1.0.0
     * @param id
     * @return
     */
    Result<Integer> deleteMenu(Long id);
    
    /**
     * 
     * 创建菜单
     * 
     * @author lixavier
     * @version 1.0.0
     * @param menuDTO
     * @return
     */
    Result<Long> createMenu(MenuDTO menuDTO);

    /**
     *
     * 批量创建菜单
     *
     * @author lixavier
     * @version 1.0.0
     * @param menuDTOs
     * @return
     */
    Result<Integer> batchCreate(List<MenuDTO> menuDTOs);

    /**
     * 
     * 更新菜单
     * 
     * @author lixavier
     * @version 1.0.0
     * @param menuDTO
     * @return
     */
    Result<Integer> updateMenu(MenuDTO menuDTO);
    
    /**
     * 
     * 根据ID获取菜单信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param id
     * @return
     */
    Result<MenuODTO> getMenu(Long id);


    /**
     * 
     * 分页查询菜单信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param menuQueryDTO
     * @return
     */
    Result<QueryResultODTO<MenuODTO>> queryMenu(MenuQueryDTO menuQueryDTO);


    Result<List<MenuODTO>> getResourcesByRoleId(Long roleId);



    Result<NavODTO> getNav(boolean isAdmin, Long userId);


}