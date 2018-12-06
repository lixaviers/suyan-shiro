package com.lixavier.manage.service;

import com.lixavier.manage.req.UserDTO;
import com.lixavier.manage.req.UserQueryDTO;
import com.lixavier.manage.resp.UserODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 系统管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public interface IUserService {

    /**
     * 删除用户
     *
     * @param idList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Integer> deleteUser(List<Long> idList);

    /**
     * 创建用户
     *
     * @param userDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Long> createUser(UserDTO userDTO);

    /**
     * 更新用户
     *
     * @param userDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Integer> updateUser(UserDTO userDTO);

    Result<Integer> addBalance(UserDTO userDTO);

    /**
     * 根据ID获取用户信息
     *
     * @param id
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<UserODTO> getUser(Long id);

    Result<UserODTO> getUserInfo(Long id);

    /**
     * 分页查询用户信息
     *
     * @param userQueryDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<QueryResultODTO<UserODTO>> queryUser(UserQueryDTO userQueryDTO);


    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<UserODTO> getUserByUserName(String userName);

    Result<Boolean> password(UserDTO userDTO);


}