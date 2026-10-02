package kz.iitu.springlab.springlab.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Pointcuts {

    @Pointcut("within(kz.iitu.springlab.springlab.service..*)")
    public void serviceLayer() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("serviceLayer() && publicMethod()")
    public void serviceOperation() {
    }
}