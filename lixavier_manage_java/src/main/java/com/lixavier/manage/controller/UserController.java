package com.lixavier.manage.controller;

import com.lixavier.manage.annotation.SysLog;
import com.lixavier.manage.constant.Constants;
import com.lixavier.manage.result.ValidationResult;
import com.lixavier.manage.util.ShiroUtil;
import com.lixavier.manage.util.ValidationUtils;
import com.lixavier.manage.req.UserDTO;
import com.lixavier.manage.req.UserQueryDTO;
import com.lixavier.manage.resp.UserODTO;
import com.lixavier.manage.resp.QueryResultODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.service.IUserService;
import com.lixavier.manage.vo.UserVO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RequestMapping("user")
@RestController
@Api(value = "user", description = "用户管理接口")
public class UserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

//    @SysLog("用户登录")
    @ApiOperation(value = "login", notes = "用户登录")
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public Result<String> login(@Valid @RequestBody UserDTO userDTO) {
        Result<String> result = Result.newSuccess();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userDTO.getUserName(), userDTO.getPassword());
        try {
            subject.login(token);
            UserODTO userODTO = userService.getUserByUserName(userDTO.getUserName()).getDataMap();
            UserVO user = new UserVO();
            user.setId(userODTO.getId());
            user.setUserName(userODTO.getUserName());
            user.setNickName(userODTO.getNickName());
            user.setIsAdmin(userODTO.getIsAdmin());
            ShiroUtil.getSession().setAttribute(Constants.USER_SESSION_KEY, user);
            result.setDataMap(userODTO.getNickName());
        } catch (IncorrectCredentialsException e) {
            result.setErrorCode(ResultCode.LOGIN_ERROR);
        } catch (LockedAccountException e) {
            result.setErrorCode(ResultCode.USER_LOCKED);
        } catch (AuthenticationException e) {
            result.setErrorCode(ResultCode.USER_NOT_EXIST);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "logout", notes = "用户退出")
    @RequestMapping(value = "logout", method = {RequestMethod.POST})
    public Result<String> logout() {
        Result<String> result = Result.newSuccess();
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("用户修改密码")
    @ApiOperation(value = "password", notes = "用户修改密码")
    @RequestMapping(value = "password", method = {RequestMethod.POST})
    public Result<Boolean> password(@Valid @RequestBody UserDTO userDTO) {
        Result<Boolean> result = Result.newSuccess();
        try {
            userDTO.setId(getUser().getId());
            result = userService.password(userDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("创建用户")
    @ApiOperation(value = "createUser", notes = "创建用户")
    @RequestMapping(value = "createUser", method = {RequestMethod.POST})
    public Result<Long> register(@Valid @RequestBody UserDTO userDTO) {
        Result<Long> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = userService.createUser(userDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("删除用户")
    @ApiOperation(value = "deleteUser", notes = "删除用户")
    @RequestMapping(value = "deleteUser", method = {RequestMethod.POST})
    public Result<Integer> deleteUser(@Valid @RequestBody List<Long> idList) {
        Result<Integer> result = Result.newSuccess();
        try {
            result = userService.deleteUser(idList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("更新用户")
    @ApiOperation(value = "updateUser", notes = "更新用户")
    @RequestMapping(value = "updateUser", method = {RequestMethod.POST})
    public Result<Integer> updateUser(@Valid @RequestBody UserDTO userDTO) {
        Result<Integer> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = userService.updateUser(userDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "getUser/{id}", notes = "根据用户ID获取用户信息")
    @RequestMapping(value = "getUser/{id}", method = {RequestMethod.GET})
    public Result<UserODTO> getUser(@PathVariable Long id) {
        Result<UserODTO> result = Result.newSuccess();
        try {
            result = userService.getUser(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "getUserInfo", notes = "根据用户ID获取用户信息")
    @RequestMapping(value = "getUserInfo", method = {RequestMethod.GET})
    public Result<UserODTO> getUserInfo() {
        Result<UserODTO> result = Result.newSuccess();
        try {
            result = userService.getUserInfo(getUser().getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "queryUser", notes = "分页获取用户列表信息")
    @RequestMapping(value = "queryUser", method = {RequestMethod.POST})
    Result<QueryResultODTO<UserODTO>> queryUser(@Valid @RequestBody UserQueryDTO userQueryDTO) {
        Result<QueryResultODTO<UserODTO>> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userQueryDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = userService.queryUser(userQueryDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }

    @SysLog("用户账户充值")
    @ApiOperation(value = "addBalance", notes = "用户账户充值")
    @RequestMapping(value = "addBalance", method = {RequestMethod.POST})
    public Result<Integer> addBalance(@Valid @RequestBody UserDTO userDTO) {
        Result<Integer> result = Result.newSuccess();
        try {
            ValidationResult validateEntity = ValidationUtils.validateEntity(userDTO);
            if (validateEntity.isHasErrors()) {
                result.setCode(ResultCode.COMMON_PARAM_INVALID.getCode());
                result.setMessage(validateEntity.getErrorMsg().toString());
                return result;
            }
            result = userService.addBalance(userDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setErrorCode(ResultCode.SYS_ERROR);
        }
        return result;
    }
}
