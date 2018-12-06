package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.UserLetterBiz;
import com.lixavier.manage.convertor.UserLetterConvertor;
import com.lixavier.manage.model.UserLetter;
import com.lixavier.manage.req.UserLetterDTO;
import com.lixavier.manage.req.UserLetterQueryDTO;
import com.lixavier.manage.resp.EnumODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.resp.UserLetterODTO;
import com.lixavier.manage.resp.page.UserLetterPageODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.service.IUserLetterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 用户站内信管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("userLetterService")
public class UserLetterServiceImpl implements IUserLetterService {
    private final Logger logger = LoggerFactory.getLogger(UserLetterServiceImpl.class);

    @Autowired
    private UserLetterBiz userLetterBiz;


    @Override
    public Result<Integer> deleteUserLetter(List<Long> idList) {
        Result<Integer> result = Result.newSuccess();
        result.setDataMap(userLetterBiz.deleteUserLetter(idList));
        return result;
    }


    @Override
    public Result<Boolean> read(List<Long> idList) {
        Result<Boolean> result = Result.newSuccess();
        result.setDataMap(userLetterBiz.read(idList));
        return result;
    }


    /**
     * 分页查询用户站内信信息
     *
     * @param userLetterQueryDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<QueryResultODTO<UserLetterPageODTO>> queryUserLetter(UserLetterQueryDTO userLetterQueryDTO) {
        Result<QueryResultODTO<UserLetterPageODTO>> result = Result.newSuccess();
        result.setDataMap(userLetterBiz.queryUserLetter(userLetterQueryDTO));
        return result;
    }


    @Override
    public Result<List<EnumODTO>> getUserLetterCount(Long userId) {
        Result<List<EnumODTO>> result = Result.newSuccess();
        result.setDataMap(userLetterBiz.getUserLetterCount(userId));
        return result;
    }


    @Override
    public Result<Integer> getUserNotReadLetterCount(Long userId) {
        Result<Integer> result = Result.newSuccess();
        result.setDataMap(userLetterBiz.getUserNotReadLetterCount(userId));
        return result;
    }


}