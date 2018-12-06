package com.lixavier.manage.shiro;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (this.isLoginRequest(request, response)) {
            if (this.isLoginSubmission(request, response)) {
                return this.executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(Result.newError(ResultCode.NO_LOGGED_IN)));
            out.flush();
            out.close();
            return false;
        }
    }
}
