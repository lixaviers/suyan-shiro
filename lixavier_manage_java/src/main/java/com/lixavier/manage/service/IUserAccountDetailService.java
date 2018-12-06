package com.lixavier.manage.service;

import com.lixavier.manage.req.UserAccountDetailDTO;
import com.lixavier.manage.req.UserAccountDetailQueryDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserAccountDetailODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 用户账户明细管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public interface IUserAccountDetailService {

    /**
     * 
     * 分页查询用户账户明细信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param userAccountDetailQueryDTO
     * @return
     */
    Result<QueryResultODTO<UserAccountDetailODTO>> queryUserAccountDetail(UserAccountDetailQueryDTO userAccountDetailQueryDTO);

}