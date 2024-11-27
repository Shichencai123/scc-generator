package com.scc.maker.model;

import lombok.Data;

/**
 * className:  TemplateConfig
 * Package:  com.scc.generator.model
 * Description:
 *
 * @Date: 2024/11/2 11:00
 * @Author: shicc
 */
@Data
public class TemplateConfig {
    private String author = "scc";
    private String outputText = "sum = ";
    private boolean loop = true;
}
