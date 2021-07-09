package com.ruoyi.dictionary.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * (TDictionaryCity)表实体类
 *
 * @author makejava
 * @since 2021-06-30 13:46:25
 */
@SuppressWarnings("serial")
@TableName("t_dictionary_city")
@Data
public class TDictionaryCity extends Model<TDictionaryCity> {

    @TableId(value = "pk_city_id",type = IdType.INPUT)
    private String pkCityId;
    //城市名称
    private String cityName;
    //区划代码
    private String zoningCode;
    //是否启用
    private Integer turnOn;
    //创建时间
    @TableField(fill= FieldFill.INSERT)
    private Date createTime;
    //修改时间
    @TableField(fill= FieldFill.UPDATE)
    private Date updateTime;
    //所属省份
    private String fkProvinceId;
    //版本号
    @Version()
    private Integer version;
    @TableField(select=false)
    private List<TDictionaryArea> areaList;

}
