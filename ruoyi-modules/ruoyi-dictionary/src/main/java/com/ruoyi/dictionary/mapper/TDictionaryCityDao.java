package com.ruoyi.dictionary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.dictionary.entity.TDictionaryCity;
import com.ruoyi.dictionary.entity.TDictionaryProvince;

/**
 * (TDictionaryCity)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-30 13:46:25
 */
public interface TDictionaryCityDao extends BaseMapper<TDictionaryCity> {

    int updateByEntity(TDictionaryCity entity);
}
