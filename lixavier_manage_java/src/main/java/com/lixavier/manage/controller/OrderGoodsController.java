package com.lixavier.manage.controller;

import com.lixavier.manage.req.OrderGoodsQueryDTO;
import com.lixavier.manage.req.OrderGoodsRefudDTO;
import com.lixavier.manage.resp.OrderGoodsODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.service.IOrderGoodsService;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.vo.UserVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("orderGoods")
@RestController
@Api(value = "orderGoods", description = "订单商品管理接口")
public class OrderGoodsController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(OrderGoodsController.class);

    @Autowired
    private IOrderGoodsService orderGoodsService;


    @ApiOperation(value = "queryOrderGoods", notes = "分页获取订单商品列表信息")
    @RequestMapping(value = "queryOrderGoods", method = {RequestMethod.POST})
    Result<QueryResultODTO<OrderGoodsODTO>> queryOrderGoods(@Valid @RequestBody OrderGoodsQueryDTO orderGoodsQueryDTO) {
        Result<QueryResultODTO<OrderGoodsODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(orderGoodsQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            orderGoodsQueryDTO.setUserId(getUser().getId());
            orderGoodsQueryDTO.setIsDeleted(false);
            result = orderGoodsService.queryOrderGoods(orderGoodsQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }


    @ApiOperation(value = "confirmReceipt/{id}", notes = "确认收货")
    @RequestMapping(value = "confirmReceipt/{id}", method = {RequestMethod.POST})
    public Result<Boolean> confirmReceipt(@PathVariable Long id) {
        Result<Boolean> result = Result.newSuccess();
        try {
            result = orderGoodsService.confirmReceipt(id, getUser().getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }


    @ApiOperation(value = "applyRefund/{id}", notes = "申请退款")
    @RequestMapping(value = "applyRefund/{id}", method = {RequestMethod.POST})
    public Result<Boolean> applyRefund(@PathVariable Long id) {
        Result<Boolean> result = Result.newSuccess();
        try {
            result = orderGoodsService.applyRefund(id, getUser().getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }


    @ApiOperation(value = "refund", notes = "退款")
    @RequestMapping(value = "refund", method = {RequestMethod.POST})
    public Result<Boolean> refund(@Valid @RequestBody OrderGoodsRefudDTO dto) {
        Result<Boolean> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(dto);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = orderGoodsService.refund(dto.getId(), dto.getOpt(), dto.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }


    @ApiOperation(value = "getOrderInfo/{id}", notes = "根据订单商品ID获取订单商品信息")
    @RequestMapping(value = "getOrderInfo/{id}", method = {RequestMethod.GET})
    public Result<OrderGoodsODTO> getOrderInfo(@PathVariable Long id) {
        Result<OrderGoodsODTO> result = Result.newSuccess();
        try {
            result = orderGoodsService.getOrderInfo(id, getUser().getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "deleteOrderGoods/{id}", notes = "删除订单商品")
    @RequestMapping(value = "deleteOrderGoods/{id}", method = {RequestMethod.POST})
    public Result<Integer> deleteOrderGoods(@PathVariable Long id) {
        Result<Integer> result = Result.newSuccess();
        try {
            UserVO user = getUser();
            result = orderGoodsService.deleteOrderGoods(id, getUser().getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }


//
//    @ApiOperation(value = "deleteOrderGoods/{id}", notes = "删除订单商品")
//    @RequestMapping(value = "deleteOrderGoods/{id}", method = { RequestMethod.POST })
//    public Result<Integer> deleteOrderGoods(@PathVariable Long id) {
//        Result<Integer> result = Result.newSuccess();
//        try {
//            UserVO user = getUser();
//            result = orderGoodsService.deleteOrderGoods(id);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            result.setErrorCode(ResultCode.SYS_ERROR);
//        }
//        return result;
//    }
//
//    @ApiOperation(value = "createOrderGoods", notes = "创建订单商品")
//    @RequestMapping(value = "createOrderGoods", method = { RequestMethod.POST })
//    public Result<Long> createOrderGoods(@Valid @RequestBody OrderGoodsVO orderGoodsVO){
//        Result<Long> result = Result.newSuccess();
//        try {
//            ValidationResult validateEntity = ValidationUtils.validateEntity(orderGoodsVO);
//            if (validateEntity.isHasErrors() ) {
//                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
//                result.setMessage(validateEntity.getErrorMsg().toString());
//                return result;
//            }
//            OrderGoodsDTO orderGoodsDTO = OrderGoodsConvertor.toOrderGoodsDTO(orderGoodsVO);
//            UserVO user = getUser();
//            orderGoodsDTO.setCreateUser(user.getOpenId());
//            orderGoodsDTO.setCreateUserName(user.getNickName());
//            orderGoodsDTO.setUpdateUser(user.getOpenId());
//            orderGoodsDTO.setUpdateUserName(user.getNickName());
//            result = orderGoodsService.createOrderGoods(orderGoodsDTO);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            result.setErrorCode(ResultCode.SYS_ERROR);
//        }
//        return result;
//    }
//
//    @ApiOperation(value = "updateOrderGoods", notes = "更新订单商品")
//    @RequestMapping(value = "updateOrderGoods", method = { RequestMethod.POST })
//    public Result<Integer> updateOrderGoods(@Valid @RequestBody OrderGoodsVO orderGoodsVO){
//        Result<Integer> result = Result.newSuccess();
//        try {
//            ValidationResult validateEntity = ValidationUtils.validateEntity(orderGoodsVO);
//            if (validateEntity.isHasErrors() ) {
//                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
//                result.setMessage(validateEntity.getErrorMsg().toString());
//                return result;
//            }
//            OrderGoodsDTO orderGoodsDTO = OrderGoodsConvertor.toOrderGoodsDTO(orderGoodsVO);
//            UserVO user = getUser();
//            orderGoodsDTO.setCreateUser(null);
//            orderGoodsDTO.setCreateUserName(user.getNickName());
//            orderGoodsDTO.setUpdateUser(null);
//            orderGoodsDTO.setUpdateUserName(user.getNickName());
//            result = orderGoodsService.updateOrderGoods(orderGoodsDTO);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            result.setErrorCode(ResultCode.SYS_ERROR);
//        }
//        return result;
//    }
//
//    @ApiOperation(value = "getOrderGoods/{id}", notes = "根据订单商品ID获取订单商品信息")
//    @RequestMapping(value = "getOrderGoods/{id}", method = { RequestMethod.GET })
//    public Result<OrderGoodsODTO> getOrderGoods(@PathVariable Long id ){
//        Result<OrderGoodsODTO> result = Result.newSuccess();
//        try {
//            result = orderGoodsService.getOrderGoods(id);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            result.setErrorCode(ResultCode.SYS_ERROR);
//        }
//        return result;
//    }
//


}
