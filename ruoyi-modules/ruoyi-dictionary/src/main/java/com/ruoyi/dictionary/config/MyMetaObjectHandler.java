package com.ruoyi.dictionary.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liuchun
 * @Package com.ruoyi.dictionary.config
 * @date 2021/6/30 12:15
 * description:
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     *
     * 使用Mybatis-plus执行insert操作这个方法执行，
     * 将fieldName中的字段的值设置为fieldVal的值，而后添加进去
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = new Date();
        this.setFieldValByName("createTime",date,metaObject);
        this.setFieldValByName("updateTime",date,metaObject);
        //数据库中已默认为1
//        this.setFieldValByName("version",1,metaObject);
    }

    /**
     *
     * 使用Mybatis-plus执行update操作这个方法执行，
     * 将fieldName中的字段的值修改为fieldVal的值
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}