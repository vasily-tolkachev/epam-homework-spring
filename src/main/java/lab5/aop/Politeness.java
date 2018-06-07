package lab5.aop;

import lab4.model.Person;
import lab5.model.Drink;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Politeness {

    @Pointcut("bean(bar) && execution(lab5.model.Drink sellSquishee(lab4.model.Person))")
    void sellSquishee() {}


    @After("sellSquishee()")
    public void wishGodLuck() {
        System.out.println("Have a nice day!");
    }

    @Before("sellSquishee() && args(person)")
    public void sayHello(Person person) {
        System.out.printf("How are you doing %s?%n", person.getName());
    }

    @Around("sellSquishee()")
    public Object sayPoliteWordsAndSell(ProceedingJoinPoint joinPoint) throws  Throwable {
        System.out.println("Hi!");
        Object returnValue = joinPoint.proceed();
        System.out.println("See you!");
        return returnValue;
    }

    @AfterThrowing("sellSquishee()")
    public void grumble() {
        System.out.println("You have no money. We not happy to see you!");
    }

    @AfterReturning(pointcut = "sellSquishee()", returning = "returnValue", argNames = "returnValue")
    public void askOpinion(Drink returnValue) {
        System.out.printf("Is %s Good Enough?%n", returnValue.getName());
    }
}
