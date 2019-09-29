package com.neusoft.base.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neusoft.base.annotation.SystemLog;
import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.utils.CommUtils;
import com.neusoft.base.utils.IP;
import com.neusoft.base.utils.ShiroUtils;
import com.neusoft.biz.console.sys.logsystem.model.LogSystem;
import com.neusoft.biz.console.sys.logsystem.service.LogSystemService;

import cn.hutool.core.exceptions.ExceptionUtil;

/**
 * 系统日志Aspect
 *
 * @author：yu8home
 * @date：2017年9月8日 下午10:45:15
 */
@Aspect
@Component
public class SystemLogAspect {
    @Autowired
    private LogSystemService logSystemService;

    // @Pointcut("execution(* com.neusoft.biz.console.*.*.controller.add(..)) || execution(* com.neusoft.biz.console.*.*.controller.update(..))")
    @Pointcut("@annotation(com.neusoft.base.annotation.SystemLog)")
    public void systemLog() {
    }

    @Around("systemLog()")
    public Object systemLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer curUserId = null;
        String errMsg = null;

        // “系统退出”前
        if (ShiroUtils.isLogin()) {
            curUserId = ShiroUtils.getUser().getUserId();
        }

        long beginTime = System.currentTimeMillis();
        Object rs;
        try {
            rs = joinPoint.proceed();
        } catch (Exception e) {
            errMsg = ExceptionUtil.stacktraceToString(e);
            throw e;
        }
        long runtime = System.currentTimeMillis() - beginTime;

        // “系统登录”后
        if (curUserId == null) {
            if (ShiroUtils.isLogin()) {
                curUserId = ShiroUtils.getUser().getUserId();
            }
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemLog syslog = method.getAnnotation(SystemLog.class);

        LogSystem dt = new LogSystem();
        dt.setFuncName(syslog.value());
        dt.setClassMethod(joinPoint.getSignature().toString());
        // dt.setMethodParam(JSON.toJSONString(joinPoint.getArgs()));
        dt.setIp(IP.getIpAddr(CommUtils.getHttpServletRequest()));
        dt.setRuntime(runtime);
        dt.setIsSuccess(errMsg != null ? GlobalConst.NO : GlobalConst.YES);
        dt.setErrMsg(errMsg);
        dt.setCreateUserId(curUserId);

        logSystemService.insert(dt);
        return rs;
    }

}