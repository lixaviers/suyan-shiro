package com.lixavier.manage.controller;

import com.lixavier.manage.annotation.SysLog;
import com.lixavier.manage.constant.OrderStatusEnum;
import com.lixavier.manage.req.OrderPayDTO;
import com.lixavier.manage.resp.OrderODTO;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.req.OrderDTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.service.IOrderService;
import com.lixavier.manage.vo.UserVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RequestMapping("order")
@RestController
@Api(value = "order", description = "订单管理接口")
public class OrderController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderService orderService;

    @ApiOperation(value = "deleteOrder/{id}", notes = "删除订单")
    @RequestMapping(value = "deleteOrder/{id}", method = {RequestMethod.POST})
    public Result<Integer> deleteOrder(@PathVariable Long id) {
        Result<Integer> result = Result.newSuccess();
        try {
            UserVO user = getUser();
            result = orderService.deleteOrder(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("创建订单")
    @ApiOperation(value = "createOrder", notes = "创建订单")
    @RequestMapping(value = "createOrder", method = {RequestMethod.POST})
    public Result<String> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        Result<String> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(orderDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = orderService.createOrder(orderDTO, getUser().getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("订单支付")
    @ApiOperation(value = "payOrder", notes = "订单支付")
    @RequestMapping(value = "payOrder", method = {RequestMethod.POST})
    public Result<Boolean> createOrder(@Valid @RequestBody OrderPayDTO orderPayDTO) {
        Result<Boolean> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(orderPayDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = orderService.payOrder(orderPayDTO, getUser().getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "getOrderPayPrice/{orderNo}", notes = "根据订单ID获取支付金额")
    @RequestMapping(value = "getOrderPayPrice/{orderNo}", method = {RequestMethod.GET})
    public Result<BigDecimal> getOrder(@PathVariable String orderNo) {
        Result<BigDecimal> result = Result.newSuccess();
        try {
            Result<OrderODTO> orderResult = orderService.getOrder(orderNo);
            if (orderResult.isSuccess()) {
                OrderODTO order = orderResult.getDataMap();
                if (!order.getUserId().equals(getUser().getId())) {
                    result.setErrorCode(ResultCode.NO_PERMISSION);
                    return result;
                }
                if (!OrderStatusEnum.TO_BE_PAID.equal(order.getOrderStatus())) {
                    result.setErrorCode(ResultCode.COMMON_MESSAGE, "此订单不需要支付");
                    return result;
                }
                result.setDataMap(order.getTotalPrice().add(order.getFreightPrice()));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "getPayOrder/{orderNo}", notes = "根据订单ID获取订单信息")
    @RequestMapping(value = "getPayOrder/{orderNo}", method = {RequestMethod.GET})
    public Result<OrderODTO> getPayOrder(@PathVariable String orderNo) {
        Result<OrderODTO> result = Result.newSuccess();
        try {

            Result<OrderODTO> orderResult = orderService.getOrder(orderNo);
            if (orderResult.isSuccess()) {
                OrderODTO order = orderResult.getDataMap();
                if (!order.getUserId().equals(getUser().getId())) {
                    result.setErrorCode(ResultCode.NO_PERMISSION);
                    return result;
                }
                if (!OrderStatusEnum.PAID.equal(order.getOrderStatus())) {
                    result.setErrorCode(ResultCode.COMMON_MESSAGE, "状态非法");
                    return result;
                }
            }

            result = orderResult;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }


//
//    @ApiOperation(value = "queryOrder", notes = "分页获取订单列表信息")
//    @RequestMapping(value = "queryOrder", method = {RequestMethod.POST})
//    Result<QueryResultODTO<OrderODTO>> queryOrder(@Valid @RequestBody OrderQueryDTO orderQueryDTO) {
//        Result<QueryResultODTO<OrderODTO>> result = Result.newSuccess();
//        try {
//            ValidationResult validateEntity = ValidationUtils.validateEntity(orderQueryDTO);
//            if (validateEntity.isHasErrors()) {
//                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
//                result.setMessage(validateEntity.getErrorMsg().toString());
//                return result;
//            }
//            orderQueryDTO.setUserId(getUser().getId());
//            orderQueryDTO.setIsDeleted(false);
//            result = orderService.queryOrder(orderQueryDTO);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            result.setErrorCode(ResultCode.SYS_ERROR);
//        }
//        return result;
//    }

//    @ApiOperation(value = "updateOrder", notes = "更新订单")
//    @RequestMapping(value = "updateOrder", method = { RequestMethod.POST })
//    public Result<Integer> updateOrder(@Valid @RequestBody OrderVO orderVO){
//        Result<Integer> result = Result.newSuccess();
//        try {
//            ValidationResult validateEntity = ValidationUtils.validateEntity(orderVO);
//            if (validateEntity.isHasErrors() ) {
//                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
//                result.setMessage(validateEntity.getErrorMsg().toString());
//                return result;
//            }
//            OrderDTO orderDTO = OrderConvertor.toOrderDTO(orderVO);
//            UserVO user = getUser();
//            result = orderService.updateOrder(orderDTO);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            result.setErrorCode(ResultCode.SYS_ERROR);
//        }
//        return result;
//    }
//
//    @ApiOperation(value = "getOrder/{id}", notes = "根据订单ID获取订单信息")
//    @RequestMapping(value = "getOrder/{id}", method = { RequestMethod.GET })
//    public Result<OrderODTO> getOrder(@PathVariable Long id ){
//        Result<OrderODTO> result = Result.newSuccess();
//        try {
//            result = orderService.getOrder(id);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            result.setErrorCode(ResultCode.SYS_ERROR);
//        }
//        return result;
//    }
//
//

}
