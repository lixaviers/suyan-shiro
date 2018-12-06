package com.lixavier.manage.controller;

import com.lixavier.manage.util.ShiroUtil;
import com.lixavier.manage.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {
    private final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(false);
    }

    /**
     * 获取登录用户
     *
     * @return
     */
    protected UserVO getUser() {
        UserVO sessionUser = ShiroUtil.getSessionUser();
        return sessionUser;

    }


}
