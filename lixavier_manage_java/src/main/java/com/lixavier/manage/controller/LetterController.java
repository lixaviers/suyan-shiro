package com.lixavier.manage.controller;

import com.lixavier.manage.constant.LetterTypeEnum;
import com.lixavier.manage.convertor.LetterConvertor;
import com.lixavier.manage.resp.EnumODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.req.LetterDTO;
import com.lixavier.manage.req.LetterQueryDTO;
import com.lixavier.manage.resp.LetterODTO;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.service.ILetterService;
import com.lixavier.manage.vo.UserVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("letter")
@RestController
@Api(value = "letter", description = "站内信管理接口")
public class LetterController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(LetterController.class);

    @Autowired
    private ILetterService letterService;

    @ApiOperation(value = "deleteLetter/{id}", notes = "删除站内信")
    @RequestMapping(value = "deleteLetter/{id}", method = {RequestMethod.POST})
    public Result<Integer> deleteLetter(@PathVariable Long id) {
        Result<Integer> result = Result.newSuccess();
        try {
            UserVO user = getUser();
            result = letterService.deleteLetter(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "createLetter", notes = "创建站内信")
    @RequestMapping(value = "createLetter", method = {RequestMethod.POST})
    public Result<Long> createLetter(@Valid @RequestBody LetterDTO letterDTO) {
        Result<Long> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(letterDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = letterService.createLetter(letterDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "updateLetter", notes = "更新站内信")
    @RequestMapping(value = "updateLetter", method = {RequestMethod.POST})
    public Result<Integer> updateLetter(@Valid @RequestBody LetterDTO letterDTO) {
        Result<Integer> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(letterDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = letterService.updateLetter(letterDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "getLetter/{id}", notes = "根据站内信ID获取站内信信息")
    @RequestMapping(value = "getLetter/{id}", method = {RequestMethod.GET})
    public Result<LetterODTO> getLetter(@PathVariable Long id) {
        Result<LetterODTO> result = Result.newSuccess();
        try {
            result = letterService.getLetter(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "queryLetter", notes = "分页获取站内信列表信息")
    @RequestMapping(value = "queryLetter", method = {RequestMethod.POST})
    Result<QueryResultODTO<LetterODTO>> queryLetter(@Valid @RequestBody LetterQueryDTO letterQueryDTO) {
        Result<QueryResultODTO<LetterODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(letterQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = letterService.queryLetter(letterQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }


    @ApiOperation(value = "getLetterTypes", notes = "获取站内信类型列表")
    @RequestMapping(value = "getLetterTypes", method = {RequestMethod.GET})
    public Result<List<EnumODTO>> getLetterTypes() {
        Result<List<EnumODTO>> result = Result.newSuccess();
        try {
            List<EnumODTO> odtoList = new ArrayList<>();
            for (LetterTypeEnum value : LetterTypeEnum.values()) {
                odtoList.add(new EnumODTO(value.getValue(), value.getDesc()));
            }
            result.setDataMap(odtoList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }


}
