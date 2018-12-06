package com.lixavier.manage.controller;

import com.lixavier.manage.constant.ChannelEnum;
import com.lixavier.manage.constant.LetterTypeEnum;
import com.lixavier.manage.constant.TransactionTypeEnum;
import com.lixavier.manage.resp.EnumODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.req.UserAccountDetailQueryDTO;
import com.lixavier.manage.resp.UserAccountDetailODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.service.IUserAccountDetailService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("userAccountDetail")
@RestController
@Api(value = "userAccountDetail", description = "用户账户明细管理接口")
public class UserAccountDetailController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(UserAccountDetailController.class);

    @Autowired
    private IUserAccountDetailService userAccountDetailService;


    @ApiOperation(value = "getTransactionTypeList", notes = "获取交易类型列表")
    @RequestMapping(value = "getTransactionTypeList", method = {RequestMethod.GET})
    public Result<List<EnumODTO>> getTransactionTypeList() {
        Result<List<EnumODTO>> result = Result.newSuccess();
        try {
            List<EnumODTO> odtoList = new ArrayList<>();
            for (TransactionTypeEnum value : TransactionTypeEnum.values()) {
                odtoList.add(new EnumODTO(value.getValue(), value.getDesc()));
            }
            result.setDataMap(odtoList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "getChannelList", notes = "获取渠道类型列表")
    @RequestMapping(value = "getChannelList", method = {RequestMethod.GET})
    public Result<List<EnumODTO>> getChannelList() {
        Result<List<EnumODTO>> result = Result.newSuccess();
        try {
            List<EnumODTO> odtoList = new ArrayList<>();
            for (ChannelEnum value : ChannelEnum.values()) {
                odtoList.add(new EnumODTO(value.getValue(), value.getDesc()));
            }
            result.setDataMap(odtoList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "queryUserAccountDetail", notes = "分页获取用户账户明细列表信息")
    @RequestMapping(value = "queryUserAccountDetail", method = {RequestMethod.POST})
    Result<QueryResultODTO<UserAccountDetailODTO>> queryUserAccountDetail(@Valid @RequestBody UserAccountDetailQueryDTO userAccountDetailQueryDTO) {
        Result<QueryResultODTO<UserAccountDetailODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userAccountDetailQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            userAccountDetailQueryDTO.setUserId(getUser().getId());
            result = userAccountDetailService.queryUserAccountDetail(userAccountDetailQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }
}
