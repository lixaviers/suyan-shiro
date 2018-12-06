package com.lixavier.manage.util;

import com.lixavier.manage.constant.Constants;
import com.lixavier.manage.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {

    /**
     * 获取当前session
     *
     * @return
     */
    public static Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return subject.getSession();
        }
        return null;
    }

    /**
     * 获取用户信息数据
     *
     * @return
     */
    public static UserVO getSessionUser() {
        Session session = getSession();
        if (session != null) {
            Object sessionUserObj = session.getAttribute(Constants.USER_SESSION_KEY);
            if (sessionUserObj instanceof UserVO) {
                return (UserVO) sessionUserObj;
            }
        }
        return null;
    }

}
