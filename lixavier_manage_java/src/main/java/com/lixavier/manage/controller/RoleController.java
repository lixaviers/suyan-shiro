package com.lixavier.manage.controller;

import com.lixavier.manage.annotation.SysLog;
import com.lixavier.manage.convertor.RoleConvertor;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.req.RoleDTO;
import com.lixavier.manage.req.RoleQueryDTO;
import com.lixavier.manage.resp.RoleODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.service.IRoleService;
import com.lixavier.manage.vo.UserVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("role")
@RestController
@Api(value = "role", description = "角色管理接口")
public class RoleController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;

    @SysLog("删除角色")
    @ApiOperation(value = "deleteRole/{id}", notes = "删除角色")
    @RequestMapping(value = "deleteRole/{id}", method = {RequestMethod.POST})
    public Result<Integer> deleteRole(@PathVariable Long id) {
        Result<Integer> result = Result.newSuccess();
        try {
            result = roleService.deleteRole(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("创建角色")
    @ApiOperation(value = "createRole", notes = "创建角色")
    @RequestMapping(value = "createRole", method = {RequestMethod.POST})
    public Result<Long> createRole(@Valid @RequestBody RoleDTO roleDTO) {
        Result<Long> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(roleDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = roleService.createRole(roleDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("更新角色")
    @ApiOperation(value = "updateRole", notes = "更新角色")
    @RequestMapping(value = "updateRole", method = {RequestMethod.POST})
    public Result<Integer> updateRole(@Valid @RequestBody RoleDTO roleDTO) {
        Result<Integer> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(roleDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = roleService.updateRole(roleDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "getRole/{id}", notes = "根据角色ID获取角色信息")
    @RequestMapping(value = "getRole/{id}", method = {RequestMethod.GET})
    public Result<RoleODTO> getRole(@PathVariable Long id) {
        Result<RoleODTO> result = Result.newSuccess();
        try {
            result = roleService.getRole(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "queryRole", notes = "分页获取角色列表信息")
    @RequestMapping(value = "queryRole", method = {RequestMethod.POST})
    Result<QueryResultODTO<RoleODTO>> queryRole(@Valid @RequestBody RoleQueryDTO roleQueryDTO) {
        Result<QueryResultODTO<RoleODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(roleQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = roleService.queryRole(roleQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }
}
