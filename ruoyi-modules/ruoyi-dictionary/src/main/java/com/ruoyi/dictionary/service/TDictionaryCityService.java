package com.ruoyi.dictionary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.dictionary.entity.TDictionaryCity;

/**
 * (TDictionaryCity)表服务接口
 *
 * @author liuchun
 * @since 2021-06-30 13:46:26
 */
public interface TDictionaryCityService extends IService<TDictionaryCity> {

    R updateByEntity(TDictionaryCity tDictionaryCity);
}
