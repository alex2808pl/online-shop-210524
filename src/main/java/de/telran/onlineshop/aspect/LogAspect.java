package de.telran.onlineshop.aspect;

import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("@annotation(LogAnnotation)")
    public Object aroundCallAt(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed(); //запуск перехваченного метода
        log.info("!!! " + pjp.getSignature().getName() + " отработал за "+(System.currentTimeMillis()-startTime));
        return result;
    }

}
