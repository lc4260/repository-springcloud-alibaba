package com.ruoyi.dictionary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.dictionary.entity.TDictionaryArea;

/**
 * (TDictionaryArea)表服务接口
 *
 * @author liuchun
 * @since 2021-06-30 14:29:33
 */
public interface TDictionaryAreaService extends IService<TDictionaryArea> {

    R updateByEntity(TDictionaryArea tDictionaryArea);
}
