package kz.iitu.springlab.springlab.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionTranslationAspect {

    private static final Logger log =
            LoggerFactory.getLogger(ExceptionTranslationAspect.class);

    @AfterThrowing(
            pointcut = "kz.iitu.springlab.springlab.aspect.Pointcuts.serviceOperation()",
            throwing = "ex"
    )
    public void logException(JoinPoint jp, Throwable ex) {
        log.error("[TRANSLATION] Technical exception in {}: {}",
                jp.getSignature().toShortString(),
                ex.getMessage());
    }

    @Around("kz.iitu.springlab.springlab.aspect.Pointcuts.serviceOperation()")
    public Object translateException(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (CatalogException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CatalogException(
                    "Catalog operation failed: " + ex.getMessage(),
                    ex
            );
        }
    }
}