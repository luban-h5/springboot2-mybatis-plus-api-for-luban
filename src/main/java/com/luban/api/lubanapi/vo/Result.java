package com.luban.api.lubanapi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 统一返回
 *
 * @author WeiHongBin
 */
@ApiModel(description = "返回响应数据")
@Component
@Data
public class Result<T> {
    @ApiModelProperty(value = "返回状态码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String msg;
    @ApiModelProperty(value = "返回当前状态")
    private HttpStatus status;
    @ApiModelProperty(value = "返回对象")
    private T data;

    public Result() {
        this.msg = "成功";
        setStatus(HttpStatus.OK);
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
        setCode(status.value());
    }

    private void setCode(Integer code) {
        this.code = code;
    }

    public Result(T data) {
        setStatus(HttpStatus.OK);
        this.msg = "成功";
        this.data = data;
    }

    public Result(String msg, HttpStatus status, T data) {
        this.msg = msg;
        setStatus(status);
        this.data = data;
    }

    public Result(String msg, HttpStatus status) {
        this.msg = msg;
        setStatus(status);
    }

    public Result<T> ok(T data) {
        setStatus(HttpStatus.OK);
        this.msg = "成功";
        this.data = data;
        return this;
    }

    public Result<T> error(HttpStatus status, String msg) {
        setStatus(status);
        this.msg = msg;
        this.data = null;
        return this;
    }

    public boolean isOK() {
        return this.getStatus() == HttpStatus.OK;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
