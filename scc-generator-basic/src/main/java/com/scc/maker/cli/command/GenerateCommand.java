package com.scc.maker.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.scc.maker.generator.DynamicGenerator;
import com.scc.maker.model.TemplateConfig;
import lombok.Data;
import picocli.CommandLine.Option;

import picocli.CommandLine.Command;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * className:  GenerateCommand
 * Package:  com.scc.cli.command
 * Description:
 *
 * @Date: 2024/11/4 23:05
 * @Author: shicc
 */
@Data
@Command(name = "generate", mixinStandardHelpOptions = true)
public class GenerateCommand implements Callable<Integer> {

    @Option(names = {"-a", "--author"}, arity = "0..1", interactive = true, echo = true)
    private String author;

    @Option(names = {"-o", "--output"}, arity = "0..1", interactive = true, echo = true)
    private String outputText;

    @Option(names = {"-l", "--loop"}, arity = "0..1", interactive = true, echo = true)
    private boolean loop;

    @Override
    public Integer call() throws Exception {
        String projectPath = System.getProperty("user.dir");
        String srcPath = projectPath + File.separator + "scc-generator-basic" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String targetPath = projectPath + File.separator + "scc-generator-demo-projects/acm-template/src/com/scc/acm/MainTemplate.java";
        TemplateConfig templateConfig = new TemplateConfig();
        BeanUtil.copyProperties(this, templateConfig);
        DynamicGenerator.doGenerator(srcPath, targetPath, templateConfig);
        return 0;
    }
}
