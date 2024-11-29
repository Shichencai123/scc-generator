package com.scc.maker.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.scc.maker.generator.file.DynamicFileGenerator;
import com.scc.maker.meta.Meta;
import com.scc.maker.meta.MetaManager;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * className:  MainGenerator
 * Package:  com.scc.maker.generator
 * Description:
 *
 * @Date: 2024/11/27 23:02
 * @Author: shicc
 */
public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);

        //输出的根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated";
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        //读取resource目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();

        //基础包名
        //com/scc
        String basePackage = meta.getBasePackage();
        //com.scc
        basePackage = StrUtil.join("/", StrUtil.split(basePackage, "."));
        String javaBasePackagePath = outputPath + File.separator + "src/main/java/" + basePackage;

        String inputFilePath;
        String outputFilePath;

        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = javaBasePackagePath + File.separator + "model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
    }
}
