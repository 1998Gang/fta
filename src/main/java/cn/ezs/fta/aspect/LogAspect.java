package cn.ezs.fta.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <h3>fat</h3>
 * <p>日志切面</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-04 18:20
 **/
@Aspect
@Component
public class LogAspect {
    /**
     * 配置切入点
     */
    @Pointcut("execution(public * cn.ezs.fta.service.*.testAop())")
    public void aop1(){};

    /**
     * 配置切入点
     */
    @Pointcut("execution(public * cn.ezs.fta.service.*.testTest())")
    public void aop2(){};

    @Before("aop1()")
    public void dobefore(){
        System.out.println("切入点1");
    }

    @Before("aop2()")
    public void dobefore2(){
        System.out.println("切入点2");
    }

    @After("aop1()")
    public void doAfter(){
        System.out.println("切入之后的方法");
    }
}
