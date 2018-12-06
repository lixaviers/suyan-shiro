package com.lixavier.manage.aspect;

import com.google.gson.Gson;
import com.lixavier.manage.annotation.SysLog;
import com.lixavier.manage.req.SysLogDTO;
import com.lixavier.manage.service.ISysLogService;
import com.lixavier.manage.util.HttpContextUtils;
import com.lixavier.manage.util.IPUtils;
import com.lixavier.manage.util.ShiroUtil;
import com.lixavier.manage.vo.UserVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("@annotation(com.lixavier.manage.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //用户
        UserVO user = ShiroUtil.getSessionUser();
        if (user != null) {
            SysLogDTO sysLog = new SysLogDTO();
            SysLog syslog = method.getAnnotation(SysLog.class);
            if (syslog != null) {
                //注解上的描述
                sysLog.setOperation(syslog.value());
            }

            //请求的方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = signature.getName();
            sysLog.setMethod(className + "." + methodName + "()");

            //请求的参数
            Object[] args = joinPoint.getArgs();
            try {
                String params = new Gson().toJson(args[0]);
                sysLog.setParams(params);
            } catch (Exception e) {

            }

            //获取request
            HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
            //设置IP地址
            sysLog.setIp(IPUtils.getIpAddr(request));

            sysLog.setUserName(user.getUserName());
            sysLog.setUserId(user.getId());
            sysLog.setNickName(user.getNickName());

            sysLog.setTime(time);
            //保存系统日志
            sysLogService.createSysLog(sysLog);
        }

    }
}
