package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.RoleBiz;
import com.lixavier.manage.convertor.RoleConvertor;
import com.lixavier.manage.model.Role;
import com.lixavier.manage.req.RoleDTO;
import com.lixavier.manage.req.RoleQueryDTO;
import com.lixavier.manage.resp.RoleODTO;
import com.lixavier.manage.service.IRoleService;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lixavier.manage.validate.RoleValidate.validateForCreate;
import static com.lixavier.manage.validate.RoleValidate.validateForUpdate;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 角色管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    private final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleBiz roleBiz;

    /**
     * 删除角色
     *
     * @param id
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> deleteRole(Long id) {
        Result<Integer> result = Result.newSuccess();
        result.setDataMap(roleBiz.deleteRole(id));
        return result;
    }

    /**
     * 创建角色
     *
     * @param roleDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Long> createRole(RoleDTO roleDTO) {
        Result<Long> result = Result.newSuccess();
        Role role = RoleConvertor.toRole(roleDTO);
        if (!validateForCreate(role, result)) {
            return result;
        }
        result.setDataMap(roleBiz.createRole(role));
        return result;
    }

    /**
     * 更新角色
     *
     * @param roleDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> updateRole(RoleDTO roleDTO) {
        Result<Integer> result = Result.newSuccess();
        Role role = null;
        role = RoleConvertor.toRole(roleDTO);
        if (!validateForUpdate(role, result)) {
            return result;
        }
        result.setDataMap(roleBiz.updateRole(role));
        return result;
    }

    /**
     * 根据ID获取角色信息
     *
     * @param id
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<RoleODTO> getRole(Long id) {
        Result<RoleODTO> result = Result.newSuccess();
        Role role = roleBiz.getRole(id);
        RoleODTO roleODTO = RoleConvertor.toRoleODTO(role);
        result.setDataMap(roleODTO);
        return result;
    }

    /**
     * 分页查询角色信息
     *
     * @param roleQueryDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<QueryResultODTO<RoleODTO>> queryRole(RoleQueryDTO roleQueryDTO) {
        Result<QueryResultODTO<RoleODTO>> result = Result.newSuccess();

        QueryResultODTO<Role> resultInfo = roleBiz.queryRole(roleQueryDTO);
        result.setDataMap(RoleConvertor.toQueryResult(resultInfo));

        return result;
    }
}