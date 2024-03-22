package com.example.config.aspect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName LogAspect
 * @Description 日志自定义切面
 * @Author xxx
 * @Date 2023/2/13 14:33
 * @Version 1.0
 **/
@Component
@Aspect
public class LogAspect {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper objectMapper = new ObjectMapper();

    {
        // 过滤对象的null属性.
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 时间格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 处理List
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(List.class, new JsonSerializer<>() {
            @Override
            public void serialize(List list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString("size=" + list.size());
            }
        });
        objectMapper.registerModule(simpleModule);
    }

    /**
     * 方法切入点  详情-切点表达式
     */
    @Pointcut("@annotation(com.example.model.annotation.MethodLog)")
    private void annotationMethod() {
    }

    /**
     * 前置通知 - 方法切入点
     */
    @Before("annotationMethod()")
    private void methodBefore(JoinPoint joinPoint) {
    }

    @Around("annotationMethod()")
    private Object methodAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取请求的类名
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        // 请求的方法
        String methodName = joinPoint.getSignature().getName();
        // 传入的参数
        Object[] array = joinPoint.getArgs();
        // 执行函数前打印日志
        logger.warn("--------------------------------");
        logger.info("调用前：{}：{},传递的参数为：{}", className, methodName, objectMapper.writeValueAsString(array));
        Long startTime = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        logger.info("调用后：{}：{}, 耗时：{}, 返回值为：{}", className, methodName, System.currentTimeMillis() - startTime, objectMapper.writeValueAsString(object));
        logger.warn("--------------------------------");
        return object;
    }

    /**
     * 后置通知 - 方法切入点
     */
    @After("annotationMethod()")
    private void methodAfter() {
    }
}
