package com.lixavier.manage.service;

import com.lixavier.manage.req.SysLogDTO;
import com.lixavier.manage.req.SysLogQueryDTO;
import com.lixavier.manage.resp.SysLogODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 系统日志管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public interface ISysLogService {
    
    /**
     * 
     * 创建系统日志
     * 
     * @author lixavier
     * @version 1.0.0
     * @param sysLogDTO
     * @return
     */
    Result<Long> createSysLog(SysLogDTO sysLogDTO);

    /**
     * 
     * 分页查询系统日志信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param sysLogQueryDTO
     * @return
     */
    Result<QueryResultODTO<SysLogODTO>> querySysLog(SysLogQueryDTO sysLogQueryDTO);

}