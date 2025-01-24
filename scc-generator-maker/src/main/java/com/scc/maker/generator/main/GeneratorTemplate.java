package com.scc.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.scc.maker.generator.DynamicGenerator;
import com.scc.maker.generator.JarGenerator;
import com.scc.maker.generator.ScriptGenerator;
import com.scc.maker.generator.StaticFileGenerator;
import com.scc.maker.meta.Meta;
import com.scc.maker.meta.MetaManager;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;

/**
 * @Date: 2025/1/24 15:45
 * @Author: shicc
 * @Description: 模板方法生成器
 */
public class GeneratorTemplate {
    public void doGenerator() throws TemplateException, IOException, InterruptedException {
        Meta model = MetaManager.getMetaObject();
        System.out.println(model);
        // 输出根路径
        String projectPath = System.getProperty("user.dir");
        String outputRootPath = projectPath + File.separator + "generated" + File.separator + model.getName();
        if (!FileUtil.exist(outputRootPath)) {
            FileUtil.mkdir(outputRootPath);
        }
        //1、复制原始文件
        String sourceCopyDestPath = copyOriginalFiles(model, outputRootPath);

        //2、动态、静态代码生成
        generateFiles(model, outputRootPath);

        //3、构建 jar 包
        String jarPath = generateJar(model, outputRootPath);

        //4、封装脚本
        String shellOutputFilePath = buildScript(outputRootPath, jarPath);

        //5、生成精简版文件
        generateMiniSource(outputRootPath, sourceCopyDestPath, shellOutputFilePath, jarPath);
    }

    protected String copyOriginalFiles(Meta model, String outputRootPath) {
        String sourceRootPath = model.getFileConfig().getSourceRootPath();
        String sourceCopyDestPath = outputRootPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath, sourceCopyDestPath, false);
        return sourceCopyDestPath;
    }

    protected void generateFiles(Meta model, String outputRootPath) throws IOException, TemplateException {
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

        inputPath = new File(inputRootPath, "templates/README.md.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, model);
    }

    protected String generateJar(Meta meta, String outputRootPath) throws IOException, InterruptedException {
        String jarName = String.format("%s-%s.jar", meta.getName(), meta.getVersion());
        String jarPath = outputRootPath + File.separator +"target" + File.separator + jarName;
        JarGenerator.doGenerate(outputRootPath);
        return jarPath;
    }

    protected String buildScript(String outputRootPath, String jarPath) throws IOException {
        String shellOutputFilePath = outputRootPath + File.separator + "generator";
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
        return shellOutputFilePath;
    }

    protected void generateMiniSource(String outputRootPath, String sourceCopyDestPath, String shellOutputFilePath, String jarPath) {
        String distOutputPath = outputRootPath + "-dist";
        String targetPath = outputRootPath + File.separator + "target";
        //复制jar
        FileUtil.copy(jarPath, targetPath, false);
        //复制源文件
        FileUtil.copy(sourceCopyDestPath, distOutputPath, false);
        //复制.bat文件
        FileUtil.copy(shellOutputFilePath, distOutputPath, false);
        FileUtil.copy(shellOutputFilePath + ".bat", distOutputPath, false);
    }
}
