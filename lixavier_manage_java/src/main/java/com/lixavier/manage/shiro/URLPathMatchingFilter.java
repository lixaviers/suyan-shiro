package com.lixavier.manage.shiro;

import com.alibaba.fastjson.JSON;
import com.lixavier.manage.biz.MenuBiz;
import com.lixavier.manage.constant.MenuTypeEnum;
import com.lixavier.manage.model.Menu;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import com.lixavier.manage.util.ShiroUtil;
import com.lixavier.manage.util.SpringContextUtil;
import com.lixavier.manage.vo.UserVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * 权限 拦截策略
 */
public class URLPathMatchingFilter extends PathMatchingFilter {

    @Autowired
    private MenuBiz menuBiz;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {


        //请求的url
        String requestURL = getPathWithinApplication(request);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            // 如果没有登录, 直接返回true 进入登录流程
            return true;
        }
        UserVO user = ShiroUtil.getSessionUser();
        if (user.getIsAdmin()) {
            return true;
        }

        if (menuBiz == null) {
            menuBiz = SpringContextUtil.getContext().getBean(MenuBiz.class);
        }

        List<Menu> menuList = menuBiz.getUserMenuList(user.getId(), MenuTypeEnum.API.getValue());
        if (CollectionUtils.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                if (StringUtils.isNotEmpty(menu.getUrl()) && pathMatcher.match(menu.getUrl(), requestURL)) {
                    return true;
                }
            }
        }
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSONString(Result.newError(ResultCode.NO_PERMISSION)));
        out.flush();
        out.close();
        return false;
    }
}