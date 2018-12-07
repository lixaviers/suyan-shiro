package com.lixavier.manage.biz;

import com.lixavier.manage.dao.SysLogMapper;
import com.lixavier.manage.model.SysLog;
import com.lixavier.manage.req.SysLogQueryDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层系统日志管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("sysLogBiz")
public class SysLogBiz {
    private final Logger logger = LoggerFactory.getLogger(SysLogBiz.class);

    @Autowired
    SysLogMapper sysLogMapper;

    /**
     * 创建系统日志
     *
     * @param sysLog
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createSysLog(SysLog sysLog) {
        sysLogMapper.insertSelective(sysLog);
        return sysLog.getId();
    }

    /**
     * 分页查询系统日志信息
     *
     * @param sysLogQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<SysLog> querySysLog(SysLogQueryDTO sysLogQuery) {
        QueryResultODTO<SysLog> queryResult = new QueryResultODTO<SysLog>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(sysLogQuery.getPageNo(), sysLogQuery.getPageSize());
        List<SysLog> sysLogList = sysLogMapper.querySysLog(sysLogQuery);
        PageInfo<SysLog> pageInfo = new PageInfo<SysLog>(sysLogList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(sysLogList);
        return queryResult;
    }

}