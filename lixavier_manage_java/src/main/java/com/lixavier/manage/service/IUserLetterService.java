package com.lixavier.manage.service;

import com.lixavier.manage.req.UserLetterDTO;
import com.lixavier.manage.req.UserLetterQueryDTO;
import com.lixavier.manage.resp.EnumODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserLetterODTO;
import com.lixavier.manage.resp.page.UserLetterPageODTO;
import com.lixavier.manage.result.Result;

import java.util.List;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <对外API 用户站内信管理接口>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
public interface IUserLetterService {

    /**
     * 删除用户站内信
     *
     * @param idList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Integer> deleteUserLetter(List<Long> idList);

    /**
     * 阅读站内信
     *
     * @param idList
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<Boolean> read(List<Long> idList);


    /**
     * 分页查询用户站内信信息
     *
     * @param userLetterQueryDTO
     * @return
     * @author lixavier
     * @version 1.0.0
     */
    Result<QueryResultODTO<UserLetterPageODTO>> queryUserLetter(UserLetterQueryDTO userLetterQueryDTO);


    Result<List<EnumODTO>> getUserLetterCount(Long userId);

    Result<Integer> getUserNotReadLetterCount(Long userId);

}