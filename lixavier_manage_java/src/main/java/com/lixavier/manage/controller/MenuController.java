package com.lixavier.manage.controller;

import com.lixavier.manage.annotation.SysLog;
import com.lixavier.manage.convertor.MenuConvertor;
import com.lixavier.manage.resp.NavODTO;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.req.MenuDTO;
import com.lixavier.manage.req.MenuQueryDTO;
import com.lixavier.manage.resp.MenuODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.service.IMenuService;
import com.lixavier.manage.vo.UserVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("menu")
@RestController
@Api(value = "menu", description = "菜单管理接口")
public class MenuController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuService;

    @SysLog("删除菜单")
    @ApiOperation(value = "deleteMenu/{id}", notes = "删除菜单")
    @RequestMapping(value = "deleteMenu/{id}", method = {RequestMethod.POST})
    public Result<Integer> deleteMenu(@PathVariable Long id) {
        Result<Integer> result = Result.newSuccess();
        try {
            UserVO user = getUser();
            result = menuService.deleteMenu(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("创建菜单")
    @ApiOperation(value = "createMenu", notes = "创建菜单")
    @RequestMapping(value = "createMenu", method = {RequestMethod.POST})
    public Result<Long> createMenu(@Valid @RequestBody MenuDTO menuDTO) {
        Result<Long> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(menuDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = menuService.createMenu(menuDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }


    @SysLog("更新菜单")
    @ApiOperation(value = "updateMenu", notes = "更新菜单")
    @RequestMapping(value = "updateMenu", method = {RequestMethod.POST})
    public Result<Integer> updateMenu(@Valid @RequestBody MenuDTO menuDTO) {
        Result<Integer> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(menuDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = menuService.updateMenu(menuDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }


    @ApiOperation(value = "getMenu/{id}", notes = "根据菜单ID获取菜单信息")
    @RequestMapping(value = "getMenu/{id}", method = { RequestMethod.GET })
    public Result<MenuODTO> getMenu(@PathVariable Long id ){
        Result<MenuODTO> result = Result.newSuccess();
        try {
            result = menuService.getMenu(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "queryMenu", notes = "分页获取菜单列表信息")
    @RequestMapping(value = "queryMenu", method = {RequestMethod.POST})
    Result<QueryResultODTO<MenuODTO>> queryMenu(@Valid @RequestBody MenuQueryDTO menuQueryDTO) {
        Result<QueryResultODTO<MenuODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(menuQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = menuService.queryMenu(menuQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "getMenu/role/{roleId}", notes = "获取角色菜单列表信息")
    @RequestMapping(value = "getMenu/role/{roleId}", method = {RequestMethod.GET})
    Result<List<MenuODTO>> getMenuByRoleId(@PathVariable Long roleId) {
        Result<List<MenuODTO>> result = Result.newSuccess();
        try {
            result = menuService.getResourcesByRoleId(roleId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }


    @ApiOperation(value = "nav", notes = "获取用户资源列表信息")
    @RequestMapping(value = "nav", method = {RequestMethod.GET})
    public Result<NavODTO> nav() {
        Result<NavODTO> result = Result.newSuccess();
        try {
            UserVO user = getUser();
            result = menuService.getNav(user.getIsAdmin(), user.getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }
}
