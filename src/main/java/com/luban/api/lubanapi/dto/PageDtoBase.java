package com.luban.api.lubanapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页基类
 *
 * @author admin
 */
@Data
public class PageDtoBase {
    /**
     * 每页行数
     */
    @ApiModelProperty(value = "每页行数")
    protected Integer size = 10;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    protected Integer current = 1;


    public void setSize(Integer size) {
        if (size != null) {
            this.size = size;
        }
    }

    public void setCurrent(Integer current) {
        if (current != null) {
            this.current = current;
        }
    }
}
