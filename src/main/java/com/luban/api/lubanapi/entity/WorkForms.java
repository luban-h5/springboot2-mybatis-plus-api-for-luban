package com.luban.api.lubanapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author WeiHongBin
 * @since 2019-11-26
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel(value = "WorkForms对象")
public class WorkForms implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String form;

    private Long workId;


}
