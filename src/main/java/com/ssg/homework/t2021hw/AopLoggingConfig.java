package com.ssg.homework.t2021hw;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;


/*
* API 로깅 처리를 위한 AOP 로깅 기능
* */
@Slf4j
@Aspect
@Component
public class AopLoggingConfig {


    @Around("within(com.ssg.homework.t2021hw.controller..*))")
    public Object aopLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String params = getRequestParams(); // request 값 가져오기

        long startTime = System.currentTimeMillis();

        log.info("====== request : {}({}) = {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName(), params);

        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();

        log.info("====== response : {}({}) = {} ({}ms)", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName(), new Gson().toJson(result), endTime - startTime);

        return result;

    }
    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)",
                        entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }

    private String getRequestParams() {

        String params = "없음";

        RequestAttributes requestAttributes = RequestContextHolder
                .getRequestAttributes(); // 3

        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();

            Map<String, String[]> paramMap = request.getParameterMap();
            if (!paramMap.isEmpty()) {
                params = " [" + paramMapToString(paramMap) + "]";
            }
        }

        return params;

    }

}
