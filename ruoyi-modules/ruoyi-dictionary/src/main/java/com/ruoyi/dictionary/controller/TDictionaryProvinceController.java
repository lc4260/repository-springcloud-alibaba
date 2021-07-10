package com.ruoyi.dictionary.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.dictionary.entity.TDictionaryProvince;
import com.ruoyi.dictionary.service.TDictionaryProvinceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (TDictionaryProvince)表控制层
 *
 * @author liuchun
 * @since 2021-06-30 10:09:06
 */
@RestController
@RequestMapping("/province")
public class TDictionaryProvinceController{
    /**
     * 服务对象
     */
    @Resource
    private TDictionaryProvinceService tDictionaryProvinceService;

    /**
     * 分页查询所有数据
     *
     * @param page                分页对象
     * @param tDictionaryProvince 查询实体
     * @return 所有数据
     */
    @GetMapping()
    public R selectAll(Page<TDictionaryProvince> page, TDictionaryProvince tDictionaryProvince) {
        return R.ok(this.tDictionaryProvinceService.page(page, new QueryWrapper<>(tDictionaryProvince)),"操作成功！");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return R.ok(this.tDictionaryProvinceService.getById(id),"操作成功！");
    }

    /**
     * 新增数据
     *
     * @param tDictionaryProvince 实体对象
     * @return 新增结果
     */
    @PostMapping()
    public R insert(@RequestBody TDictionaryProvince tDictionaryProvince) {
        return R.ok(this.tDictionaryProvinceService.save(tDictionaryProvince),"操作成功！");
    }

    /**
     * 修改数据
     *
     * @param tDictionaryProvince 实体对象
     * @return 修改结果
     */
    @PutMapping()
    public R update(@RequestBody TDictionaryProvince tDictionaryProvince) {
        //乐观锁有问题暂时自己写
        return this.tDictionaryProvinceService.updateByEntity(tDictionaryProvince);
    }

    @PutMapping("/order")
    public R order(@RequestBody TDictionaryProvince tDictionaryProvince) {
        //使用悲观锁保证排序准确
        return this.tDictionaryProvinceService.order(tDictionaryProvince);
    }
    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping()
    public R delete(@RequestParam("idList") List<String> idList) {
        return R.ok(this.tDictionaryProvinceService.removeByIds(idList),"操作成功！");
    }


    /**
     * 获取行政区域树
     */
    @GetMapping("/treeSelect")
    public R treeSelect() {
        return this.tDictionaryProvinceService.treeSelect();
    }
}
