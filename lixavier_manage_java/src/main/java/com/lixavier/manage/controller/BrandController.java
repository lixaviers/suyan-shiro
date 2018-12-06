package com.lixavier.manage.controller;

import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.resp.BrandODTO;
import com.lixavier.manage.service.IBrandService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("brand")
@RestController
@Api(value = "brand", description = "品牌管理接口")
public class BrandController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private IBrandService brandService;


    @ApiOperation(value = "getBrandList", notes = "获取品牌列表信息")
    @RequestMapping(value = "getBrandList", method = {RequestMethod.GET})
    Result<List<BrandODTO>> getBrandList() {
        Result<List<BrandODTO>> result = Result.newSuccess();
        try {
            result = brandService.getBrandList();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }
}
