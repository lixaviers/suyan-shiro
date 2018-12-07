package com.lixavier.manage.biz;

import com.lixavier.manage.constant.AddressLevelEnum;
import com.lixavier.manage.dao.UserAddressMapper;
import com.lixavier.manage.exception.CommonBizException;
import com.lixavier.manage.model.Address;
import com.lixavier.manage.model.UserAddress;
import com.lixavier.manage.model.UserAddressExample;
import com.lixavier.manage.req.UserAddressQueryDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.ResultCode;
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
 * @Comments: <业务层用户收货地址管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-02>
 * @Modify Date: <2018-12-02>
 * @Version: <1.0>
 */
@Service("userAddressBiz")
public class UserAddressBiz {
    private final Logger logger = LoggerFactory.getLogger(UserAddressBiz.class);

    @Autowired
    UserAddressMapper userAddressMapper;

    @Autowired
    private AddressBiz addressBiz;

    /**
     * 逻辑删除用户收货地址
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer deleteUserAddress(Long id, Long userId) {
        getUserAddressUpdate(id, userId);
        int result = userAddressMapper.logicalDeleteByPrimaryKey(id);
        return result;
    }

    /**
     * 创建用户收货地址
     *
     * @param userAddress
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long createUserAddress(UserAddress userAddress) {
        Address province = addressBiz.getAddress(userAddress.getProvinceId());
        if (province == null) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "省份");
        }
        if (!AddressLevelEnum.PROVINCE.equal(province.getAddressLevel())) {
            throw new CommonBizException(ResultCode.COMMON_PARAM_INVALID, "省份");
        }
        Address city = addressBiz.getAddress(userAddress.getCityId());
        if (city == null) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "城市");
        }
        if (city.getParentId() != province.getId()) {
            throw new CommonBizException(ResultCode.COMMON_PARAM_INVALID, "城市");
        }
        Address area = addressBiz.getAddress(userAddress.getAreaId());
        if (area == null) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "区");
        }
        if (!area.getParentId().equals(city.getId())) {
            throw new CommonBizException(ResultCode.COMMON_PARAM_INVALID, "区");
        }
        userAddress.setProvinceName(province.getName());
        userAddress.setCityName(city.getName());
        userAddress.setAreaName(area.getName());
        userAddress.setCreateTime(null);
        userAddress.setUpdateTime(null);
        userAddress.setIsDeleted(null);
        setIsDefault(userAddress);
        userAddressMapper.insertSelective(userAddress);
        return userAddress.getId();
    }

    private void setIsDefault(UserAddress userAddress) {
        if (userAddress.getIsDefault()) {
            // 默认
            UserAddress temp = new UserAddress();
            temp.setIsDefault(false);
            UserAddressExample example = new UserAddressExample();
            example.createCriteria().andIsDeletedEqualTo(false).andUserIdEqualTo(userAddress.getUserId());
            userAddressMapper.updateByExampleSelective(temp, example);
        }
    }

    private UserAddress getUserAddressUpdate(Long id, Long userId) {
        UserAddress userAddressLast = userAddressMapper.selectByPrimaryKeyForUpdate(id);
        if (userAddressLast == null || userAddressLast.getIsDeleted()) {
            throw new CommonBizException(ResultCode.COMMON_PARAM_INVALID, "用户收货地址");
        }
        if (!userAddressLast.getUserId().equals(userId)) {
            throw new CommonBizException(ResultCode.NO_PERMISSION);
        }
        return userAddressLast;
    }

    /**
     * 更新用户收货地址
     *
     * @param userAddress
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Integer updateUserAddress(UserAddress userAddress) {
        getUserAddressUpdate(userAddress.getId(), userAddress.getUserId());
        userAddress.setCreateTime(null);
        userAddress.setUpdateTime(null);
        userAddress.setIsDeleted(null);
        setIsDefault(userAddress);
        return userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

    /**
     * 根据ID获取用户收货地址信息
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public UserAddress getUserAddress(Long id) {
        UserAddress userAddress = userAddressMapper.selectByPrimaryKey(id);
        if (userAddress == null || userAddress.getIsDeleted()) {
            throw new CommonBizException(ResultCode.DATA_NOT_EXIST, "收货地址");
        }
        return userAddress;
    }

    /**
     * 分页查询用户收货地址信息
     *
     * @param userAddressQuery
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    @Transactional(readOnly = true)
    public QueryResultODTO<UserAddress> queryUserAddress(UserAddressQueryDTO userAddressQuery) {
        QueryResultODTO<UserAddress> queryResult = new QueryResultODTO<UserAddress>();
        // 使用分页插件PageHelper实现分页功能
        PageHelper.startPage(userAddressQuery.getPageNo(), userAddressQuery.getPageSize());
        List<UserAddress> userAddressList = userAddressMapper.queryUserAddress(userAddressQuery);
        PageInfo<UserAddress> pageInfo = new PageInfo<UserAddress>(userAddressList);
        queryResult.setPageNo(pageInfo.getPageNum());
        queryResult.setPageSize(pageInfo.getPageSize());
        queryResult.setTotalPages(pageInfo.getPages());
        queryResult.setTotalRecords(pageInfo.getTotal());
        queryResult.setRecords(userAddressList);
        return queryResult;
    }

}