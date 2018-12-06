package com.lixavier.manage.aspect;

import com.google.gson.Gson;
import com.lixavier.manage.exception.CommonBizException;
import com.lixavier.manage.result.Result;
import com.lixavier.manage.result.ResultCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Order(1)
public class ServiceInterceptor {
    private final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);

    @Around("execution(* com.lixavier.manage.service..*.*(..))")
    public Object intercept(ProceedingJoinPoint pjp) throws Throwable {
        Class<?> targetClass = pjp.getTarget().getClass();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = targetClass.getMethod(signature.getName(), signature.getParameterTypes());
        Object[] args = pjp.getArgs();

        long startTimeMillis = System.currentTimeMillis();
        StringBuilder log = new StringBuilder(">>> Start Method ")
                .append(method.getName()).append(" with args : ");
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (i > 0) {
                log.append(", ");
            }
            log.append(new Gson().toJson(arg));
        }
        logger.info(log.toString());

        Object ret = null;
        try {
            ret = pjp.proceed(args);
        } catch (CommonBizException e) {
            logger.warn(e.getErrorMsg() + " Input parameters=" + new Gson().toJson(args), e);

            Result<Object> r = Result.newSuccess();
            r.setErrorCode(e.getResultCode());
            r.setMessage(e.getErrorMsg());
            ret = r;
        } catch (Exception e) {
            logger.error(e.getMessage() + " Input parameters=" + new Gson().toJson(args), e);

            Result<Object> r = Result.newSuccess();
            r.setErrorCode(ResultCode.SYS_ERROR);
            ret = r;
        }

        log = new StringBuilder(">>> Finish Method ");
        logger.info(log.toString());
        return ret;
    }
}
