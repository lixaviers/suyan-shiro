package com.lixavier.manage.biz;

import com.lixavier.manage.dao.UserRoleMapper;
import com.lixavier.manage.model.UserRole;
import com.lixavier.manage.model.UserRoleExample;
import com.lixavier.manage.req.UserRoleQueryDTO;
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
 * @Comments: <业务层用户与角色对应关系管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("userRoleBiz")
public class UserRoleBiz {
    private final Logger logger = LoggerFactory.getLogger(UserRoleBiz.class);

    @Autowired
    UserRoleMapper userRoleMapper;

    /**
     * 删除用户与角色对应关系
     *
     * @param userId
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteUserRole(Long userId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userRoleMapper.deleteByExample(example);
    }

    /**
     * 批量创建
     *
     * @param userRoleList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public int batchCreateUserRole(List<UserRole> userRoleList) {
        return userRoleMapper.insertBatch(userRoleList);
    }


}