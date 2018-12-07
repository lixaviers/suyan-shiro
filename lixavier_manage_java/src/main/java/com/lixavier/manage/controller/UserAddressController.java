package com.lixavier.manage.controller;

import com.lixavier.manage.annotation.SysLog;
import com.lixavier.manage.convertor.UserAddressConvertor;
import com.lixavier.manage.req.UserAddressDTO;
import com.lixavier.manage.req.UserAddressQueryDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserAddressODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.service.IUserAddressService;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.vo.UserVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("userAddress")
@RestController
@Api(value = "userAddress", description = "用户收货地址管理接口")
public class UserAddressController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(UserAddressController.class);

    @Autowired
    private IUserAddressService userAddressService;

    @SysLog("删除用户收货地址")
    @ApiOperation(value = "deleteUserAddress/{id}", notes = "删除用户收货地址")
    @RequestMapping(value = "deleteUserAddress/{id}", method = {RequestMethod.POST})
    public Result<Integer> deleteUserAddress(@PathVariable Long id) {
        Result<Integer> result = Result.newSuccess();
        try {
            UserVO user = getUser();
            result = userAddressService.deleteUserAddress(id, getUser().getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("创建用户收货地址")
    @ApiOperation(value = "createUserAddress", notes = "创建用户收货地址")
    @RequestMapping(value = "createUserAddress", method = {RequestMethod.POST})
    public Result<Long> createUserAddress(@Valid @RequestBody UserAddressDTO userAddressDTO) {
        Result<Long> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userAddressDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            userAddressDTO.setUserId(getUser().getId());
            result = userAddressService.createUserAddress(userAddressDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("更新用户收货地址")
    @ApiOperation(value = "updateUserAddress", notes = "更新用户收货地址")
    @RequestMapping(value = "updateUserAddress", method = {RequestMethod.POST})
    public Result<Integer> updateUserAddress(@Valid @RequestBody UserAddressDTO userAddressDTO) {
        Result<Integer> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userAddressDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = userAddressService.updateUserAddress(userAddressDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "getUserAddress/{id}", notes = "根据用户收货地址ID获取用户收货地址信息")
    @RequestMapping(value = "getUserAddress/{id}", method = {RequestMethod.GET})
    public Result<UserAddressODTO> getUserAddress(@PathVariable Long id) {
        Result<UserAddressODTO> result = Result.newSuccess();
        try {
            result = userAddressService.getUserAddress(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "queryUserAddress", notes = "分页获取用户收货地址列表信息")
    @RequestMapping(value = "queryUserAddress", method = {RequestMethod.POST})
    Result<QueryResultODTO<UserAddressODTO>> queryUserAddress(@Valid @RequestBody UserAddressQueryDTO userAddressQueryDTO) {
        Result<QueryResultODTO<UserAddressODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userAddressQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            userAddressQueryDTO.setUserId(getUser().getId());
            result = userAddressService.queryUserAddress(userAddressQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }
}
