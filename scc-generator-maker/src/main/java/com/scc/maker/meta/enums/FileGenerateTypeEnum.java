package com.scc.maker.meta.enums;

/**
 * @Date: 2025/1/24 16:15
 * @Author: shicc
 * @Description: 文件生成类型枚举
 */
public enum FileGenerateTypeEnum {
    /**
     * 根据ftl模板动态生成文件
     */
    DYNAMIC("动态生成", "dynamic"),
    /**
     * 拷贝静态文件
     */
    STATIC("静态生成", "static");


    FileGenerateTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    };
    private final String text;

    private final String value;

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
