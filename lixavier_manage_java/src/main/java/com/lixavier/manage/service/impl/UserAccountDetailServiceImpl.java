package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.UserAccountDetailBiz;
import com.lixavier.manage.convertor.UserAccountDetailConvertor;
import com.lixavier.manage.model.UserAccountDetail;
import com.lixavier.manage.req.UserAccountDetailQueryDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserAccountDetailODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.IUserAccountDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 用户账户明细管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
@Service("userAccountDetailService")
public class UserAccountDetailServiceImpl implements IUserAccountDetailService {
    private final Logger logger = LoggerFactory.getLogger(UserAccountDetailServiceImpl.class);

    @Autowired
    private UserAccountDetailBiz userAccountDetailBiz;

    @Override
    public Result<QueryResultODTO<UserAccountDetailODTO>> queryUserAccountDetail(UserAccountDetailQueryDTO userAccountDetailQueryDTO) {
        Result<QueryResultODTO<UserAccountDetailODTO>> result = Result.newSuccess();

        QueryResultODTO<UserAccountDetail> resultInfo = userAccountDetailBiz.queryUserAccountDetail(userAccountDetailQueryDTO);
        result.setDataMap(UserAccountDetailConvertor.toQueryResult(resultInfo));

        return result;
    }
}