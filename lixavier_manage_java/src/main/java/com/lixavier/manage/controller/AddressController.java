package com.lixavier.manage.controller;

import com.lixavier.manage.resp.AddressODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.service.IAddressService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("address")
@RestController
@Api(value = "address", description = "地址管理接口")
public class AddressController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private IAddressService addressService;

    @ApiOperation(value = "getAddressList", notes = "获取地址信息")
    @RequestMapping(value = "getAddressList", method = {RequestMethod.GET})
    public Result<List<AddressODTO>> getAddress() {
        Result<List<AddressODTO>> result = Result.newSuccess();
        try {
            result = addressService.getAddressList();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

}
