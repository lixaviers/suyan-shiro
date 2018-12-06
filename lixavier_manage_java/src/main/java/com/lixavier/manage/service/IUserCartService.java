package com.lixavier.manage.service;

import com.lixavier.manage.req.UserCartDTO;
import com.lixavier.manage.req.UserCartQueryDTO;
import com.lixavier.manage.resp.GoodsODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserCartODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 用户购物车管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-12-03>
 * @Modify Date: <2018-12-03>
 * @Version: <1.0>
 */
public interface IUserCartService {

    /**
     * 删除用户购物车
     *
     * @param idList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Integer> deleteUserCart(List<Long> idList);

    /**
     * 创建用户购物车
     *
     * @param userCartDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Long> createUserCart(UserCartDTO userCartDTO);

    /**
     * 批量创建用户购物车
     *
     * @param userCartDTOs
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Integer> batchCreate(List<UserCartDTO> userCartDTOs);

    /**
     * 更新用户购物车
     *
     * @param userCartDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Integer> updateUserCart(UserCartDTO userCartDTO);


    /**
     * 分页查询用户购物车信息
     *
     * @param userCartQueryDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<QueryResultODTO<GoodsODTO>> queryUserCart(UserCartQueryDTO userCartQueryDTO);


}