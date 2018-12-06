package com.lixavier.manage.controller;

import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.req.SysLogQueryDTO;
import com.lixavier.manage.resp.SysLogODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.service.ISysLogService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("sysLog")
@RestController
@Api(value = "sysLog", description = "系统日志管理接口")
public class SysLogController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(SysLogController.class);

    @Autowired
    private ISysLogService sysLogService;

    @ApiOperation(value = "querySysLog", notes = "分页获取系统日志列表信息")
    @RequestMapping(value = "querySysLog", method = {RequestMethod.POST})
    Result<QueryResultODTO<SysLogODTO>> querySysLog(@Valid @RequestBody SysLogQueryDTO sysLogQueryDTO) {
        Result<QueryResultODTO<SysLogODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(sysLogQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = sysLogService.querySysLog(sysLogQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

}
