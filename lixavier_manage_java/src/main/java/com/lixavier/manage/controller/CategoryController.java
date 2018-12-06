package com.lixavier.manage.controller;

import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.resp.CategoryODTO;
import com.lixavier.manage.service.ICategoryService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("category")
@RestController
@Api(value = "category", description = "类目管理接口")
public class CategoryController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private ICategoryService categoryService;


    @ApiOperation(value = "getCategoryList", notes = "获取类目列表信息")
    @RequestMapping(value = "getCategoryList", method = {RequestMethod.GET})
    Result<List<CategoryODTO>> getCategoryList() {
        Result<List<CategoryODTO>> result = Result.newSuccess();
        try {
            result = categoryService.getCategoryList();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }
}
