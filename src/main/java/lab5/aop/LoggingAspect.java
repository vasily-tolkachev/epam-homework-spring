package lab5.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("@annotation(Loggable)")
    public void serviceMethod() {}

    @Around("serviceMethod()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(String.format("Method: %s, Argumets: %s", methodName,  Arrays.toString(joinPoint.getArgs())));
        Object result = joinPoint.proceed();
        System.out.println(String.format("Method: %s, Result: %s", methodName, result));
        return result;
    }
}
