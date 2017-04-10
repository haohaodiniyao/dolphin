package org.dolphin.commons.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface YuniLogger {
	String value() default "";
	
	boolean isOut() default false;
	
	boolean isIn() default true;
	
	String filterField() default "";
}
