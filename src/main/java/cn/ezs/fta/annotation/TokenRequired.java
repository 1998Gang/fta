package cn.ezs.fta.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解表示，必须要登录才能访问的接口
 * @author 1998Gang
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenRequired {
    /**
     * @return boolean
     */
    boolean required() default true;
}
