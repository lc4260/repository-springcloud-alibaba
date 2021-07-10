package com.ruoyi.dictionary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.dictionary.entity.TDictionaryArea;

/**
 * (TDictionaryArea)表数据库访问层
 *
 * @author liuchun
 * @since 2021-06-30 14:29:32
 */
public interface TDictionaryAreaDao extends BaseMapper<TDictionaryArea> {

    int updateByEntity(TDictionaryArea entity);
}
