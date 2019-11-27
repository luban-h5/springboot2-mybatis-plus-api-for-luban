package com.luban.api.lubanapi.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WeiHongBin
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "WorkCreateDTO")
public class WorkCreateDTO {

    private String title;

    private String description;

    private String coverImageUrl;

    private List<Object> pages;

    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime updateTime = LocalDateTime.now();

    private boolean publish = false;

    private boolean template = false;
}
