package org.dragard.projectmanager.util;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.annotation.ResponceHandle;
import org.dragard.projectmanager.endpoint.Response;

import javax.inject.Inject;

//@Interceptor
//@ResponceHandle
@Aspect
public class ServiceInterceptor {

    @Inject
    @Setter(value = AccessLevel.PROTECTED)
    private ServiceLocator serviceLocator;

    @Pointcut("execution(org.dragard.projectmanager.endpoint.Response " +
            "org.dragard.projectmanager.service.*.*(..))")
    public void servicePointcut(){}
/*
    @Pointcut("execution(* org.dragard.spring.projectmanager.service.*.*(User)) && args(user)")
    public void servicePointcut2(User user){}*/

    @Around("servicePointcut()")
    public Object handleResponce(ProceedingJoinPoint jp) throws Exception{
        Response response = null;
        try {
            response = (Response) jp.proceed();
            /*if (!(o instanceof Response)){
                return o;
            }
            response = (Response) o;*/
            UtilClass.checkResponse(response);
        } catch (Throwable e) {
            String cause = e.getMessage();
            while (e.getCause() != null){
                cause = cause + " " + e.getCause().getMessage();
                e = e.getCause();
            }
            if (cause.contains("Invalid token") || cause.contains("Token corrupted")){
                serviceLocator.getAuthorizationService().setToken(null);
            }
            System.out.println(String.format("FAILED (%s)", cause));
        }
        return response;
    }
}
