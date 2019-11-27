package com.luban.api.lubanapi.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luban.api.lubanapi.convert.WorkConvert;
import com.luban.api.lubanapi.dto.WorkCreateDTO;
import com.luban.api.lubanapi.dto.WorkQueryDTO;
import com.luban.api.lubanapi.dto.WorkUpdateDTO;
import com.luban.api.lubanapi.entity.Work;
import com.luban.api.lubanapi.service.WorkService;
import com.luban.api.lubanapi.vo.WorkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 * <p>
 * 由于业务都非常简单，并没有把业务代码下沉到 service 层
 *
 * @author WeiHongBin
 */
@RestController
@RequestMapping("/works")
@Api(value = "作品管理", tags = {"作品管理"})
@Slf4j
public class WorkController {


    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }


    @ApiOperation("根据workId查询")
    @GetMapping("/{id}")
    public WorkVO findWorkById(@PathVariable Long id) {
        return WorkConvert.toVO(workService.getById(id));
    }

    @ApiOperation("查询所有work")
    @GetMapping
    public List<WorkVO> listAllWorks(@Valid WorkQueryDTO dto) {
        WorkVO vo = new WorkVO();
        vo.setTemplate(Boolean.parseBoolean(dto.getIs_template()));
        BeanUtils.copyProperties(dto, vo);
        return workService.list(new QueryWrapper<>(WorkConvert.toEntity(vo))).stream().map(WorkConvert::toVO).collect(Collectors.toList());
    }


    @ApiOperation("分页查询works")
    @GetMapping("/pageable")
    public IPage<WorkVO> listWorks(@Valid WorkQueryDTO dto) {
        WorkVO vo = new WorkVO();
        vo.setTemplate(Boolean.parseBoolean(dto.getIs_template()));
        BeanUtils.copyProperties(dto, vo);
        IPage<Work> page = workService.page(new Page<>(dto.getCurrent(), dto.getSize()), new LambdaQueryWrapper<Work>(WorkConvert.toEntity(vo)).orderByDesc(Work::getCreateTime));
        List<WorkVO> voList = page.getRecords().stream().map(WorkConvert::toVO).collect(Collectors.toList());
        return new Page<WorkVO>(page.getCurrent(), page.getSize(), page.getTotal()).setPages(page.getPages()).setRecords(voList);
    }

    @ApiOperation("创建work")
    @PostMapping
    public WorkVO createWork(@RequestBody @Valid WorkCreateDTO dto) {
        WorkVO vo = new WorkVO();
        BeanUtils.copyProperties(dto, vo);
        Work work = WorkConvert.toEntity(vo);
        workService.save(work);
        return WorkConvert.toVO(work);
    }

    @ApiOperation("更新work")
    @PutMapping("/{id}")
    public WorkVO updateWork(@PathVariable Long id, @RequestBody @Valid WorkUpdateDTO dto) {
        Work work = workService.getById(id);
        if (!StringUtils.isEmpty(dto.getTitle())) {
            work.setTitle(dto.getTitle());
        }
        if (!StringUtils.isEmpty(dto.getDescription())) {
            work.setDescription(dto.getDescription());
        }
        if (!StringUtils.isEmpty(dto.getCoverImageUrl())) {
            work.setCoverImageUrl(dto.getCoverImageUrl());
        }
        if (!CollectionUtils.isEmpty(dto.getPages())) {
            work.setPages(JSON.toJSONString(dto.getPages()));
        }
        if (dto.isTemplate()) {
            work.setTemplate(true);
        }
        if (dto.isPublish()) {
            work.setPublish(true);
        }
        work.setUpdateTime(LocalDateTime.now());
        workService.saveOrUpdate(work);
        return WorkConvert.toVO(work);
    }


    @ApiOperation("删除work")
    @DeleteMapping("/{id}")
    public void deleteWorkById(@PathVariable Long id) {
        workService.removeById(id);
    }

    @ApiOperation("设为模板")
    @PostMapping("/set-as-template/{id}")
    public WorkVO markWorkAsTemplate(@PathVariable Long id) {
        Work work = workService.getById(id);
        work.setTemplate(true);
        work.setUpdateTime(LocalDateTime.now());
        workService.updateById(work);
        return WorkConvert.toVO(work);
    }

    @ApiOperation("统计作品总数")
    @GetMapping("/count")
    public int countWork() {
        return workService.count();
    }

    @ApiOperation("使用模板")
    @PostMapping("/use-template/{id}")
    public WorkVO useTemplate(@PathVariable Long id) {
        Work work = workService.getById(id);
        work.setId(null);
        work.setTemplate(false);
        work.setPublish(false);
        workService.save(work);
        return WorkConvert.toVO(work);
    }
}

