package com.lixavier.manage.shiro;

import com.lixavier.manage.biz.UserBiz;
import com.lixavier.manage.constant.UserStatusEnum;
import com.lixavier.manage.model.User;
import com.lixavier.manage.resp.UserODTO;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    // 认证登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        Result<UserODTO> result = userService.getUserByUserName(username);


        //账号不存在
        if (result == null || !result.isSuccess()) {
            throw new UnknownAccountException(ResultCode.USER_NOT_EXIST.getMessage());
        }
        UserODTO user = result.getDataMap();
        //账号锁定
        if (UserStatusEnum.DISABLE.equal(user.getCommonStatus())) {
            throw new LockedAccountException(ResultCode.USER_LOCKED.getMessage());
        }


        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
