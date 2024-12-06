package com.scc.maker.generator;

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
    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        String inputRootPath = "D:/projects/web/scc-generator/scc-generator-maker";
        String outputRootPath = "D:/projects/web/scc-generator/scc-generator-demo-projects/acm-template-pro";

        String inputPath;
        String outputPath;

        inputPath = new File(inputRootPath, "src/main/java/com/scc/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/acm/MainTemplate.java.ftl").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "src/main/resources/templates/java/cli/command/ConfigCommand.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/cli/command/ConfigCommand.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "src/main/resources/templates/java/cli/command/GenerateCommand.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/cli/command/GenerateCommand.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "src/main/resources/templates/java/cli/command/ListCommand.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/cli/command/ListCommand.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "src/main/resources/templates/java/cli/CommandExecutor.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/cli/CommandExecutor.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "src/main/resources/templates/java/generator/DynamicGenerator.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/generator/DynamicFileGenerator.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "src/main/resources/templates/java/generator/FileGenerator.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/generator/FileGenerator.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "src/main/resources/templates/java/generator/JarGenerator.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/generator/JarGenerator.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "src/main/resources/templates/java/generator/MainGenerator.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/generator/MainGenerator.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "src/main/resources/templates/java/generator/StaticGenerator.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/generator/StaticFileGenerator.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "src/main/resources/templates/java/model/DataModel.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/model/DataModel.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "src/main/resources/templates/java/Main.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/main/java/com/scc/Main.java").getAbsolutePath();
        StaticFileGenerator.copyFileByHutool(inputPath, outputPath);

        inputPath = new File(inputRootPath, "src/main/resources/templates/pom.xml.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "pom.xml").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        doGenerate(MetaManager.getMetaObject());

        String outputRootPath = "D:/projects/web/scc-generator/scc-generator-demo-projects/acm-template-pro";

        Meta metaObject = MetaManager.getMetaObject();

        String shellOutputPath = outputRootPath + File.separator + "generator";
        String jarPath = String.format("%s-%s.jar", metaObject.getName(), metaObject.getVersion());
        ScriptGenerator.doGenerate(shellOutputPath, jarPath);
    }
}
