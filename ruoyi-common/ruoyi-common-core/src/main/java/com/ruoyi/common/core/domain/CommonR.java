package com.ruoyi.common.core.domain;

import com.ruoyi.common.core.enums.ResultStatus;

import java.util.HashMap;

/**
 * 通用返回结果json封装体
 * @author liuchun
 * @Package com.ruoyi.common.core.domain
 * @date 2021/6/29 16:03
 * description:
 */
public class CommonR extends HashMap<String,Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public CommonR(){
    }
    public CommonR code(int code){
        this.put("code",code);
        return this;
    }
    public CommonR msg(String msg){
        this.put("msg",msg);
        return this;
    }
    public CommonR data(Object data){
        this.put("data",data);
        return this;
    }
    public CommonR status(String status){
        this.put("status",status);
        return this;
    }
    public CommonR success(String msg){
        return new CommonR().code(200).msg(msg).status(ResultStatus.OK.getStatus()).data(null);
    }
    public CommonR success(String msg,Object data){
        return new CommonR().code(200).msg(msg).status(ResultStatus.OK.getStatus()).data(data);
    }
    public CommonR error(String msg){
        return new CommonR().code(444).msg(msg).status(ResultStatus.ERROR.getStatus()).data(null);
    }
    @Override
    public CommonR put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
