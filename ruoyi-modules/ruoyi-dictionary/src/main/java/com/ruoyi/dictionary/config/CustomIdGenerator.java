//package com.ruoyi.dictionary.config;
//
//import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
//import org.springframework.stereotype.Component;
//
///**
// * @author liuchun
// * @Package com.ruoyi.dictionary.config
// * @date 2021/6/30 11:24
// * description:
// */
//@Component
//public class CustomIdGenerator implements IdentifierGenerator {
//    @Override
//    public Long nextId(Object entity) {
//        //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
//        String bizKey = entity.getClass().getName();
//        //根据bizKey调用分布式ID生成
//        long id = ....;
//        //返回生成的id值即可.
//        return id;
//    }
//}
