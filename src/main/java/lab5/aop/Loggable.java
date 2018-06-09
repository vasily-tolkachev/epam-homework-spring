package lab5.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** @see {@link lab5.aop.LoggingAspect#logMethodCall(ProceedingJoinPoint)} */
@Target(METHOD)
@Retention(RUNTIME)
public @interface Loggable {}
