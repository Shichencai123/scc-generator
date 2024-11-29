package com.scc.maker.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

/**
 * className:  ListCommand.java.ftl
 * Package:  com.scc.cli.command
 * Description:
 *
 * @Date: 2024/11/4 23:06
 * @Author: shicc
 */
@CommandLine.Command(name = "list", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {

    @Override
    public void run() {
        String projectPath = System.getProperty("user.dir");
        List<File> files = FileUtil.loopFiles(projectPath + File.separator + "scc-generator-demo-projects/acm-template");
        for (File file : files) {
            System.out.println(file.getName());
        }
    }
}
