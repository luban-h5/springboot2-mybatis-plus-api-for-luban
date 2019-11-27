package com.luban.api.lubanapi.controller;

import cn.hutool.core.util.IdUtil;
import com.luban.api.lubanapi.exception.UnifiedException;
import com.luban.api.lubanapi.vo.FileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WeiHongBin
 */
@RestController
@RequestMapping("/upload")
@Api(value = "上传文件", tags = {"上传文件"})
@Slf4j
public class UploadController {

    @Value("${upload-path}")
    public String uploadPath;

    @ApiOperation("文件上传")
    @PostMapping
    public List<FileVO> upload(@Validated @NotNull MultipartFile files) {
        String originalFilename = files.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String key = IdUtil.fastSimpleUUID() + "." + suffix;
        try {
            files.transferTo(new File(uploadPath + key));
        } catch (IOException e) {
            throw new UnifiedException("文件上传失败");
        }
        return new ArrayList<FileVO>() {{
            add(new FileVO() {{
                setUrl(key);
            }});
        }};
    }
}
