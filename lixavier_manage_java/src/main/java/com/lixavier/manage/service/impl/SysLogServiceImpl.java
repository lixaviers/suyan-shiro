package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.SysLogBiz;
import com.lixavier.manage.convertor.SysLogConvertor;
import com.lixavier.manage.model.SysLog;
import com.lixavier.manage.req.SysLogDTO;
import com.lixavier.manage.req.SysLogQueryDTO;
import com.lixavier.manage.resp.SysLogODTO;
import com.lixavier.manage.service.ISysLogService;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 系统日志管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("sysLogService")
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private SysLogBiz sysLogBiz;


    /**
     * 创建系统日志
     *
     * @param sysLogDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Long> createSysLog(SysLogDTO sysLogDTO) {
        Result<Long> result = Result.newSuccess();
        SysLog sysLog = SysLogConvertor.toSysLog(sysLogDTO);
        result.setDataMap(sysLogBiz.createSysLog(sysLog));
        return result;
    }


    /**
     * 分页查询系统日志信息
     *
     * @param sysLogQueryDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<QueryResultODTO<SysLogODTO>> querySysLog(SysLogQueryDTO sysLogQueryDTO) {
        Result<QueryResultODTO<SysLogODTO>> result = Result.newSuccess();

        QueryResultODTO<SysLog> resultInfo = sysLogBiz.querySysLog(sysLogQueryDTO);
        result.setDataMap(SysLogConvertor.toQueryResult(resultInfo));

        return result;
    }
}