package com.neusoft.base.spring;

import java.util.Properties;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 事务AOP
 *
 * @author：yu8home
 * @date：2018年1月11日 上午10:02:03
 */
@Configuration
public class TxAdvisor {
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        Properties p = new Properties();
        p.setProperty("get*", "PROPAGATION_SUPPORTS,readOnly");
        p.setProperty("qry*", "PROPAGATION_SUPPORTS,readOnly");
        p.setProperty("*", "PROPAGATION_REQUIRED,-Exception");

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public * com.neusoft..*ServiceImpl.*(..))");

        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, p);
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }

}