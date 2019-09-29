package com.neusoft.base.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.neusoft.base.annotation.DataSource;
import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.db.DynamicDataSource;

/**
 * 多数据源Aspect
 *
 * @author：yu8home
 * @date：2018年9月11日 下午1:09:59
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.neusoft.base.annotation.DataSource)")
    public void dataSources() {
    }

    @Around("dataSources()")
    public Object dataSourcesAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        DataSource ds = method.getAnnotation(DataSource.class);
        if (ds == null) {
            DynamicDataSource.setDsType(GlobalConst.DB_MASTER);
        } else {
            DynamicDataSource.setDsType(ds.value());
        }

        try {
            return joinPoint.proceed();
        } finally {
            DynamicDataSource.clearDbType();
        }
    }

}