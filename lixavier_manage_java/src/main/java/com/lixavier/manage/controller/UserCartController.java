package com.lixavier.manage.controller;

import com.lixavier.manage.resp.GoodsODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.req.UserCartDTO;
import com.lixavier.manage.req.UserCartQueryDTO;
import com.lixavier.manage.resp.UserCartODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.service.IUserCartService;
import com.lixavier.manage.vo.UserVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("userCart")
@RestController
@Api(value = "userCart", description = "用户购物车管理接口")
public class UserCartController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(UserCartController.class);

    @Autowired
    private IUserCartService userCartService;

    @ApiOperation(value = "deleteUserCart", notes = "删除用户购物车")
    @RequestMapping(value = "deleteUserCart", method = {RequestMethod.POST})
    public Result<Integer> deleteUserCart(@Valid @RequestBody List<Long> idList) {
        Result<Integer> result = Result.newSuccess();
        try {
            UserVO user = getUser();
            result = userCartService.deleteUserCart(idList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "createUserCart", notes = "创建用户购物车")
    @RequestMapping(value = "createUserCart", method = {RequestMethod.POST})
    public Result<Long> createUserCart(@Valid @RequestBody UserCartDTO userCartDTO) {
        Result<Long> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userCartDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            userCartDTO.setUserId(getUser().getId());
            result = userCartService.createUserCart(userCartDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "updateUserCart", notes = "更新用户购物车")
    @RequestMapping(value = "updateUserCart", method = {RequestMethod.POST})
    public Result<Integer> updateUserCart(@Valid @RequestBody UserCartDTO userCartDTO) {
        Result<Integer> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userCartDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            userCartDTO.setUserId(getUser().getId());
            result = userCartService.updateUserCart(userCartDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "queryUserCart", notes = "分页获取用户购物车列表信息")
    @RequestMapping(value = "queryUserCart", method = {RequestMethod.POST})
    Result<QueryResultODTO<GoodsODTO>> queryUserCart(@Valid @RequestBody UserCartQueryDTO userCartQueryDTO) {
        Result<QueryResultODTO<GoodsODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userCartQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            userCartQueryDTO.setUserId(getUser().getId());
            result = userCartService.queryUserCart(userCartQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }
}
