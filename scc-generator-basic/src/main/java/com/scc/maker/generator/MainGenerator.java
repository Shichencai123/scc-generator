package com.scc.maker.generator;

import com.scc.maker.model.TemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * className:  MainGenerator
 * Package:  com.scc.generator
 * Description:
 *
 * @Date: 2024/11/2 20:53
 * @Author: shicc
 */
public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        // 静态资源生成
        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + File.separator + "scc-generator-demo-projects/acm-template";
        StaticGenerator.copyFileByHutool(inputPath, projectPath);

        String srcPath = projectPath + File.separator + "scc-generator-basic" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String targetPath = projectPath + File.separator + "acm-template/src/com/scc/acm/MainTemplate.java";
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setLoop(false);
        templateConfig.setAuthor("hello world!");
        templateConfig.setOutputText("sum =");
        DynamicGenerator.doGenerator(srcPath, targetPath, templateConfig);
        // 动态资源替换


    }
}
