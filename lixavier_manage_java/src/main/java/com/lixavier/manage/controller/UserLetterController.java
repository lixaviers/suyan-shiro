package com.lixavier.manage.controller;

import com.lixavier.manage.annotation.SysLog;
import com.lixavier.manage.constant.LetterTypeEnum;
import com.lixavier.manage.resp.EnumODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.page.UserLetterPageODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.req.UserLetterQueryDTO;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.service.IUserLetterService;
import com.lixavier.manage.vo.UserVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("userLetter")
@RestController
@Api(value = "userLetter", description = "用户站内信管理接口")
public class UserLetterController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(UserLetterController.class);

    @Autowired
    private IUserLetterService userLetterService;

    @SysLog("删除用户站内信")
    @ApiOperation(value = "deleteUserLetter", notes = "删除用户站内信")
    @RequestMapping(value = "deleteUserLetter", method = {RequestMethod.POST})
    public Result<Integer> deleteUserLetter(@Valid @RequestBody List<Long> idList) {
        Result<Integer> result = Result.newSuccess();
        try {
            UserVO user = getUser();
            result = userLetterService.deleteUserLetter(idList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("阅读站内信")
    @ApiOperation(value = "read", notes = "阅读站内信")
    @RequestMapping(value = "read", method = {RequestMethod.POST})
    public Result<Boolean> getUserLetter(@Valid @RequestBody List<Long> idList) {
        Result<Boolean> result = Result.newSuccess();
        try {
            result = userLetterService.read(idList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "queryUserLetter", notes = "分页获取用户站内信列表信息")
    @RequestMapping(value = "queryUserLetter", method = {RequestMethod.POST})
    Result<QueryResultODTO<UserLetterPageODTO>> queryUserLetter(@Valid @RequestBody UserLetterQueryDTO userLetterQueryDTO) {
        Result<QueryResultODTO<UserLetterPageODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userLetterQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            userLetterQueryDTO.setUserId(getUser().getId());
            result = userLetterService.queryUserLetter(userLetterQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "getLetterTypeCount", notes = "获取站内信类型个数")
    @RequestMapping(value = "getLetterTypeCount", method = {RequestMethod.GET})
    public Result<List<EnumODTO>> getLetterTypeCount() {
        Result<List<EnumODTO>> result = Result.newSuccess();
        try {
            Map<Byte, EnumODTO> map = new HashMap<>();
            for (LetterTypeEnum value : LetterTypeEnum.values()) {
                map.put(value.getValue(), new EnumODTO(value.getValue(), value.getDesc()));
            }
            Result<List<EnumODTO>> listResult = userLetterService.getUserLetterCount(getUser().getId());
            if (listResult.isSuccess() && CollectionUtils.isNotEmpty(listResult.getDataMap())) {
                for (EnumODTO enumODTO : listResult.getDataMap()) {
                    EnumODTO odto = map.get(enumODTO.getValue());
                    odto.setCount(enumODTO.getCount());
                }
            }
            List<EnumODTO> odtoList = new ArrayList<>();
            map.forEach((k, v) -> {
                odtoList.add(v);
            });
            result.setDataMap(odtoList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "getUserNotReadLetterCount", notes = "获取未读站内信个数")
    @RequestMapping(value = "getUserNotReadLetterCount", method = {RequestMethod.GET})
    public Result<Integer> getUserNotReadLetterCount() {
        Result<Integer> result = Result.newSuccess();
        try {
            result = userLetterService.getUserNotReadLetterCount(getUser().getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

}
