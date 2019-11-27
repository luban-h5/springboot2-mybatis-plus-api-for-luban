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
@ApiModel(value = "WorkQueryDTO")
public class WorkUpdateDTO {

    private String title;

    private String description;

    private String coverImageUrl;

    private List<Object> pages;

    private LocalDateTime updateTime = LocalDateTime.now();

    private boolean publish;

    private boolean template;
}
