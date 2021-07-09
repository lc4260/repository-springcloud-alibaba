package com.ruoyi.dictionary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.dictionary.entity.TDictionaryProvince;

/**
 * (TDictionaryProvince)表服务接口
 *
 * @author makejava
 * @since 2021-06-30 10:09:05
 */
public interface TDictionaryProvinceService extends IService<TDictionaryProvince> {

    //排序
    R order(TDictionaryProvince tDictionaryProvince);

    R updateByEntity(TDictionaryProvince tDictionaryProvince);

    R treeSelect();
}
