package com.lixavier.manage.service;

import com.lixavier.manage.req.RoleDTO;
import com.lixavier.manage.req.RoleQueryDTO;
import com.lixavier.manage.resp.RoleODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 角色管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public interface IRoleService {

    /**
     * 
     * 删除角色
     * 
     * @author lixavier
     * @version 1.0.0
     * @param id
     * @return
     */
    Result<Integer> deleteRole(Long id);
    
    /**
     * 
     * 创建角色
     * 
     * @author lixavier
     * @version 1.0.0
     * @param roleDTO
     * @return
     */
    Result<Long> createRole(RoleDTO roleDTO);

    /**
     * 
     * 更新角色
     * 
     * @author lixavier
     * @version 1.0.0
     * @param roleDTO
     * @return
     */
    Result<Integer> updateRole(RoleDTO roleDTO);
    
    /**
     * 
     * 根据ID获取角色信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param id
     * @return
     */
    Result<RoleODTO> getRole(Long id);

    /**
     * 
     * 分页查询角色信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param roleQueryDTO
     * @return
     */
    Result<QueryResultODTO<RoleODTO>> queryRole(RoleQueryDTO roleQueryDTO);

    /**
    * The following custom service interface about dubbo
    */
}