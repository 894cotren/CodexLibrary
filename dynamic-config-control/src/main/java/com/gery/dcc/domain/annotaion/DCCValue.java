package com.gery.dcc.domain.annotaion;

import java.lang.annotation.*;

/**
 * 这是一个动态配置的标记注解，顺带赋值初始值。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface DCCValue {

    String value() default "";

}
