package com.scc.maker;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.scc.maker.generator.file.DynamicFileGenerator;
import com.scc.maker.meta.Meta;
import com.scc.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * className:  Main
 * Package:  com.scc.cli
 * Description:
 *
 * @Date: 2024/11/4 23:19
 * @Author: shicc
 */
public class Main {
    public static void main(String[] args) throws TemplateException, IOException {
//        args = new String[]{"generate", "-l", "-a", "-o"};
//        args = new String[]{"config"};
        //args = new String[]{"list"};
        //CommandExecutor commandExecutor = new CommandExecutor();
        //commandExecutor.doExecute(args);
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);

        // 输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }

        // 读取 resources 目录
        /*ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();*/

        String inputResourcePath = "E:\\project\\web\\scc-generator\\scc-generator-maker\\src\\main\\resources";

        // Java 包基础路径
        String outputBasePackage = meta.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/" + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;

        // model.DataModel
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

        DynamicFileGenerator.doGenerate(inputFilePath, outputPath, meta);
    }
}
