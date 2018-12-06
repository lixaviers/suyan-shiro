//package com.lixavier.manage.controller;
//
//import com.lixavier.manage.annotation.SysLog;
//import com.lixavier.manage.req.UserGiftCardDTO;
//import com.lixavier.manage.req.page.UserGiftCardQueryPageDTO;
//import com.lixavier.manage.resp.GiftCardODTO;
//import com.lixavier.manage.resp.QueryResultODTO;
//import com.lixavier.manage.result.Result;
//import com.lixavier.manage.result.ResultCode;
//import com.lixavier.manage.service.IUserGiftCardService;
//import com.wordnik.swagger.annotations.Api;
//import com.wordnik.swagger.annotations.ApiOperation;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RequestMapping("userGiftCard")
//@RestController
//@Api(value = "userGiftCard", description = "用户礼品卡管理接口")
//public class UserGiftCardController extends BaseController {
//    private final Logger logger = LoggerFactory.getLogger(UserGiftCardController.class);
//
//    @Autowired
//    private IUserGiftCardService userGiftCardService;
//
//    @SysLog("用户激活礼品卡")
//    @ApiOperation(value = "createUserGiftCard", notes = "用户激活礼品卡")
//    @RequestMapping(value = "createUserGiftCard", method = {RequestMethod.POST})
//    public Result<Long> createUserGiftCard(@Valid @RequestBody UserGiftCardDTO userGiftCardDTO) {
//        Result<Long> result = Result.newSuccess();
//        try {
//            userGiftCardDTO.setUserId(getUser().getId());
//            result = userGiftCardService.createUserGiftCard(userGiftCardDTO);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            result.setErrorCode(ResultCode.SYS_ERROR);
//        }
//        return result;
//    }
//
//
//    @ApiOperation(value = "getUserGiftCards", notes = "根据用户获取用户礼品卡信息")
//    @RequestMapping(value = "getUserGiftCards", method = {RequestMethod.POST})
//    public Result<QueryResultODTO<GiftCardODTO>> getUserGiftCards(@Valid @RequestBody UserGiftCardQueryPageDTO dto) {
//        Result<QueryResultODTO<GiftCardODTO>> result = Result.newSuccess();
//        try {
//            dto.setUserId(getUser().getId());
//            result = userGiftCardService.getUserGiftCards(dto);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            result.setErrorCode(ResultCode.SYS_ERROR);
//        }
//        return result;
//    }
//
//}
