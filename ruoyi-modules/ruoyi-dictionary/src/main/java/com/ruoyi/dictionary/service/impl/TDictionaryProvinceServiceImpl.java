package com.ruoyi.dictionary.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.enums.OrderEnum;
import com.ruoyi.common.core.enums.TurnOnEnum;
import com.ruoyi.dictionary.entity.TDictionaryArea;
import com.ruoyi.dictionary.entity.TDictionaryCity;
import com.ruoyi.dictionary.mapper.TDictionaryAreaDao;
import com.ruoyi.dictionary.mapper.TDictionaryCityDao;
import com.ruoyi.dictionary.mapper.TDictionaryProvinceDao;
import com.ruoyi.dictionary.entity.TDictionaryProvince;
import com.ruoyi.dictionary.service.TDictionaryProvinceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * (TDictionaryProvince)表服务实现类
 *
 * @author makejava
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

    @Override
    public R updateByEntity(TDictionaryProvince entity) {
        //先查版本号
        int i = provinceDao.updateByEntity(entity);
        return R.ok(i > 0 ? "操作成功!":"请重试！");
    }

    @Override
    public R treeSelect() {
        //先查省份
        TDictionaryProvince queryProvince = new TDictionaryProvince();
        queryProvince.setTurnOn(TurnOnEnum.ON.value());
        List<TDictionaryProvince> provinceList = this.provinceDao.selectList(new QueryWrapper<TDictionaryProvince>(queryProvince));
        //再查所属城市
        Iterator<TDictionaryProvince> provinceIterator = provinceList.iterator();
        while (provinceIterator.hasNext()){
            TDictionaryProvince next = provinceIterator.next();
            TDictionaryCity queryCity = new TDictionaryCity();
            queryCity.setFkProvinceId(next.getPkProvinceId());
            queryCity.setTurnOn(TurnOnEnum.ON.value());
            List<TDictionaryCity> cityList = cityDao.selectList(new QueryWrapper<TDictionaryCity>(queryCity));
            //再查管辖区域
            Iterator<TDictionaryCity> cityIterator = cityList.iterator();
            while (cityIterator.hasNext()) {
                TDictionaryCity nextCity = cityIterator.next();
                TDictionaryArea queryArea = new TDictionaryArea();
                queryArea.setFkCityId(nextCity.getPkCityId());
                queryArea.setTurnOn(TurnOnEnum.ON.value());
                List<TDictionaryArea> areaList = areaDao.selectList(new QueryWrapper<TDictionaryArea>(queryArea));
                nextCity.setAreaList(areaList);
            }
            //
            next.setCityList(cityList);
        }
        return R.ok(provinceList,"获取成功！");
    }
}
