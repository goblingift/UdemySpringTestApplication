/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goblingift.aspect;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Aspect
@Component
public class AspectManager {
    
    public static final String USERBEAN_METH_REF 
            = "execution(* com.goblingift.bean.UserBean.calculateRandom(*))";
    public static final String USERBEAN_METH_REF_TAKEOFF
            = "execution(* com.goblingift.bean.UserBean.takeOff(*))";
    
    
    @Pointcut("execution(* com.goblingift.bean.UserBean.calculateRandom(*))")
    public void namedPointcutMethod() {}
    
    @After("namedPointcutMethod()")
    public void afterCalculating() {
        System.out.println("XYZ: Method after");
    }
    
    @AfterThrowing(value = USERBEAN_METH_REF, throwing = "exception")
    public void afterThrowingException(JoinPoint jp, Exception exception) {
        System.out.println("AfterThrowing advice running now...");
        System.out.println("Method which crashed: " + jp.getSignature());
        System.out.println("Calling object: " + jp.getTarget());
        System.out.println("Params of method call: " + Arrays.asList(jp.getArgs()));
    }
    
    @Around(USERBEAN_METH_REF_TAKEOFF)
    public double aroundTakeOff(ProceedingJoinPoint joinPoint) throws Throwable {
        
        if ( (double)Arrays.asList(joinPoint.getArgs()).get(0) >= 25.00) {
            System.out.println("WARNING: User tried to take-off more than 25 bucks: " + joinPoint.getTarget());
            throw new IllegalStateException("THATS TOO MUCH!");
        }
        return (double) joinPoint.proceed();
    }
    
}
