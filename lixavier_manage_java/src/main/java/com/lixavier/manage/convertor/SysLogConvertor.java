package com.lixavier.manage.convertor;

import com.lixavier.manage.model.SysLog;
import com.lixavier.manage.req.SysLogDTO;
import com.lixavier.manage.resp.SysLogODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo层系统日志转换类 Dubbo层的Info对象与dal层的model对象相互转换>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public abstract class SysLogConvertor {

    private static final BeanCopier beanCopierForSysLogODTO = BeanCopier.create(SysLog.class, SysLogODTO.class, false);
    private static final BeanCopier beanCopierForSysLog = BeanCopier.create(SysLogDTO.class, SysLog.class, false);

    public static SysLogODTO toSysLogODTO(SysLog sysLog) {
        if (sysLog == null) {
            return null;
        }
        SysLogODTO sysLogODTO = new SysLogODTO();
        beanCopierForSysLogODTO.copy(sysLog, sysLogODTO, null);
        return sysLogODTO;
    }

    public static SysLog toSysLog(SysLogDTO sysLogDTO) {
        SysLog sysLog = new SysLog();
        beanCopierForSysLog.copy(sysLogDTO, sysLog, null);
        return sysLog;
    }

    public static List<SysLogODTO> toSysLogODTOList(List<SysLog> sysLogList) {
        if (sysLogList == null || sysLogList.isEmpty()) {
            return null;
        }
        List<SysLogODTO> sysLogInfoList = new ArrayList<SysLogODTO>(sysLogList.size());
        for (SysLog sysLog : sysLogList) {
            sysLogInfoList.add(toSysLogODTO(sysLog));
        }
        return sysLogInfoList;
    }

    public static List<SysLog> toSysLogList(List<SysLogDTO> sysLogDTOList) {
        if (sysLogDTOList == null || sysLogDTOList.isEmpty()) {
            return null;
        }
        List<SysLog> sysLogList = new ArrayList<SysLog>(sysLogDTOList.size());
        for (SysLogDTO sysLogDTO : sysLogDTOList) {
            sysLogList.add(toSysLog(sysLogDTO));
        }
        return sysLogList;
    }

    public static QueryResultODTO<SysLogODTO> toQueryResult(QueryResultODTO<SysLog> queryResult) {
        QueryResultODTO<SysLogODTO> queryResultInfo = new QueryResultODTO<SysLogODTO>();
        queryResultInfo.setPageNo(queryResult.getPageNo());
        queryResultInfo.setPageSize(queryResult.getPageSize());
        queryResultInfo.setTotalPages(queryResult.getTotalPages());
        queryResultInfo.setTotalRecords(queryResult.getTotalRecords());
        queryResultInfo.setRecords(toSysLogODTOList(queryResult.getRecords()));
        return queryResultInfo;
        }
}