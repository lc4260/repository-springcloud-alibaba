package com.ruoyi.dictionary.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;


import lombok.Data;

/**
 * (TDictionaryProvince)表实体类
 *
 * @author liuchun
 * @since 2021-06-30 10:09:02
 */
@SuppressWarnings("serial")
@TableName(value = "t_dictionary_province",excludeProperty="city_list")
@Data
public class TDictionaryProvince extends Model<TDictionaryProvince> {

    @TableId(value = "pk_province_id",type = IdType.INPUT)
    private String pkProvinceId;
    //省份名称
    private String provinceName;
    //区划代码
    private String zoningCode;
    //简称
    private String abbreviation;
    //省会
    private String provinceCapital;
    //创建时间
    @TableField(fill= FieldFill.INSERT)
    private Date createTime;
    //修改时间
    @TableField(fill= FieldFill.UPDATE)
    private Date updateTime;
    //是否启用
    private Integer turnOn;
    //版本号
    @Version()
    private Integer version;
    private Integer dicSort;
    @TableField(select=false)
    private String order;
    @TableField(select=false)
    private List<TDictionaryCity> cityList;
}
