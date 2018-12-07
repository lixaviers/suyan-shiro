package com.lixavier.manage.biz;

import com.lixavier.manage.dao.UserAccountDetailMapper;
import com.lixavier.manage.model.UserAccountDetail;
import com.lixavier.manage.req.UserAccountDetailQueryDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.resp.QueryResultODTO;
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
 * @Comments: <业务层用户账户明细管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
@Service("userAccountDetailBiz")
public class UserAccountDetailBiz {
    private final Logger logger = LoggerFactory.getLogger(UserAccountDetailBiz.class);

    @Autowired
    UserAccountDetailMapper userAccountDetailMapper;

    /**
     * 创建用户账户明细
     *
     * @param userAccountDetail
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createUserAccountDetail(UserAccountDetail userAccountDetail) {
        userAccountDetail.setCreateTime(null);
        userAccountDetail.setUpdateTime(null);
        userAccountDetailMapper.insertSelective(userAccountDetail);
        return userAccountDetail.getId();
    }

    /**
     * 分页查询用户账户明细信息
     *
     * @param userAccountDetailQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<UserAccountDetail> queryUserAccountDetail(UserAccountDetailQueryDTO userAccountDetailQuery) {
        QueryResultODTO<UserAccountDetail> queryResult = new QueryResultODTO<UserAccountDetail>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(userAccountDetailQuery.getPageNo(), userAccountDetailQuery.getPageSize());
        List<UserAccountDetail> userAccountDetailList = userAccountDetailMapper.queryUserAccountDetail(userAccountDetailQuery);
        PageInfo<UserAccountDetail> pageInfo = new PageInfo<UserAccountDetail>(userAccountDetailList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(userAccountDetailList);
        return queryResult;
    }

}