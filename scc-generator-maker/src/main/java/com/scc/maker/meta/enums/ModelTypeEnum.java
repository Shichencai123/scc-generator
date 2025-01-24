package com.scc.maker.meta.enums;

/**
 * @Date: 2025/1/24 16:25
 * @Author: shicc
 * @Description: 模型类型枚举
 */
public enum ModelTypeEnum {
    /**
     * 字符串类型变量
     */
    STRING("字符串", "String"),
    /**
     * 布尔值类型变量
     */
    BOOLEAN("布尔值", "boolean");

    ModelTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    private final String text;

    private final String value;

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
