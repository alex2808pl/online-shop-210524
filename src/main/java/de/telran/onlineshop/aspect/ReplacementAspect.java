package de.telran.onlineshop.aspect;

import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.enums.Role;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReplacementAspect {
    @Pointcut("execution(public * de.telran.onlineshop.controller.UsersController.getUserById(..))") // методы аннотации UserAnnotation
    public void callAtGetMappingAnnotation() { }

    // Замещение функциональности метода
    @Around("callAtGetMappingAnnotation()")
    public Object aroundCallAt(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        retVal = pjp.proceed();
        UserDto userDtoTest2 = new UserDto(
                2L,
                "Дуня Смирнова",
                "dunya@i.com",
                "+491607654321",
                "Password2",
                Role.CLIENT
        );
        retVal = ResponseEntity.status(222).body(userDtoTest2);
        return retVal;
    }

}
