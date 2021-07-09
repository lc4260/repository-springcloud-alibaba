package com.ruoyi.dictionary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.dictionary.entity.TDictionaryProvince;

/**
 * (TDictionaryProvince)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-30 10:09:03
 */
public interface TDictionaryProvinceDao extends BaseMapper<TDictionaryProvince> {

    int updateByEntity(TDictionaryProvince entity);
    //升序
    void upOrderById(TDictionaryProvince entity);
    //降序
    void downOrderById(TDictionaryProvince entity);
}
