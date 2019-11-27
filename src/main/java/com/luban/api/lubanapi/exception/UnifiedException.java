package com.luban.api.lubanapi.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 统一异常
 *
 * @author WeiHongBin
 */
@Slf4j
@Data
@EqualsAndHashCode
public class UnifiedException extends RuntimeException {
    private HttpStatus status;
    private String message;


    /**
     * Unified exception
     * 统一异常
     */
    public UnifiedException() {
        super("服务器忙,请稍候重试");
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "服务器忙,请稍候重试";
    }

    /**
     * 统一异常
     *
     * @param status  异常状态码
     * @param message 异常消息
     */
    public UnifiedException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    /**
     * 统一异常
     *
     * @param message 异常消息
     */
    public UnifiedException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message;
    }

    public UnifiedException(String message, Throwable e) {
        super(message, e);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message;
    }

    public UnifiedException unauthorized(String message) {
        return new UnifiedException(HttpStatus.UNAUTHORIZED, message);
    }

    public UnifiedException verifyError(Map map) {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        ObjectMapper mapper = new ObjectMapper().registerModule(module);
        String asString = "";
        try {
            asString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error("JSON 转换异常", e);
        }
        return new UnifiedException(HttpStatus.BAD_REQUEST, asString);
    }
}

