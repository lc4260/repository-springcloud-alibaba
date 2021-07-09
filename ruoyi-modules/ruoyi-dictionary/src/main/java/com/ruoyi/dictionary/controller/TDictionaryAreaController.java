package com.ruoyi.dictionary.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.dictionary.entity.TDictionaryArea;
import com.ruoyi.dictionary.service.TDictionaryAreaService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (TDictionaryArea)表控制层
 *
 * @author makejava
 * @since 2021-06-30 14:29:33
 */
@RestController
@RequestMapping("/area")
public class TDictionaryAreaController{
    /**
     * 服务对象
     */
    @Resource
    private TDictionaryAreaService tDictionaryAreaService;

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param tDictionaryArea 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<TDictionaryArea> page, TDictionaryArea tDictionaryArea) {
        return R.ok(this.tDictionaryAreaService.page(page, new QueryWrapper<>(tDictionaryArea)),"操作成功！");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return R.ok(this.tDictionaryAreaService.getById(id),"操作成功！");
    }

    /**
     * 新增数据
     *
     * @param tDictionaryArea 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody TDictionaryArea tDictionaryArea) {
        return R.ok(this.tDictionaryAreaService.save(tDictionaryArea),"操作成功！");
    }

    /**
     * 修改数据
     *
     * @param tDictionaryArea 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody TDictionaryArea tDictionaryArea) {
        return this.tDictionaryAreaService.updateByEntity(tDictionaryArea);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return R.ok(this.tDictionaryAreaService.removeByIds(idList),"操作成功！");
    }
}
