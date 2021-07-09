package com.ruoyi.common.core.enums;

/**
 * @author liuchun
 * @Package com.ruoyi.common.core.enums
 * @date 2021/6/30 14:42
 * description:
 */
public enum TurnOnEnum {
    ON(1),
    OFF(0);
    private final int value;

    TurnOnEnum(int value)
    {
        this.value = value;
    }
    public int value()
    {
        return value;
    }
}
