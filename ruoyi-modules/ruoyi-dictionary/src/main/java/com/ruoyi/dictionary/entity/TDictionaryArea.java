package com.ruoyi.dictionary.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;

/**
 * (TDictionaryArea)表实体类
 *
 * @author makejava
 * @since 2021-06-30 14:29:32
 */
@SuppressWarnings("serial")
@TableName("t_dictionary_area")
@Data
public class TDictionaryArea extends Model<TDictionaryArea> {

    @TableId(value = "pk_area_id",type = IdType.INPUT)
    private String pkAreaId;
    //地级区名称
    private String areaName;
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
    //所属城市
    private String fkCityId;
    //版本号
    @Version()
    private Integer version;

}
