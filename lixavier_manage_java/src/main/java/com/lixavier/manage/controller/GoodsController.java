package com.lixavier.manage.controller;

import com.lixavier.manage.annotation.SysLog;
import com.lixavier.manage.convertor.GoodsConvertor;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.req.GoodsDTO;
import com.lixavier.manage.req.GoodsQueryDTO;
import com.lixavier.manage.resp.GoodsODTO;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.service.IGoodsService;
import com.lixavier.manage.vo.UserVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("goods")
@RestController
@Api(value = "goods", description = "商品管理接口")
public class GoodsController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private IGoodsService goodsService;

    @SysLog("删除商品")
    @ApiOperation(value = "deleteGoods/{id}", notes = "删除商品")
    @RequestMapping(value = "deleteGoods/{id}", method = {RequestMethod.POST})
    public Result<Integer> deleteGoods(@PathVariable Long id) {
        Result<Integer> result = Result.newSuccess();
        try {
            UserVO user = getUser();
            result = goodsService.deleteGoods(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("创建商品")
    @ApiOperation(value = "createGoods", notes = "创建商品")
    @RequestMapping(value = "createGoods", method = {RequestMethod.POST})
    public Result<Long> createGoods(@Valid @RequestBody GoodsDTO goodsDTO) {
        Result<Long> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(goodsDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            UserVO user = getUser();
            result = goodsService.createGoods(goodsDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("更新商品")
    @ApiOperation(value = "updateGoods", notes = "更新商品")
    @RequestMapping(value = "updateGoods", method = {RequestMethod.POST})
    public Result<Integer> updateGoods(@Valid @RequestBody GoodsDTO goodsDTO) {
        Result<Integer> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(goodsDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = goodsService.updateGoods(goodsDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "getGoods/{id}", notes = "根据商品ID获取商品信息")
    @RequestMapping(value = "getGoods/{id}", method = {RequestMethod.GET})
    public Result<GoodsODTO> getGoods(@PathVariable Long id) {
        Result<GoodsODTO> result = Result.newSuccess();
        try {
            result = goodsService.getGoods(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "queryGoods", notes = "分页获取商品列表信息")
    @RequestMapping(value = "queryGoods", method = {RequestMethod.POST})
    Result<QueryResultODTO<GoodsODTO>> queryGoods(@Valid @RequestBody GoodsQueryDTO goodsQueryDTO) {
        Result<QueryResultODTO<GoodsODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(goodsQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = goodsService.queryGoods(goodsQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "queryGoodsList", notes = "分页获取商品列表信息")
    @RequestMapping(value = "queryGoodsList", method = {RequestMethod.POST})
    Result<QueryResultODTO<GoodsODTO>> queryGoodsList(@Valid @RequestBody GoodsQueryDTO goodsQueryDTO) {
        Result<QueryResultODTO<GoodsODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(goodsQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = goodsService.queryGoodsList(goodsQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }
}
