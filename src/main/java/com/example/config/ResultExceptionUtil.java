package com.example.config;

import com.example.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static com.example.model.enmu.ResultEnum.NULL_POINTER_EXCEPTION;
import static com.example.model.enmu.ResultEnum.PARAM_ERROR;


/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/31 14:00
 */
@Slf4j
@RestControllerAdvice
public class ResultExceptionUtil {

    /**
     * 处理传参校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return Result.error(PARAM_ERROR.getCode(),
                Objects.requireNonNull(bindingResult.getFieldError()).getField(),
                bindingResult.getFieldError().getDefaultMessage());
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerExceptionException(NullPointerException e) {
        return Result.error(NULL_POINTER_EXCEPTION.getCode(),
                e.getMessage(),
                NULL_POINTER_EXCEPTION.getMsg());
    }

}
