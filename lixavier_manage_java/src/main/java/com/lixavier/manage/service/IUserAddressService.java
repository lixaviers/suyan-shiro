package com.lixavier.manage.service;

import com.lixavier.manage.req.UserAddressDTO;
import com.lixavier.manage.req.UserAddressQueryDTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserAddressODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 用户收货地址管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-02>
 * @Modify Date: <2018-12-02>
 * @Version: <1.0>
 */
public interface IUserAddressService {

    /**
     * 
     * 删除用户收货地址
     * 
     * @author lixavier
     * @version 1.0.0
     * @param id
     * @return
     */
    Result<Integer> deleteUserAddress(Long id);
    
    /**
     * 
     * 创建用户收货地址
     * 
     * @author lixavier
     * @version 1.0.0
     * @param userAddressDTO
     * @return
     */
    Result<Long> createUserAddress(UserAddressDTO userAddressDTO);

    /**
     *
     * 更新用户收货地址
     *
     * @author lixavier
     * @version 1.0.0
     * @param userAddressDTO
     * @return
     */
    Result<Integer> updateUserAddress(UserAddressDTO userAddressDTO);

    /**
     * 
     * 根据ID获取用户收货地址信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param id
     * @return
     */
    Result<UserAddressODTO> getUserAddress(Long id);

    /**
     * 
     * 分页查询用户收货地址信息
     * 
     * @author lixavier
     * @version 1.0.0
     * @param userAddressQueryDTO
     * @return
     */
    Result<QueryResultODTO<UserAddressODTO>> queryUserAddress(UserAddressQueryDTO userAddressQueryDTO);

    /**
    * The following custom service interface about dubbo
    */
}