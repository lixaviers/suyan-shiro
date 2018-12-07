package com.lixavier.manage.controller;

import com.lixavier.manage.annotation.SysLog;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.req.GoodsCommentDTO;
import com.lixavier.manage.req.GoodsCommentQueryDTO;
import com.lixavier.manage.resp.GoodsCommentODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.service.IGoodsCommentService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("goodsComment")
@RestController
@Api(value = "goodsComment", description = "商品评论管理接口")
public class GoodsCommentController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(GoodsCommentController.class);

    @Autowired
    private IGoodsCommentService goodsCommentService;

    @SysLog("创建商品评论")
    @ApiOperation(value = "createGoodsComment", notes = "创建商品评论")
    @RequestMapping(value = "createGoodsComment", method = {RequestMethod.POST})
    public Result<Long> createGoodsComment(@Valid @RequestBody GoodsCommentDTO goodsCommentDTO) {
        Result<Long> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(goodsCommentDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            goodsCommentDTO.setUserId(getUser().getId());
            result = goodsCommentService.createGoodsComment(goodsCommentDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("回复商品评论")
    @ApiOperation(value = "replyGoodsComment", notes = "回复商品评论")
    @RequestMapping(value = "replyGoodsComment", method = {RequestMethod.POST})
    public Result<Integer> replyGoodsComment(@Valid @RequestBody GoodsCommentDTO goodsCommentDTO) {
        Result<Integer> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(goodsCommentDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = goodsCommentService.replyGoodsComment(goodsCommentDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "queryGoodsComment", notes = "分页获取商品评论列表信息")
    @RequestMapping(value = "queryGoodsComment", method = {RequestMethod.POST})
    Result<QueryResultODTO<GoodsCommentODTO>> queryGoodsComment(@Valid @RequestBody GoodsCommentQueryDTO goodsCommentQueryDTO) {
        Result<QueryResultODTO<GoodsCommentODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(goodsCommentQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = goodsCommentService.queryGoodsComment(goodsCommentQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }
}
