package com.ruoyi.dictionary.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.enums.OrderEnum;
import com.ruoyi.common.core.enums.TurnOnEnum;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.dictionary.entity.TDictionaryArea;
import com.ruoyi.dictionary.entity.TDictionaryCity;
import com.ruoyi.dictionary.mapper.TDictionaryAreaDao;
import com.ruoyi.dictionary.mapper.TDictionaryCityDao;
import com.ruoyi.dictionary.mapper.TDictionaryProvinceDao;
import com.ruoyi.dictionary.entity.TDictionaryProvince;
import com.ruoyi.dictionary.service.TDictionaryProvinceService;
import com.ruoyi.dictionary.utils.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * (TDictionaryProvince)表服务实现类
 *
 * @author liuchun
 * @since 2021-06-30 10:09:05
 */
@Service("tDictionaryProvinceService")
public class TDictionaryProvinceServiceImpl extends ServiceImpl<TDictionaryProvinceDao, TDictionaryProvince> implements TDictionaryProvinceService {

    @Resource
    private TDictionaryProvinceDao provinceDao;
    @Resource
    private TDictionaryCityDao cityDao;
    @Resource
    private TDictionaryAreaDao areaDao;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RegionUtil regionUtil;
    /**
     *
     * @param entity
     * @return
     */
    @Transactional
    @Override
    public synchronized R order(TDictionaryProvince entity) {
        //查询原来的排序
        TDictionaryProvince select = provinceDao.selectById(entity.getPkProvinceId());
        entity.setDicSort(select.getDicSort());
        if(OrderEnum.UP.key().equals(entity.getOrder())){
            provinceDao.upOrderById(entity);
        }else {
            provinceDao.downOrderById(entity);
        }
        return R.ok("操作成功!");
    }

    /**
     * 修改数据
     * @param entity 要修改的实体的数据
     * @return
     */
    @Override
    public R updateByEntity(TDictionaryProvince entity) {
        //先查版本号
        int i = provinceDao.updateByEntity(entity);
        return R.ok(i > 0 ? "操作成功!":"请重试！");
    }

    /**
     * 获取行政区域树
     * 该方法也可使用缓存预热，提升用户体验
     * 如果行政区划有变化，就需要更新缓存或者删除key
     * @return
     */
    @Override
    public R treeSelect() {
        /**
         * 先看redis中有没有
         */
        List<TDictionaryProvince> provinceList = redisService.getCacheList("dictionary:region:tree");
        if(provinceList != null && provinceList.size() > 0){
            return R.ok(provinceList,"获取成功！");
        }
        //调用工具类，查询
        provinceList = regionUtil.getRegionTree();
        return R.ok(provinceList,"获取成功！");
    }
}
