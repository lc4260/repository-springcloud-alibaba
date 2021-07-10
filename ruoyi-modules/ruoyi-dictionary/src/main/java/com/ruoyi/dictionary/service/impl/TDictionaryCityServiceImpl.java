package com.ruoyi.dictionary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.dictionary.mapper.TDictionaryCityDao;
import com.ruoyi.dictionary.entity.TDictionaryCity;
import com.ruoyi.dictionary.service.TDictionaryCityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (TDictionaryCity)表服务实现类
 *
 * @author liuchun
 * @since 2021-06-30 13:46:26
 */
@Service("tDictionaryCityService")
public class TDictionaryCityServiceImpl extends ServiceImpl<TDictionaryCityDao, TDictionaryCity> implements TDictionaryCityService {

    @Resource
    private TDictionaryCityDao cityDao;

    /**
     * 修改数据
     * @param entity 要修改的实体的数据
     * @return
     */
    @Override
    public R updateByEntity(TDictionaryCity entity) {
        int i = cityDao.updateByEntity(entity);
        return R.ok(i > 0 ? "操作成功!":"请重试！");
    }
}
