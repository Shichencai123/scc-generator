package com.scc.maker.meta.enums;

/**
 * @Date: 2025/1/24 16:15
 * @Author: shicc
 * @Description: 文件类型枚举
 */

public enum FileTypeEnum {
    /**
     * 目录
     */
    DIR("目录", "dir"),
    /**
     * 文件
     */
    FILE("文件", "file");

    FileTypeEnum(String text, String value) {
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
