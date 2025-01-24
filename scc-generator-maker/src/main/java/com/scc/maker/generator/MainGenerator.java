package com.scc.maker.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.scc.maker.meta.Meta;
import com.scc.maker.meta.MetaManager;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;

/**
 * className:  MainGenerator
 * Package:  com.scc.maker.generator
 * Description: 核心生成器
 *
 * @Date: 2024/11/27 23:02
 * @Author: shicc
 */
public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {

        Meta model = MetaManager.getMetaObject();
        System.out.println(model);
        // 输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputRootPath = projectPath + File.separator + "generated" + File.separator + model.getName();
        if (!FileUtil.exist(outputRootPath)) {
            FileUtil.mkdir(outputRootPath);
        }
        // 读取 resources 目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputRootPath = classPathResource.getAbsolutePath();
        // Java 包基础路径
        String outputBasePackage = model.getBasePackage();
        String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
      /*  String outputBaseJavaPackagePath = output + File.separator + "src/main/java/" + outputBasePackagePath;*/
        String inputPath;
        String outputPath;

        inputPath = new File(inputRootPath, "templates/java/cli/command/ConfigCommand.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/cli/command/ConfigCommand.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "templates/java/cli/command/GenerateCommand.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/cli/command/GenerateCommand.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "templates/java/cli/command/ListCommand.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/cli/command/ListCommand.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "templates/java/cli/CommandExecutor.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/cli/CommandExecutor.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "templates/java/generator/DynamicGenerator.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/generator/DynamicGenerator.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "templates/java/generator/JarGenerator.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/generator/JarGenerator.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "templates/java/generator/MainGenerator.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/generator/MainGenerator.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "templates/java/generator/StaticGenerator.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/generator/StaticGenerator.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "templates/java/model/DataModel.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/model/DataModel.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "templates/java/Main.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/Main.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "templates/java/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/acm/MainTemplate.java.ftl").getAbsolutePath();
        StaticFileGenerator.copyFileByHutool(inputPath, outputPath);

        inputPath = new File(inputRootPath, "templates/pom.xml.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "pom.xml").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(projectPath, ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath, ".gitignore").getAbsolutePath();
        StaticFileGenerator.copyFileByHutool(inputPath, outputPath);

        inputPath = new File(projectPath, "readme.md").getAbsolutePath();
        outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
        StaticFileGenerator.copyFileByHutool(inputPath, outputPath);



        // 构建 jar 包
        JarGenerator.doGenerate(outputRootPath);
        // 封装脚本
        String shellOutputFilePath = outputRootPath + File.separator + "generator";
        String jarName = String.format("%s-%s.jar", model.getName(), model.getVersion());
        String jarPath = "target/" + jarName;
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);


    }
}
