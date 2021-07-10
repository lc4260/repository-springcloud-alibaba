package com.ruoyi.dictionary.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.dictionary.entity.TDictionaryCity;
import com.ruoyi.dictionary.service.TDictionaryCityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (TDictionaryCity)表控制层
 *
 * @author liuchun
 * @since 2021-06-30 13:46:27
 */
@RestController
@RequestMapping("/city")
public class TDictionaryCityController{
    /**
     * 服务对象
     */
    @Resource
    private TDictionaryCityService tDictionaryCityService;

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param tDictionaryCity 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TDictionaryCity> page, TDictionaryCity tDictionaryCity) {
        return R.ok(this.tDictionaryCityService.page(page, new QueryWrapper<>(tDictionaryCity)),"操作成功！");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return R.ok(this.tDictionaryCityService.getById(id),"操作成功！");
    }

    /**
     * 新增数据
     *
     * @param tDictionaryCity 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TDictionaryCity tDictionaryCity) {
        return R.ok(this.tDictionaryCityService.save(tDictionaryCity),"操作成功！");
    }

    /**
     * 修改数据
     *
     * @param tDictionaryCity 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TDictionaryCity tDictionaryCity) {
        return this.tDictionaryCityService.updateByEntity(tDictionaryCity);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return R.ok(this.tDictionaryCityService.removeByIds(idList),"操作成功！");
    }
}
