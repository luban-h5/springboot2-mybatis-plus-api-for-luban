package com.luban.api.lubanapi.exception;


import com.luban.api.lubanapi.util.JSON;
import com.luban.api.lubanapi.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异常处理
 *
 * @author WeiHongBin
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandle {


    @Autowired
    private JSON json;

    /**
     * 统一异常处理器
     *
     * @param e UnifiedException
     * @return 响应实体
     */
    @ExceptionHandler(value = UnifiedException.class)
    public ResponseEntity<Result<String>> unifiedExceptionHandle(UnifiedException e) {
        Result<String> result = new Result<>(e.getMessage(), e.getStatus());
        if (e.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR) {
            log.error(e.getMessage(), e);
        } else {
            log.warn(e.getMessage(), e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * 处理参数校验异常
     *
     * @param e BindException
     * @return 响应实体
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Object> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        // 获取全部错误对象
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String errorMsg = objectErrorHandle(allErrors);
        log.warn("MethodArgumentNotValidException:", e);
        return new Result<>().error(HttpStatus.BAD_REQUEST, errorMsg);
    }

    private String objectErrorHandle(List<ObjectError> allErrors) {
        Map<String, String> map = new HashMap<>(allErrors.size());
        // 把错误对象转成 k,v 存入Map
        allErrors.forEach(error -> {
            String key = error.getObjectName();
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                key = fieldError.getField();
            }
            map.put(key, error.getDefaultMessage());
        });
        return json.toJSON(map);
    }

    /**
     * 处理参数校验异常
     *
     * @param e BindException
     * @return 响应实体
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Object> bindExceptionHandle(BindException e) {
        // 获取全部错误对象
        List<ObjectError> allErrors = e.getAllErrors();
        String errorMsg = objectErrorHandle(allErrors);
        log.warn("BindException:", e);
        return new Result<>().error(HttpStatus.BAD_REQUEST, errorMsg);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Object> noHandlerFoundExceptionHandle(NoHandlerFoundException e) {
        log.warn("NoHandlerFoundException 异常:", e);
        return new Result<>().error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Object> exceptionHandle(Exception e) {
        log.error("Exception 异常:", e);
        return new Result<>().error(HttpStatus.INTERNAL_SERVER_ERROR, "服务繁忙，请稍后再试！");
    }
}
