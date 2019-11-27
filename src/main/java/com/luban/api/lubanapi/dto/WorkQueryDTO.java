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
public class WorkQueryDTO extends PageDtoBase {

    private String title;

    private String description;

    private String coverImageUrl;

    private List<Object> pages;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String is_publish;

    private String is_template;
}
