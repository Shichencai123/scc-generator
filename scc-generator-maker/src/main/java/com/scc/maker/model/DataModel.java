package com.scc.maker.model;

import lombok.Data;

/**
 * className:  DataModel
 * Package:  com.scc.generator.model
 * Description:
 *
 * @Date: 2024/11/2 11:00
 * @Author: shicc
 */
@Data
public class DataModel {
    /**
     * 作者注释
     */
    private String author = "scc";
    /**
     * 输出信息
     */
    private String outputText = "sum = ";
    /**
     * 是否生成循环
     */
    private boolean loop = true;
}
