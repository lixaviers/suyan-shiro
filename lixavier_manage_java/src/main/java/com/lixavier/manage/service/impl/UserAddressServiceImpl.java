package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.UserAddressBiz;
import com.lixavier.manage.convertor.UserAddressConvertor;
import com.lixavier.manage.model.UserAddress;
import com.lixavier.manage.req.UserAddressDTO;
import com.lixavier.manage.req.UserAddressQueryDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserAddressODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.IUserAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lixavier.manage.validate.UserAddressValidate.validateForCreate;
import static com.lixavier.manage.validate.UserAddressValidate.validateForUpdate;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 用户收货地址管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-02>
 * @Modify Date: <2018-12-02>
 * @Version: <1.0>
 */
@Service("userAddressService")
public class UserAddressServiceImpl implements IUserAddressService {
    private final Logger logger = LoggerFactory.getLogger(UserAddressServiceImpl.class);

    @Autowired
    private UserAddressBiz userAddressBiz;

    @Override
    public Result<Integer> deleteUserAddress(Long id) {
        Result<Integer> result = Result.newSuccess();
        result.setDataMap(userAddressBiz.deleteUserAddress(id));
        return result;
    }

    /**
     * 创建用户收货地址
     *
     * @param userAddressDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Long> createUserAddress(UserAddressDTO userAddressDTO) {
        Result<Long> result = Result.newSuccess();
        UserAddress userAddress = UserAddressConvertor.toUserAddress(userAddressDTO);
        if (!validateForCreate(userAddress, result)) {
            return result;
        }
        result.setDataMap(userAddressBiz.createUserAddress(userAddress));
        return result;
    }

    /**
     * 更新用户收货地址
     *
     * @param userAddressDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> updateUserAddress(UserAddressDTO userAddressDTO) {
        Result<Integer> result = Result.newSuccess();
        UserAddress userAddress = null;
        userAddress = UserAddressConvertor.toUserAddress(userAddressDTO);
        if (!validateForUpdate(userAddress, result)) {
            return result;
        }
        result.setDataMap(userAddressBiz.updateUserAddress(userAddress));
        return result;
    }

    /**
     * 根据ID获取用户收货地址信息
     *
     * @param id
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<UserAddressODTO> getUserAddress(Long id) {
        Result<UserAddressODTO> result = Result.newSuccess();
        UserAddress userAddress = userAddressBiz.getUserAddress(id);
        UserAddressODTO userAddressODTO = UserAddressConvertor.toUserAddressODTO(userAddress);
        result.setDataMap(userAddressODTO);
        return result;
    }

    /**
     * 分页查询用户收货地址信息
     *
     * @param userAddressQueryDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<QueryResultODTO<UserAddressODTO>> queryUserAddress(UserAddressQueryDTO userAddressQueryDTO) {
        Result<QueryResultODTO<UserAddressODTO>> result = Result.newSuccess();

        QueryResultODTO<UserAddress> resultInfo = userAddressBiz.queryUserAddress(userAddressQueryDTO);
        result.setDataMap(UserAddressConvertor.toQueryResult(resultInfo));

        return result;
    }
}