package com.ruoyi.dictionary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.dictionary.entity.TDictionaryCity;
import com.ruoyi.dictionary.mapper.TDictionaryAreaDao;
import com.ruoyi.dictionary.entity.TDictionaryArea;
import com.ruoyi.dictionary.mapper.TDictionaryCityDao;
import com.ruoyi.dictionary.service.TDictionaryAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (TDictionaryArea)表服务实现类
 *
 * @author liuchun
 * @since 2021-06-30 14:29:33
 */
@Service("tDictionaryAreaService")
public class TDictionaryAreaServiceImpl extends ServiceImpl<TDictionaryAreaDao, TDictionaryArea> implements TDictionaryAreaService {

    @Resource
    private TDictionaryAreaDao areaDao;

    /**
     * 修改数据
     * @param entity 要修改的实体的数据
     * @return
     */
    @Override
    public R updateByEntity(TDictionaryArea entity) {
        int i = areaDao.updateByEntity(entity);
        return R.ok(i > 0 ? "操作成功!":"请重试！");
    }
}
