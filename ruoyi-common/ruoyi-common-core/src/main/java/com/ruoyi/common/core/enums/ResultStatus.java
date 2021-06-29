package com.ruoyi.common.core.enums;

/**
 * 返回结果状态枚举
 * @author liuchun
 * @Package com.ruoyi.common.core.enums
 * @date 2021/6/29 16:12
 * description:
 */
public enum ResultStatus {
    OK("success"),
    ERROR("error");
    private final String status;

    ResultStatus(String status)
    {
        this.status = status;
    }
    public String getStatus()
    {
        return status;
    }

}
