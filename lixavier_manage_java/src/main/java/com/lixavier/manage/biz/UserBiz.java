package com.lixavier.manage.biz;

import com.lixavier.manage.constant.AccountDetailTypeEnum;
import com.lixavier.manage.constant.ChannelEnum;
import com.lixavier.manage.constant.TransactionTypeEnum;
import com.lixavier.manage.constant.UserStatusEnum;
import com.lixavier.manage.dao.UserMapper;
import com.lixavier.manage.exception.CommonBizException;
import com.lixavier.manage.model.User;
import com.lixavier.manage.model.UserAccountDetail;
import com.lixavier.manage.model.UserExample;
import com.lixavier.manage.model.UserRole;
import com.lixavier.manage.req.UserQueryDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.shiro.ShiroUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <业务层用户管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("userBiz")
public class UserBiz {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleBiz userRoleBiz;
    @Autowired
    private UserAccountDetailBiz userAccountDetailBiz;

    /**
     * 逻辑删除用户
     *
     * @param idList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteUser(List<Long> idList) {
        if (CollectionUtils.isNotEmpty(idList)) {
            for (Long id : idList) {
                userMapper.logicalDeleteByPrimaryKey(id);
            }
        }
        return 1;
    }

    /**
     * 创建用户
     *
     * @param user
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createUser(User user) {
        User userLast = getUserByUserName(user.getUserName());
        if (userLast != null) {
            throw new CommonBizException(ResultCode.DATA_EXIST, "用户名");
        }
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setSalt(salt);
        user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
        user.setCommonStatus(UserStatusEnum.NORMAL.getValue());
        user.setBalance(BigDecimal.ZERO);
        user.setIsDeleted(false);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        userMapper.insertSelective(user);
        createUserRole(user);
        return user.getId();
    }

    private void createUserRole(User user) {
        if (!user.getIsAdmin()) {
            if (CollectionUtils.isNotEmpty(user.getRoleIdList())) {
                List<UserRole> userRoleList = new ArrayList<>();
                for (Long roleId : user.getRoleIdList()) {
                    UserRole userRole = new UserRole();
                    userRole.setUserId(user.getId());
                    userRole.setRoleId(roleId);
                    userRoleList.add(userRole);
                }
                userRoleBiz.batchCreateUserRole(userRoleList);
            }
        }
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer updateUser(User user) {
        User userLast = userMapper.selectByPrimaryKeyForUpdate(user.getId());
        if (userLast == null) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "用户");
        }
        user.setUserName(null);
        user.setBalance(null);
        user.setIsDeleted(false);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        user.setSalt(null);
        user.setPassword(null);
        userMapper.updateByPrimaryKeySelective(user);

        userRoleBiz.deleteUserRole(user.getId());
        createUserRole(user);

        return 1;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer addBalance(User user, Byte transactionType, Byte channel, Byte type) {
        User userLast = userMapper.selectByPrimaryKeyForUpdate(user.getId());
        if (userLast == null) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "用户");
        }

        UserAccountDetail userAccountDetail = new UserAccountDetail();
        userAccountDetail.setUserId(user.getId());
        userAccountDetail.setTransactionType(transactionType);
        userAccountDetail.setAmount(user.getBalance());
        userAccountDetail.setBalance(userLast.getBalance().add(user.getBalance()));
        userAccountDetail.setChannel(channel);
        userAccountDetail.setType(type);
        userAccountDetailBiz.createUserAccountDetail(userAccountDetail);

        return userMapper.addBalance(user);
    }

    /**
     * 根据ID获取用户信息
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null || user.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "用户");
        }
        if (!user.getIsAdmin()) {
            user.setRoleIdList(userMapper.getRoleIdListByRoleId(id));
        }
        return user;
    }

    @Transactional(readOnly = true)
    public User getUserInfo(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null || user.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "用户");
        }
        user.setPassword(null);
        user.setSalt(null);
        user.setUpdateTime(null);
        user.setIsDeleted(null);
        return user;
    }

    /**
     * 分页查询用户信息
     *
     * @param userQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<User> queryUser(UserQueryDTO userQuery) {
        QueryResultODTO<User> queryResult = new QueryResultODTO<User>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(userQuery.getPageNo(), userQuery.getPageSize());
        List<User> userList = userMapper.queryUser(userQuery);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(userList);
        return queryResult;
    }


    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public User getUserByUserName(String userName) {
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(userName).andIsDeletedEqualTo(false);
        List<User> users = userMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(users)) {
            return users.get(0);
        }
        return null;
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public boolean password(User user) {
        User userLast = userMapper.selectByPrimaryKeyForUpdate(user.getId());
        if (userLast == null) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "用户");
        }
        String password = ShiroUtils.sha256(user.getPassword(), userLast.getSalt());
        if (!userLast.getPassword().equals(password)) {
            throw new CommonBizException(ResultCode.PWSSWORD_ERROR);
        }
        User temp = new User();
        temp.setId(user.getId());
        temp.setPassword(ShiroUtils.sha256(user.getNewPassword(), userLast.getSalt()));
        userMapper.updateByPrimaryKeySelective(temp);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public BigDecimal pay(Long userId, BigDecimal amount) {
        getUserInfo(userId);

        User user = userMapper.selectByPrimaryKeyForUpdate(userId);
        if (user == null || user.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "用户");
        }
        // 小于等于0 不需要扣除余额
        if (BigDecimal.ZERO.compareTo(amount) >= 0) {
            return user.getBalance();
        }
        int result = userMapper.pay(userId, amount);
        if (result < 1) {
            throw new CommonBizException(ResultCode.LACK_OF_DATA, "余额");
        }
        return user.getBalance().subtract(amount);
    }


}