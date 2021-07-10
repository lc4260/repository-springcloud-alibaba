package com.ruoyi.dictionary.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.enums.TurnOnEnum;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.dictionary.entity.TDictionaryArea;
import com.ruoyi.dictionary.entity.TDictionaryCity;
import com.ruoyi.dictionary.entity.TDictionaryProvince;
import com.ruoyi.dictionary.mapper.TDictionaryAreaDao;
import com.ruoyi.dictionary.mapper.TDictionaryCityDao;
import com.ruoyi.dictionary.mapper.TDictionaryProvinceDao;
import com.ruoyi.dictionary.service.TDictionaryProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * @author liuchun
 * @Package com.ruoyi.dictionary.utils
 * @date 2021/7/10 15:02
 * description:
 */
@Component
public class RegionUtil {

    @Resource
    private TDictionaryProvinceDao provinceDao;
    @Resource
    private TDictionaryCityDao cityDao;
    @Resource
    private TDictionaryAreaDao areaDao;
    @Resource
    private RedisService redisService;

    /**
     * 获取行政区域树
     * 该方法也可使用缓存预热，提升用户体验
     * 如果行政区划有变化，就需要更新缓存或者删除key
     * @return
     */
    public  List<TDictionaryProvince> getRegionTree(){
        //先查省份
        TDictionaryProvince queryProvince = new TDictionaryProvince();
        queryProvince.setTurnOn(TurnOnEnum.ON.value());
        List<TDictionaryProvince> provinceList = provinceDao.selectList(new QueryWrapper<TDictionaryProvince>(queryProvince));
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
        //放入缓存
        redisService.setCacheList("dictionary:region:tree",provinceList);
        return provinceList;
    }
}
