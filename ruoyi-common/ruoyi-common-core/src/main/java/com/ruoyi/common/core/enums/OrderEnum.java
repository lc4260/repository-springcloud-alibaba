package com.ruoyi.common.core.enums;

/**
 * @author liuchun
 * @Package com.ruoyi.common.core.enums
 * @date 2021/7/1 11:15
 * description:
 */
public enum OrderEnum {
    UP("up","升序"),
    DOWN("down","降序");
    private final String key;
    private final String value;

    OrderEnum(String key, String value)
    {
        this.key = key;
        this.value = value;
    }
    public String value()
    {
        return value;
    }
    public String key()
    {
        return key;
    }
}
