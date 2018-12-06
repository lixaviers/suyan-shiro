package com.lixavier.manage.controller;

import com.lixavier.manage.annotation.SysLog;
import com.lixavier.manage.req.GiftCardDTO;
import com.lixavier.manage.req.GiftCardQueryDTO;
import com.lixavier.manage.req.UserGiftCardQueryDTO;
import com.lixavier.manage.resp.GiftCardODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.service.IGiftCardService;
import com.lixavier.manage.util.ValidationUtils;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 礼品卡表
 */
@RestController
@RequestMapping("giftCard")
public class GiftCardController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(GiftCardController.class);

    @Autowired
    private IGiftCardService giftCardService;


    @ApiOperation(value = "queryGiftCard", notes = "分页获取礼品卡列表信息")
    @RequestMapping(value = "queryGiftCard", method = {RequestMethod.POST})
    Result<QueryResultODTO<GiftCardODTO>> queryGiftCard(@Valid @RequestBody GiftCardQueryDTO giftCardQueryDTO) {
        Result<QueryResultODTO<GiftCardODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(giftCardQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = giftCardService.queryGiftCard(giftCardQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "queryUserGiftCards", notes = "获取用户礼品卡信息")
    @RequestMapping(value = "queryUserGiftCards", method = {RequestMethod.POST})
    public Result<QueryResultODTO<GiftCardODTO>> getUserGiftCards(@Valid @RequestBody UserGiftCardQueryDTO dto) {
        Result<QueryResultODTO<GiftCardODTO>> result = Result.newSuccess();
        try {
            dto.setUserId(getUser().getId());
            result = giftCardService.queryUserGiftCards(dto);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }


    @ApiOperation(value = "getGiftCard/{id}", notes = "根据礼品卡ID获取礼品卡信息")
    @RequestMapping(value = "getGiftCard/{id}", method = {RequestMethod.GET})
    public Result<GiftCardODTO> getGiftCard(@PathVariable("id") String id) {
        Result<GiftCardODTO> result = Result.newSuccess();
        try {
            result = giftCardService.getGiftCard(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("创建礼品卡")
    @ApiOperation(value = "createGiftCard", notes = "创建礼品卡")
    @RequestMapping(value = "createGiftCard", method = {RequestMethod.POST})
    public Result<Integer> createGiftCard(@Valid @RequestBody GiftCardDTO giftCardDTO) {
        Result<Integer> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(giftCardDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = giftCardService.createGiftCard(giftCardDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("用户激活礼品卡")
    @ApiOperation(value = "createUserGiftCard", notes = "用户激活礼品卡")
    @RequestMapping(value = "createUserGiftCard", method = {RequestMethod.POST})
    public Result<Integer> createUserGiftCard(@Valid @RequestBody GiftCardDTO giftCardDTO) {
        Result<Integer> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(giftCardDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            giftCardDTO.setUserId(getUser().getId());
            result = giftCardService.createUserGiftCard(giftCardDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("更新礼品卡")
    @ApiOperation(value = "updateGiftCard", notes = "更新礼品卡")
    @RequestMapping(value = "updateGiftCard", method = {RequestMethod.POST})
    public Result<Integer> updateGiftCard(@Valid @RequestBody GiftCardDTO giftCardDTO) {
        Result<Integer> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(giftCardDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = giftCardService.updateGiftCard(giftCardDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;

    }

    @SysLog("删除礼品卡")
    @ApiOperation(value = "deleteGiftCard", notes = "删除礼品卡")
    @RequestMapping(value = "deleteGiftCard", method = {RequestMethod.POST})
    public Result<Integer> delete(@Valid @RequestBody List<String> idList) {
        Result<Integer> result = Result.newSuccess();
        try {
            result = giftCardService.deleteGiftCard(idList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

}
