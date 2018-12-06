package com.lixavier.manage.service.impl;

import com.lixavier.manage.biz.UserBiz;
import com.lixavier.manage.constant.AccountDetailTypeEnum;
import com.lixavier.manage.constant.ChannelEnum;
import com.lixavier.manage.constant.TransactionTypeEnum;
import com.lixavier.manage.convertor.UserConvertor;
import com.lixavier.manage.model.User;
import com.lixavier.manage.req.UserDTO;
import com.lixavier.manage.req.UserQueryDTO;
import com.lixavier.manage.resp.UserODTO;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.service.IUserService;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lixavier.manage.util.ValidationUtil.isEmptyOrLessThan;
import static com.lixavier.manage.util.ValidationUtil.isMoreThan;
import static com.lixavier.manage.validate.UserValidate.validateForCreate;
import static com.lixavier.manage.validate.UserValidate.validateForUpdate;
import static com.lixavier.manage.validate.UserValidate.validateForUpdateBalance;

/**
 * @CopyRright (c)2008-2017: <素焉信息技术有限公司>
 * @Project: <manage>
 * @Comments: <Dubbo Service 用户管理类>
 * @jdk 1.8
 * @Author: <lixavier>
 * @email : <lixavier@163.com>
 * @Create Date: <2018-11-24>
 * @Modify Date: <2018-11-24>
 * @Version: <1.0>
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserBiz userBiz;


    @Override
    public Result<Integer> deleteUser(List<Long> idList) {
        Result<Integer> result = Result.newSuccess();
        result.setDataMap(userBiz.deleteUser(idList));
        return result;
    }

    /**
     * 创建用户
     *
     * @param userDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Long> createUser(UserDTO userDTO) {
        Result<Long> result = Result.newSuccess();
        User user = UserConvertor.toUser(userDTO);
        if (!validateForCreate(user, result)) {
            return result;
        }
        result.setDataMap(userBiz.createUser(user));
        return result;
    }

    /**
     * 更新用户
     *
     * @param userDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<Integer> updateUser(UserDTO userDTO) {
        Result<Integer> result = Result.newSuccess();
        User user = UserConvertor.toUser(userDTO);
        if (!validateForUpdate(user, result)) {
            return result;
        }
        result.setDataMap(userBiz.updateUser(user));
        return result;
    }

    @Override
    public Result<Integer> addBalance(UserDTO userDTO) {
        Result<Integer> result = Result.newSuccess();
        User user = UserConvertor.toUser(userDTO);
        if (!validateForUpdateBalance(user, result)) {
            return result;
        }
        result.setDataMap(userBiz.addBalance(user, TransactionTypeEnum.TOP_UP.getValue(),
                ChannelEnum.BACKGROUND_TOP_UP.getValue(), AccountDetailTypeEnum.CASH.getValue()));
        return result;
    }

    /**
     * 根据ID获取用户信息
     *
     * @param id
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<UserODTO> getUser(Long id) {
        Result<UserODTO> result = Result.newSuccess();
        User user = userBiz.getUser(id);
        UserODTO userODTO = UserConvertor.toUserODTO(user);
        result.setDataMap(userODTO);
        return result;
    }

    @Override
    public Result<UserODTO> getUserInfo(Long id) {
        Result<UserODTO> result = Result.newSuccess();
        result.setDataMap(UserConvertor.toUserODTO(userBiz.getUserInfo(id)));
        return result;
    }

    /**
     * 分页查询用户信息
     *
     * @param userQueryDTO
     * @return
     * @Author: <lixavier@163.com>
     * @Version: <1.0>
     */
    @Override
    public Result<QueryResultODTO<UserODTO>> queryUser(UserQueryDTO userQueryDTO) {
        Result<QueryResultODTO<UserODTO>> result = Result.newSuccess();

        QueryResultODTO<User> resultInfo = userBiz.queryUser(userQueryDTO);
        result.setDataMap(UserConvertor.toQueryResult(resultInfo));

        return result;
    }


    @Override
    public Result<UserODTO> getUserByUserName(String userName) {
        Result<UserODTO> result = Result.newSuccess();
        result.setDataMap(UserConvertor.toUserODTO(userBiz.getUserByUserName(userName)));
        return result;
    }

    @Override
    public Result<Boolean> password(UserDTO userDTO) {
        Result<Boolean> result = Result.newSuccess();
        if (isEmptyOrLessThan(result, User.PASSWORD, userDTO.getPassword(), 6)) {
            return result;
        }
        if (isEmptyOrLessThan(result, "新密码", userDTO.getNewPassword(), 6)) {
            return result;
        }
        if (isMoreThan(result, "新密码", userDTO.getNewPassword(), 16)) {
            return result;
        }
        if (userDTO.getPassword().equals(userDTO.getNewPassword())) {
            result.setErrorCode(ResultCode.NEW_PWSSWORD_ERROR);
            return result;
        }
        result.setDataMap(userBiz.password(UserConvertor.toUser(userDTO)));
        return result;
    }

}